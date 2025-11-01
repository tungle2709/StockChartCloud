package com.trading.app.controller;

import com.trading.app.model.Stock;
import com.trading.app.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StockController {
    
    @Autowired
    private StockService stockService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }
    
    @GetMapping("/{symbol}")
    public ResponseEntity<Stock> getStock(@PathVariable String symbol) {
        return ResponseEntity.ok(stockService.getStockBySymbol(symbol));
    }
    
    @GetMapping("/{symbol}/price")
    public ResponseEntity<Double> getStockPrice(@PathVariable String symbol) {
        Stock stock = stockService.getStockBySymbol(symbol);
        return ResponseEntity.ok(stock.getCurrentPrice());
    }
}
