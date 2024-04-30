package org.javaacademy.bitcoin_to_rub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CoindeskDtoRs {
    private Bpi bpi;

    @Data
    public static class Bpi {
        @JsonProperty("USD")
        private Usd usd;

        @Data
        public static class Usd {
            @JsonProperty("rate_float")
            private BigDecimal priceToUsd;
        }
    }

}


