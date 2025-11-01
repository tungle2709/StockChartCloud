package com.trading.app.controller;

import com.trading.app.dto.TradeRequest;
import com.trading.app.model.Trade;
import com.trading.app.security.UserDetailsImpl;
import com.trading.app.service.TradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trades")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TradeController {
    
    @Autowired
    private TradeService tradeService;
    
    @PostMapping("/execute")
    public ResponseEntity<?> executeTrade(@Valid @RequestBody TradeRequest tradeRequest, 
                                          Authentication authentication) {
        try {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Trade trade = tradeService.executeTrade(tradeRequest, userDetails.getUsername());
            return ResponseEntity.ok(trade);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/history")
    public ResponseEntity<List<Trade>> getTradeHistory(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(tradeService.getUserTrades(userDetails.getUsername()));
    }
    
    @GetMapping("/recent")
    public ResponseEntity<List<Trade>> getRecentTrades(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(tradeService.getRecentTrades(userDetails.getUsername()));
    }
}
