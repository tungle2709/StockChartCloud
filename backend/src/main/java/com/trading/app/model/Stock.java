package com.trading.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String symbol;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Double currentPrice;
    
    @Column(name = "previous_close")
    private Double previousClose;
    
    @Column(name = "day_high")
    private Double dayHigh;
    
    @Column(name = "day_low")
    private Double dayLow;
    
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated = LocalDateTime.now();
}
