import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trade, TradeRequest } from '../models/stock.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TradeService {
  private apiUrl = 'http://localhost:8080/api/trades';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  executeTrade(tradeRequest: TradeRequest): Observable<Trade> {
    return this.http.post<Trade>(`${this.apiUrl}/execute`, tradeRequest, {
      headers: this.getHeaders()
    });
  }

  getTradeHistory(): Observable<Trade[]> {
    return this.http.get<Trade[]>(`${this.apiUrl}/history`, {
      headers: this.getHeaders()
    });
  }

  getRecentTrades(): Observable<Trade[]> {
    return this.http.get<Trade[]>(`${this.apiUrl}/recent`, {
      headers: this.getHeaders()
    });
  }
}
