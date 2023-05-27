package com.example.coins;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;

public class CoinService {

    private final String apiBase = "https://api.coingecko.com/api/v3/coins/";

    private final Logger log = LoggerFactory.getLogger(Coin.class);

    public void getMarketChart(String coinId, Currency currency, Integer days, String interval) {
        
        StringBuilder uri = new StringBuilder(apiBase).append(coinId).append("/market_chart?vs_currency=")
                .append(currency).append("&days=").append(days).append("&interval=").append(interval);

        HttpClient client = HttpClient.newBuilder().build();

        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri.toString()))
                    .GET()
                    .build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Coin coin = objectMapper.readValue(response.body(), Coin.class);
                for(Double [] var : coin.getPrices())
                    for(Double k : var)
                        System.out.println(k);
            } catch (InterruptedException ex) {
                log.debug(ex.getMessage());
            } catch (IOException ex) {
                log.debug(ex.getMessage());
            }
        } catch (URISyntaxException ex) {
            log.debug(ex.getMessage());
        }

    }

}
