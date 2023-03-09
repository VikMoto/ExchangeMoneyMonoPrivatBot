package ExchangeMoneyBot.UI;

import module13.ExchangeMoneyBot.currencyGet.DTO.CurrencyRateDto;

import java.util.List;
import java.util.stream.Collectors;

public class PrettyCurrencyResponseService {
    private static String RESPONSE_TEMPLATE = "For currency cr rate rt";

    public static String formatRateResponse(String command, List<CurrencyRateDto> currencyRateDto) {

        String result = "";
        System.out.println("currencyRateDto = " + currencyRateDto);

        command = command.toUpperCase();
// for debugging:
//        System.out.println("command : " + command + " list " + currencyRateDto);
        switch (command) {
            case "BUY" -> {
                result = currencyRateDto.stream()
                        .map(item -> {
                            return  RESPONSE_TEMPLATE.replace("cr", item.getCurrency().toString())
                                    .replace("rt",item.getBuyRate() + "\n");

                        })
                        .collect(Collectors.joining());
                System.out.println("Pretty result = " + result);
            }
            case "SELL" -> {
                result = currencyRateDto.stream()
                        .map(item -> {
                            return  RESPONSE_TEMPLATE.replace("cr", item.getCurrency().toString())
                                    .replace("rt", item.getSellRate() + "\n");

                        })
                        .collect(Collectors.joining());
                System.out.println("Pretty result = " + result);
            }
            default -> {
                result = "available commands only BUY and SEll";
            }
        }


        return result;
    }
}
