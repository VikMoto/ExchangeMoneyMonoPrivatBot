package ExchangeMoneyBot.currencyGet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateDto implements Comparable<CurrencyRateDto> {
//    private Currency baseCurrency;
    private Currency currency;
    private BigDecimal buyRate;
    private BigDecimal sellRate;


    @Override
    public int compareTo(CurrencyRateDto other) {
        return this.currency.compareTo(other.currency);
    }
}
