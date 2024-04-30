package org.javaacademy.bitcoin_to_rub.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "integration.currency-bitcoin")
@Configuration
@Getter
@Setter
public class CoindeskProperty {
    private String url;
}
