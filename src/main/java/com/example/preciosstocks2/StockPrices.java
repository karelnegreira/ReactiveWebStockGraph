package com.example.preciosstocks2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StockPrices {
    private static String symbol;
    private Double price;
    private LocalDateTime time;

    public StockPrices(){
        //empty constructor for possible autowiring
      // this.symbol = "GOOG";
    }
    public void setSymbol(String symbol){this.symbol = symbol;}
    public String getSymbol() {
        return symbol;
    }
    public Double getPrice(){return price;}
}
