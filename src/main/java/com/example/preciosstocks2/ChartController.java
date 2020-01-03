package com.example.preciosstocks2;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Consumer;

@Component
public class ChartController  implements Consumer<StockPrices> {
    @FXML
    public  LineChart<String, Double> chart;
    String symbol;
    private WebClientStockCients webClientStockClient;
    private ObservableList<XYChart.Data<String,Double>>seriesData=FXCollections.observableArrayList();
    //  String symbol = stockPrices.getSymbol();
    StockCodeContainer stockCodeContainer = null;
    public ChartController(WebClientStockCients webClientStockClient) {
        this.webClientStockClient = webClientStockClient;
    }

    @FXML
    public void initialize(){
        stockCodeContainer = new StockCodeContainer();
        symbol = stockCodeContainer.getSymbol();
        System.out.println("initialize chartController" +symbol);
        ObservableList<XYChart.Series<String,Double>>data= FXCollections.observableArrayList();
        data.add(new XYChart.Series<>(symbol,seriesData));
        webClientStockClient.pricesFor(symbol)
                .subscribe(this);
        chart.getData().addAll(data);
    }
    public void clear(){
         //   webClientStockClient.pricesFor(symbol).subscribe(this).wait();
            webClientStockClient.stopWebClient();
            chart = null;
    }

    @Override
    public void accept(StockPrices stockPrices) {
        Platform.runLater(()->seriesData.add(new XYChart.Data<>(String.valueOf(stockPrices.getTime().getSecond()),
                stockPrices.getPrice() ))
        );
    }
}
