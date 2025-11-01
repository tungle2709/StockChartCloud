package com.trading.app.service;

import com.trading.app.model.Portfolio;
import com.trading.app.model.User;
import com.trading.app.repository.PortfolioRepository;
import com.trading.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PortfolioService {
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<Portfolio> getUserPortfolio(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return portfolioRepository.findByUser(user);
    }
    
    public User getUserInfo(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
