package org.javaacademy.bitcoin_to_rub.entity;

import lombok.NonNull;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class BitcoinToCurrency {
    @NonNull
    String currency;
    @NonNull
    BigDecimal rateCurrency;
    @NonNull
    BigDecimal priceBitcoin;
    @NonNull
    LocalDateTime updatedTimeDate;
}
