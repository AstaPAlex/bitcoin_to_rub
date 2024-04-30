package org.javaacademy.bitcoin_to_rub.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class FreeCurrencyDtoRs {
    private Map<String, BigDecimal> data;
}
