import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, interval } from 'rxjs';
import { switchMap, startWith } from 'rxjs/operators';
import { Stock } from '../models/stock.model';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  private apiUrl = 'http://localhost:8080/api/stocks';

  constructor(private http: HttpClient) {}

  getAllStocks(): Observable<Stock[]> {
    return this.http.get<Stock[]>(`${this.apiUrl}/all`);
  }

  getStock(symbol: string): Observable<Stock> {
    return this.http.get<Stock>(`${this.apiUrl}/${symbol}`);
  }

  getStockPrice(symbol: string): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/${symbol}/price`);
  }

  getStocksRealtime(): Observable<Stock[]> {
    return interval(5000).pipe(
      startWith(0),
      switchMap(() => this.getAllStocks())
    );
  }
}
