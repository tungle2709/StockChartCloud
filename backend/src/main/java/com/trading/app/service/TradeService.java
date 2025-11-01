package com.trading.app.service;

import com.trading.app.dto.TradeRequest;
import com.trading.app.model.Portfolio;
import com.trading.app.model.Stock;
import com.trading.app.model.Trade;
import com.trading.app.model.User;
import com.trading.app.repository.PortfolioRepository;
import com.trading.app.repository.StockRepository;
import com.trading.app.repository.TradeRepository;
import com.trading.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TradeService {
    
    @Autowired
    private TradeRepository tradeRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StockRepository stockRepository;
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Transactional
    public Trade executeTrade(TradeRequest tradeRequest, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Stock stock = stockRepository.findBySymbol(tradeRequest.getSymbol())
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        
        double totalAmount = stock.getCurrentPrice() * tradeRequest.getQuantity();
        
        if (tradeRequest.getType() == Trade.TradeType.BUY) {
            if (user.getBalance() < totalAmount) {
                throw new RuntimeException("Insufficient balance");
            }
            
            user.setBalance(user.getBalance() - totalAmount);
            updatePortfolio(user, stock, tradeRequest.getQuantity(), stock.getCurrentPrice(), true);
        } else {
            Portfolio portfolio = portfolioRepository.findByUserAndStock(user, stock)
                    .orElseThrow(() -> new RuntimeException("No holdings found"));
            
            if (portfolio.getQuantity() < tradeRequest.getQuantity()) {
                throw new RuntimeException("Insufficient shares");
            }
            
            user.setBalance(user.getBalance() + totalAmount);
            updatePortfolio(user, stock, tradeRequest.getQuantity(), stock.getCurrentPrice(), false);
        }
        
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        
        Trade trade = new Trade();
        trade.setUser(user);
        trade.setStock(stock);
        trade.setType(tradeRequest.getType());
        trade.setQuantity(tradeRequest.getQuantity());
        trade.setPrice(stock.getCurrentPrice());
        trade.setTotalAmount(totalAmount);
        trade.setStatus(Trade.TradeStatus.COMPLETED);
        trade.setCreatedAt(LocalDateTime.now());
        
        return tradeRepository.save(trade);
    }
    
    private void updatePortfolio(User user, Stock stock, int quantity, double price, boolean isBuy) {
        Portfolio portfolio = portfolioRepository.findByUserAndStock(user, stock)
                .orElse(null);
        
        if (isBuy) {
            if (portfolio == null) {
                portfolio = new Portfolio();
                portfolio.setUser(user);
                portfolio.setStock(stock);
                portfolio.setQuantity(quantity);
                portfolio.setAveragePrice(price);
                portfolio.setCreatedAt(LocalDateTime.now());
            } else {
                double totalCost = (portfolio.getAveragePrice() * portfolio.getQuantity()) + (price * quantity);
                portfolio.setQuantity(portfolio.getQuantity() + quantity);
                portfolio.setAveragePrice(totalCost / portfolio.getQuantity());
            }
        } else {
            portfolio.setQuantity(portfolio.getQuantity() - quantity);
            if (portfolio.getQuantity() == 0) {
                portfolioRepository.delete(portfolio);
                return;
            }
        }
        
        portfolio.setUpdatedAt(LocalDateTime.now());
        portfolioRepository.save(portfolio);
    }
    
    public List<Trade> getUserTrades(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return tradeRepository.findByUserOrderByCreatedAtDesc(user);
    }
    
    public List<Trade> getRecentTrades(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return tradeRepository.findTop10ByUserOrderByCreatedAtDesc(user);
    }
}
