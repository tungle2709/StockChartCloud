import { Component, OnInit, OnDestroy, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import { StockService } from '../../services/stock.service';
import { PortfolioService } from '../../services/portfolio.service';
import { TradeService } from '../../services/trade.service';
import { AuthService } from '../../services/auth.service';
import { Stock, Portfolio, PortfolioSummary, TradeRequest } from '../../models/stock.model';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

Chart.register(...registerables);

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, OnDestroy, AfterViewInit {
  @ViewChild('stockChart') stockChartRef!: ElementRef;
  
  stocks: Stock[] = [];
  selectedStock: Stock | null = null;
  portfolio: Portfolio[] = [];
  portfolioSummary: PortfolioSummary | null = null;
  chart: Chart | null = null;
  username: string = '';
  
  tradeRequest: TradeRequest = {
    symbol: '',
    type: 'BUY',
    quantity: 1
  };
  
  private subscription: Subscription = new Subscription();
  private chartData: { time: string, price: number }[] = [];

  constructor(
    private stockService: StockService,
    private portfolioService: PortfolioService,
    private tradeService: TradeService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const user = this.authService.getCurrentUser();
    if (user) {
      this.username = user.username;
      this.loadPortfolio();
    } else {
      this.username = 'Guest';
    }
    this.loadStocks();
    this.startRealTimeUpdates();
  }

  ngAfterViewInit(): void {
    setTimeout(() => this.initChart(), 100);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    if (this.chart) {
      this.chart.destroy();
    }
  }

  loadStocks(): void {
    this.stockService.getAllStocks().subscribe({
      next: (stocks) => {
        this.stocks = stocks;
        if (stocks.length > 0 && !this.selectedStock) {
          this.selectStock(stocks[0]);
        }
      },
      error: (err) => console.error('Error loading stocks:', err)
    });
  }

  loadPortfolio(): void {
    this.portfolioService.getPortfolio().subscribe({
      next: (portfolio) => this.portfolio = portfolio,
      error: (err) => console.error('Error loading portfolio:', err)
    });
    
    this.portfolioService.getPortfolioSummary().subscribe({
      next: (summary) => this.portfolioSummary = summary,
      error: (err) => console.error('Error loading summary:', err)
    });
  }

  startRealTimeUpdates(): void {
    const sub = this.stockService.getStocksRealtime().subscribe({
      next: (stocks) => {
        this.stocks = stocks;
        if (this.selectedStock) {
          const updated = stocks.find(s => s.symbol === this.selectedStock!.symbol);
          if (updated) {
            this.selectedStock = updated;
            this.updateChart(updated);
          }
        }
        this.loadPortfolio();
      },
      error: (err) => console.error('Error in real-time updates:', err)
    });
    this.subscription.add(sub);
  }

  selectStock(stock: Stock): void {
    this.selectedStock = stock;
    this.tradeRequest.symbol = stock.symbol;
    this.chartData = [];
    this.updateChart(stock);
  }

  initChart(): void {
    if (!this.stockChartRef) return;
    
    const ctx = this.stockChartRef.nativeElement.getContext('2d');
    this.chart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: [],
        datasets: [{
          label: 'Stock Price',
          data: [],
          borderColor: '#667eea',
          backgroundColor: 'rgba(102, 126, 234, 0.1)',
          borderWidth: 2,
          tension: 0.4,
          fill: true
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: {
            display: true,
            position: 'top'
          },
          title: {
            display: true,
            text: 'Real-Time Stock Price Chart'
          }
        },
        scales: {
          y: {
            beginAtZero: false,
            ticks: {
              callback: (value) => '$' + value
            }
          }
        }
      }
    });
  }

  updateChart(stock: Stock): void {
    if (!this.chart) return;
    
    const now = new Date().toLocaleTimeString();
    this.chartData.push({ time: now, price: stock.currentPrice });
    
    if (this.chartData.length > 20) {
      this.chartData.shift();
    }
    
    this.chart.data.labels = this.chartData.map(d => d.time);
    this.chart.data.datasets[0].data = this.chartData.map(d => d.price);
    this.chart.data.datasets[0].label = `${stock.symbol} Price`;
    this.chart.update();
  }

  executeTrade(): void {
    if (!this.tradeRequest.symbol || this.tradeRequest.quantity < 1) {
      alert('Please select a stock and enter valid quantity');
      return;
    }
    
    this.tradeService.executeTrade(this.tradeRequest).subscribe({
      next: () => {
        alert('Trade executed successfully!');
        this.loadPortfolio();
        this.tradeRequest.quantity = 1;
      },
      error: (err) => {
        alert('Trade failed: ' + (err.error || 'Unknown error'));
        console.error(err);
      }
    });
  }

  getPriceChange(stock: Stock): number {
    return ((stock.currentPrice - stock.previousClose) / stock.previousClose) * 100;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
