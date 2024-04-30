package org.javaacademy.bitcoin_to_rub.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "integration.free-currency-api")
@Getter
@Setter
public class FreeCurrencyProperty {
    private String token;
    private String tokenName;
    private String url;
    private String currency;
}
