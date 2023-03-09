package ExchangeMoneyBot.Telegram;

import module13.ExchangeMoneyBot.UI.PrettyCurrencyResponseService;
import module13.ExchangeMoneyBot.currencyGet.CurrencyRetrievalMonoService;
import module13.ExchangeMoneyBot.currencyGet.CurrencyRetrievalPrivatService;
import module13.ExchangeMoneyBot.currencyGet.CurrencyRetrievalService;
import module13.ExchangeMoneyBot.currencyGet.DTO.CurrencyRateDto;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class CurrencyCompareBot extends TelegramLongPollingBot {
        private static final String URL_PRIVAT = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
        private static final String URL_MONO = "https://api.monobank.ua/bank/currency";
//        List<String> urls = List.of(url,url2);
        CurrencyRetrievalService currencyRetrievalServicePrivat = new CurrencyRetrievalPrivatService();
        CurrencyRetrievalService currencyRetrievalServiceMono = new CurrencyRetrievalMonoService();

        @Override
        public String getBotUsername() {
            // TODO
            return BotConstants.BOT_NAME;
        }

        @Override
        public String getBotToken() {
            // TODO
            return BotConstants.BOT_TOKEN;
        }

        @Override
        public void onUpdateReceived(Update update) {
                // We check if the update has a message and the message has text
                if (update.hasMessage() && update.getMessage().hasText()) {
                        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
                        message.setChatId(update.getMessage().getChatId().toString());
                        final String command = update.getMessage().getText();


                        List<CurrencyRateDto> currencyRatesPrivat = currencyRetrievalServicePrivat.getCurrencyRates(URL_PRIVAT);
                        List<CurrencyRateDto> currencyRatesMono = currencyRetrievalServiceMono.getCurrencyRates(URL_MONO);

                        message.setText("PrivatBank:\n" + PrettyCurrencyResponseService
                                .formatRateResponse(command, currencyRatesPrivat) + "\nMonoBank:\n" +
                                PrettyCurrencyResponseService
                                        .formatRateResponse(command, currencyRatesMono));

                        try {
                                execute(message); // Call method to send the message
                        } catch (TelegramApiException e) {
                                e.printStackTrace();
                        }
                }
        }


}
