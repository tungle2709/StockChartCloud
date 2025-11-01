package com.trading.app.controller;

import com.trading.app.model.Portfolio;
import com.trading.app.model.User;
import com.trading.app.security.UserDetailsImpl;
import com.trading.app.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PortfolioController {
    
    @Autowired
    private PortfolioService portfolioService;
    
    @GetMapping
    public ResponseEntity<List<Portfolio>> getPortfolio(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(portfolioService.getUserPortfolio(userDetails.getUsername()));
    }
    
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getPortfolioSummary(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = portfolioService.getUserInfo(userDetails.getUsername());
        List<Portfolio> portfolios = portfolioService.getUserPortfolio(userDetails.getUsername());
        
        double totalInvestment = portfolios.stream()
                .mapToDouble(p -> p.getAveragePrice() * p.getQuantity())
                .sum();
        
        double currentValue = portfolios.stream()
                .mapToDouble(p -> p.getStock().getCurrentPrice() * p.getQuantity())
                .sum();
        
        double profitLoss = currentValue - totalInvestment;
        double profitLossPercentage = totalInvestment > 0 ? (profitLoss / totalInvestment) * 100 : 0;
        
        Map<String, Object> summary = new HashMap<>();
        summary.put("balance", user.getBalance());
        summary.put("totalInvestment", Math.round(totalInvestment * 100.0) / 100.0);
        summary.put("currentValue", Math.round(currentValue * 100.0) / 100.0);
        summary.put("profitLoss", Math.round(profitLoss * 100.0) / 100.0);
        summary.put("profitLossPercentage", Math.round(profitLossPercentage * 100.0) / 100.0);
        summary.put("totalPortfolioValue", Math.round((user.getBalance() + currentValue) * 100.0) / 100.0);
        
        return ResponseEntity.ok(summary);
    }
}
