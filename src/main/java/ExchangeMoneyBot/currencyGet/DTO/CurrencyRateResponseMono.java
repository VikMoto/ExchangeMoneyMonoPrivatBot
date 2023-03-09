package ExchangeMoneyBot.currencyGet.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyRateResponseMono implements CurrencyRateResponse {
   //    {"currencyCodeA":840,"currencyCodeB":980,"date":1677103274,"rateBuy":36.65,"rateCross":0,"rateSell":37.4406}
//    private int currencyCodeA;
//    private int currencyCodeB;
//    private Date date;
//    private BigDecimal rateBuy;
//    private BigDecimal rateCross;
//    private BigDecimal rateSell;

 private int currencyCodeA;
 private int currencyCodeB;
 private long date;
 private BigDecimal rateBuy;
 private BigDecimal rateCross;
 private BigDecimal rateSell;

}
