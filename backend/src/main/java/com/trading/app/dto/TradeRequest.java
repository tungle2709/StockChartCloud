package com.trading.app.dto;

import com.trading.app.model.Trade.TradeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TradeRequest {
    @NotBlank
    private String symbol;
    
    @NotNull
    private TradeType type;
    
    @NotNull
    @Positive
    private Integer quantity;
}
