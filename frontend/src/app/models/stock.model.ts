export interface Stock {
  id: number;
  symbol: string;
  name: string;
  currentPrice: number;
  previousClose: number;
  dayHigh: number;
  dayLow: number;
  lastUpdated: Date;
}

export interface User {
  id: number;
  username: string;
  email: string;
  balance: number;
}

export interface Portfolio {
  id: number;
  stock: Stock;
  quantity: number;
  averagePrice: number;
  createdAt: Date;
  updatedAt: Date;
}

export interface Trade {
  id: number;
  stock: Stock;
  type: 'BUY' | 'SELL';
  quantity: number;
  price: number;
  totalAmount: number;
  status: string;
  createdAt: Date;
}

export interface PortfolioSummary {
  balance: number;
  totalInvestment: number;
  currentValue: number;
  profitLoss: number;
  profitLossPercentage: number;
  totalPortfolioValue: number;
}

export interface TradeRequest {
  symbol: string;
  type: 'BUY' | 'SELL';
  quantity: number;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface SignupRequest {
  username: string;
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  id: number;
  username: string;
  email: string;
}
