import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Portfolio, PortfolioSummary } from '../models/stock.model';

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {
  private apiUrl = 'http://localhost:8080/api/portfolio';

  constructor(private http: HttpClient) {}

  getPortfolio(): Observable<Portfolio[]> {
    return this.http.get<Portfolio[]>(`${this.apiUrl}`);
  }

  getPortfolioSummary(): Observable<PortfolioSummary> {
    return this.http.get<PortfolioSummary>(`${this.apiUrl}/summary`);
  }
}
