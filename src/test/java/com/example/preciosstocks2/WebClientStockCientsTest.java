package com.example.preciosstocks2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

class WebClientStockCientsTest {
    WebClient webClient = WebClient.builder().build();
@Test
    void shouldRetrieveDataFromRuningService(){
    //Given
WebClientStockCients webClientStockCients = new WebClientStockCients(webClient);
//When
    Flux<StockPrices>prices = webClientStockCients.pricesFor("GOOG");
    //Then
    Assertions.assertNotNull(prices);
    Flux<StockPrices>fivePrices = prices.take(5);
    Assertions.assertEquals(5,fivePrices.count().block());
    Assertions.assertEquals("GOOG", fivePrices.blockFirst().getSymbol());
}
}