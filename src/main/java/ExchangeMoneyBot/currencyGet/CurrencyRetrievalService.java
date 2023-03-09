package ExchangeMoneyBot.currencyGet;

import module13.ExchangeMoneyBot.currencyGet.DTO.CurrencyRateDto;

import java.util.List;

public interface CurrencyRetrievalService {
     List<CurrencyRateDto> getCurrencyRates(String url);
}
