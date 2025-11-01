package com.trading.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TradeType type;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(nullable = false)
    private Double price;
    
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TradeStatus status = TradeStatus.COMPLETED;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public enum TradeType {
        BUY, SELL
    }
    
    public enum TradeStatus {
        PENDING, COMPLETED, FAILED
    }
}
