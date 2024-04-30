package org.javaacademy.bitcoin_to_rub.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.bitcoin_to_rub.dto.BitcoinToCurrencyDtoRs;
import org.javaacademy.bitcoin_to_rub.dto.PageDto;
import org.javaacademy.bitcoin_to_rub.service.BitcoinToCurrencyService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/rate")
@RequiredArgsConstructor
public class BitcoinToRubController {
    private final BitcoinToCurrencyService bitcoinToCurrencyService;

    @GetMapping("/now")
    public BitcoinToCurrencyDtoRs getBitcoinToCurrency() {
        return bitcoinToCurrencyService.getBitcoinToCurrency();
    }

    @GetMapping("/average")
    public BigDecimal getAverage() {
        return bitcoinToCurrencyService.getAverage();
    }

    @GetMapping("/history")
    public PageDto<List<BitcoinToCurrencyDtoRs>> getHistory(@RequestParam Integer startElement,
                           @RequestParam Integer pageSize) {
        return bitcoinToCurrencyService.getHistory(startElement, pageSize);
    }

}
