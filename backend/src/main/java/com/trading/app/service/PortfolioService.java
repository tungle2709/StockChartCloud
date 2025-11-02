package com.trading.app.service;

import com.trading.app.model.Portfolio;
import com.trading.app.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PortfolioService {
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    public List<Portfolio> getPortfolio() {
        return portfolioRepository.findAll();
    }
    
    public double getTotalBalance() {
        return 100000.00;
    }
}
