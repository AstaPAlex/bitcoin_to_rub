package org.javaacademy.bitcoin_to_rub.repository;

import org.javaacademy.bitcoin_to_rub.entity.BitcoinToCurrency;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class BitcoinToCurrencyRepository {
    private final List<BitcoinToCurrency> bitcoinToCurrencyList = new ArrayList<>();

    public void add(BitcoinToCurrency bitcoinToCurrency) {
        bitcoinToCurrencyList.add(bitcoinToCurrency);
    }

    public List<BitcoinToCurrency> getAll() {
        return bitcoinToCurrencyList;
    }

    public BigDecimal getAverage() {
        BigDecimal bigDecimal = bitcoinToCurrencyList.stream()
                .map(BitcoinToCurrency::getPriceBitcoin)
                .reduce(BigDecimal::add)
                .orElseThrow(() -> new RuntimeException("No rates now!"));
        return bigDecimal.divide(BigDecimal.valueOf(bitcoinToCurrencyList.size()), 2, RoundingMode.CEILING);
    }
}
