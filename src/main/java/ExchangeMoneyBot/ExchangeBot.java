package ExchangeMoneyBot;

import module13.ExchangeMoneyBot.Telegram.CurrencyCompareBot;
import module13.ExchangeMoneyBot.currencyGet.CurrencyRetrievalMonoService;
import module13.ExchangeMoneyBot.currencyGet.CurrencyRetrievalPrivatService;
import module13.ExchangeMoneyBot.currencyGet.CurrencyRetrievalService;
import module13.ExchangeMoneyBot.currencyGet.DTO.CurrencyRateDto;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

public class ExchangeBot {
    // should to show actual exchange rate
    // on request sale - sale rate hrn
    // on request buy - rate buy hrn
    // get exchange rate from service CurrencyRetrievalPrivatService
    //parsing data Gson
    private static final String URL_MONO = "https://api.monobank.ua/bank/currency";

    public static void main(String[] args) {




        String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
        CurrencyRetrievalService currencyRetrievalMonoService = new CurrencyRetrievalMonoService();
        List<CurrencyRateDto> currencyRatesMono = currencyRetrievalMonoService.getCurrencyRates(URL_MONO);
        System.out.println("currencyRatesMono = " + currencyRatesMono);

        CurrencyRetrievalPrivatService currencyRetrievalPrivatService =
                new CurrencyRetrievalPrivatService();
        System.out.println("currencyRetrievalPrivatService.getCurrencyRates(url, Currency.USD) = "
                + currencyRetrievalPrivatService.getCurrencyRates(url));

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new CurrencyCompareBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
