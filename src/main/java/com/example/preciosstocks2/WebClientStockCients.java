package com.example.preciosstocks2;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

@Log4j2
public class WebClientStockCients {
    private static  WebClient webClient;

    public WebClientStockCients(WebClient webClient) {
        WebClientStockCients.webClient = webClient;

    }

    public Flux<StockPrices> pricesFor( String symbol) {
        return webClient.get()
                .uri("localhost:8080/stocks/{symbol}", symbol)
                .retrieve()
                .bodyToFlux(StockPrices.class)
                .retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(20))
                .doOnError(IOException.class, e -> log.error(e.getMessage()));

    }
    public void stopWebClient(){
        webClient.delete();
        System.out.println("The webclient is deletedÑ " + webClient.get().toString());
    }
}
