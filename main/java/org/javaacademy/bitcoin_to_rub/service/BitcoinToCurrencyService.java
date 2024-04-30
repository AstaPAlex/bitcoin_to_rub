package org.javaacademy.bitcoin_to_rub.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.bitcoin_to_rub.dto.BitcoinToCurrencyDtoRs;
import org.javaacademy.bitcoin_to_rub.dto.CoindeskDtoRs;
import org.javaacademy.bitcoin_to_rub.dto.FreeCurrencyDtoRs;
import org.javaacademy.bitcoin_to_rub.dto.PageDto;
import org.javaacademy.bitcoin_to_rub.entity.BitcoinToCurrency;
import org.javaacademy.bitcoin_to_rub.repository.BitcoinToCurrencyRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BitcoinToCurrencyService {
    private final FreeCurrencyIntegrationService currencyIntegrationService;
    private final CoindeskIntegrationService coindeskIntegrationService;
    private final BitcoinToCurrencyRepository bitcoinToCurrencyRepository;

    public BitcoinToCurrencyDtoRs getBitcoinToCurrency() {
        BitcoinToCurrency bitcoinToCurrency = convertToEntity(
                currencyIntegrationService.getFreeCurrencyDtoRs(),
                coindeskIntegrationService.getCoindeskDtoRs());
        bitcoinToCurrencyRepository.add(bitcoinToCurrency);
        return convertToDtoRs(bitcoinToCurrency);
    }

    private BitcoinToCurrencyDtoRs convertToDtoRs(BitcoinToCurrency bitcoinToCurrency) {
        return new BitcoinToCurrencyDtoRs(
                bitcoinToCurrency.getCurrency(),
                bitcoinToCurrency.getRateCurrency(),
                bitcoinToCurrency.getPriceBitcoin(),
                bitcoinToCurrency.getUpdatedTimeDate()
        );
    }

    private BitcoinToCurrency convertToEntity(FreeCurrencyDtoRs currencyRateDtoRs,
                                                    CoindeskDtoRs coindeskDtoRs) {
        String currency = currencyRateDtoRs.getData().keySet().stream().findFirst().orElseThrow();
        BigDecimal priceToUsd = coindeskDtoRs.getBpi().getUsd().getPriceToUsd();
        BigDecimal rateCurrency = currencyRateDtoRs.getData().get(currency);
        return new BitcoinToCurrency(
                currency,
                rateCurrency.setScale(2, RoundingMode.CEILING),
                rateCurrency.multiply(priceToUsd).setScale(2, RoundingMode.CEILING),
                LocalDateTime.now()
        );
    }

    public BigDecimal getAverage() {
        return bitcoinToCurrencyRepository.getAverage();
    }

    public PageDto<List<BitcoinToCurrencyDtoRs>> getHistory(Integer startElement, Integer pageSize) {
        List<BitcoinToCurrencyDtoRs> bitcoinToCurrencyDtoRs = getListBitcoinToCurrencyDtoRs(startElement, pageSize);
        return new PageDto<>(
                bitcoinToCurrencyDtoRs.size(),
                bitcoinToCurrencyRepository.getAll().size(),
                startElement,
                startElement + bitcoinToCurrencyDtoRs.size() - 1,
                bitcoinToCurrencyDtoRs);
    }

    private List<BitcoinToCurrencyDtoRs> getListBitcoinToCurrencyDtoRs(Integer startElement, Integer pageSize) {
        return bitcoinToCurrencyRepository.getAll().stream()
                .skip(startElement)
                .limit(pageSize)
                .map(this::convertToDtoRs)
                .toList();
    }
}
