package org.javaacademy.bitcoin_to_rub.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BitcoinToCurrencyDtoRs {
    private final String currency;
    private final BigDecimal rateCurrency;
    private final BigDecimal priceBitcoin;
    private final LocalDateTime updatedTimeDate;
}
