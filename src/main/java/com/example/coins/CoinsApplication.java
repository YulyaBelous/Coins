package com.example.coins;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;

@SpringBootApplication
public class CoinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoinsApplication.class, args);
        CoinService coinService = new CoinService();
        coinService.getMarketChart("bitcoin", Currency.getInstance("EUR"), 0, "daily");
    }

}
