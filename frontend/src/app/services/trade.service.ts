import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trade, TradeRequest } from '../models/stock.model';

@Injectable({
  providedIn: 'root'
})
export class TradeService {
  private apiUrl = 'http://localhost:8080/api/trades';

  constructor(private http: HttpClient) {}

  executeTrade(tradeRequest: TradeRequest): Observable<Trade> {
    return this.http.post<Trade>(`${this.apiUrl}/execute`, tradeRequest);
  }

  getTradeHistory(): Observable<Trade[]> {
    return this.http.get<Trade[]>(`${this.apiUrl}/history`);
  }

  getRecentTrades(): Observable<Trade[]> {
    return this.http.get<Trade[]>(`${this.apiUrl}/recent`);
  }
}
