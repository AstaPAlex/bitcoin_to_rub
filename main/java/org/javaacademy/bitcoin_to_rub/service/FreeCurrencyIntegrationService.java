package org.javaacademy.bitcoin_to_rub.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.bitcoin_to_rub.config.FreeCurrencyProperty;
import org.javaacademy.bitcoin_to_rub.dto.FreeCurrencyDtoRs;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class FreeCurrencyIntegrationService {
    private final FreeCurrencyProperty properties;
    private final RestTemplate restTemplate;

    public FreeCurrencyDtoRs getFreeCurrencyDtoRs() {
        RequestEntity<Void> request = RequestEntity
                .get(properties.getUrl().formatted(properties.getCurrency()))
                .header(properties.getTokenName(), properties.getToken())
                .build();
        try {
            return restTemplate.exchange(request, FreeCurrencyDtoRs.class).getBody();
        } catch (Throwable throwable) {
            throw new RuntimeException("No rates now!");
        }
    }

}
