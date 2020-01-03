package com.example.preciosstocks2;

import org.springframework.stereotype.Component;


public class StockCodeContainer {
  static  String symbol;
    public StockCodeContainer(){
        System.out.println("StockCodeContainer" + this.symbol);
    }
    public StockCodeContainer(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol(){
        return symbol;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
}
