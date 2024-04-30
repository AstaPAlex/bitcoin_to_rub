package org.javaacademy.bitcoin_to_rub.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.bitcoin_to_rub.config.CoindeskProperty;
import org.javaacademy.bitcoin_to_rub.dto.CoindeskDtoRs;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CoindeskIntegrationService {
    private final CoindeskProperty coindeskProperty;
    private final RestTemplate restTemplate;

    public CoindeskDtoRs getCoindeskDtoRs() {
        RequestEntity<Void> request = RequestEntity
                .get(coindeskProperty.getUrl())
                .build();
        try {
            return restTemplate.exchange(request, CoindeskDtoRs.class).getBody();
        } catch (Throwable throwable) {
            throw new RuntimeException("No rates now!");
        }
    }

}
