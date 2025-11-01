package com.trading.app.service;

import com.trading.app.model.Stock;
import com.trading.app.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class StockService {
    
    @Autowired
    private StockRepository stockRepository;
    
    private Random random = new Random();
    
    @PostConstruct
    public void initializeStocks() {
        if (stockRepository.count() == 0) {
            List<Stock> stocks = Arrays.asList(
                createStock("AAPL", "Apple Inc.", 175.0),
                createStock("GOOGL", "Alphabet Inc.", 140.0),
                createStock("MSFT", "Microsoft Corporation", 370.0),
                createStock("AMZN", "Amazon.com Inc.", 145.0),
                createStock("TSLA", "Tesla Inc.", 245.0),
                createStock("META", "Meta Platforms Inc.", 310.0),
                createStock("NVDA", "NVIDIA Corporation", 495.0),
                createStock("JPM", "JPMorgan Chase & Co.", 155.0),
                createStock("V", "Visa Inc.", 250.0),
                createStock("WMT", "Walmart Inc.", 160.0)
            );
            stockRepository.saveAll(stocks);
        }
    }
    
    private Stock createStock(String symbol, String name, Double price) {
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setName(name);
        stock.setCurrentPrice(price);
        stock.setPreviousClose(price);
        stock.setDayHigh(price * 1.02);
        stock.setDayLow(price * 0.98);
        stock.setLastUpdated(LocalDateTime.now());
        return stock;
    }
    
    @Scheduled(fixedRate = 5000)
    public void updateStockPrices() {
        List<Stock> stocks = stockRepository.findAll();
        for (Stock stock : stocks) {
            double change = (random.nextDouble() - 0.5) * 2;
            double newPrice = stock.getCurrentPrice() * (1 + change / 100);
            
            stock.setCurrentPrice(Math.round(newPrice * 100.0) / 100.0);
            stock.setDayHigh(Math.max(stock.getDayHigh(), stock.getCurrentPrice()));
            stock.setDayLow(Math.min(stock.getDayLow(), stock.getCurrentPrice()));
            stock.setLastUpdated(LocalDateTime.now());
        }
        stockRepository.saveAll(stocks);
    }
    
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
    
    public Stock getStockBySymbol(String symbol) {
        return stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Stock not found: " + symbol));
    }
}
