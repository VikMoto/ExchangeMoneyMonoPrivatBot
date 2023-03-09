package ExchangeMoneyBot.currencyGet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import module13.ExchangeMoneyBot.Utils.CurrencyUtils;
import module13.ExchangeMoneyBot.currencyGet.DTO.Currency;
import module13.ExchangeMoneyBot.currencyGet.DTO.CurrencyRateDto;
import module13.ExchangeMoneyBot.currencyGet.DTO.CurrencyRateResponseMono;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyRetrievalMonoService implements CurrencyRetrievalService {
    @Override
    public List<CurrencyRateDto> getCurrencyRates(String url) {
        String response;
        try {
            response = Jsoup
                    .connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
//            System.out.println("response = " + response);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can`t to connect Bank API : " + url);
        }

        Gson gson = new Gson();
        List<CurrencyRateResponseMono> currencyItems = new ArrayList<>();
        Type collectionType = new TypeToken<List<CurrencyRateResponseMono>>(){}.getType();
        String result = "";

        List<CurrencyRateResponseMono> currencyRateResponsesMono =
                CurrencyUtils.convertResponseToList(gson, response, collectionType);

//        List<CurrencyRateDto> currencyRateDtoMono = currencyRateResponsesMono.stream()
//                .peek(System.out::print)
//                .map(dto -> new CurrencyRateDto(dto.g))
//                .collect(Collectors.toList());

        final List<CurrencyRateDto> currencyRateDto = currencyRateResponsesMono.stream()
                .map(dto -> new CurrencyRateDto(
                        Currency.getCurrencyByCode(dto.getCurrencyCodeA()),
                        dto.getRateBuy(),
                        dto.getRateSell()))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("currencyRateDto Mono= " + currencyRateDto);


        return currencyRateDto;
    }

//    private static List<CurrencyRateDto> convertResponseToList(Gson gson, String response, Type collectionType) {
//
//        List<CurrencyRateDto> listCurr = gson.fromJson(response, collectionType);
//        return listCurr;
//    }

}
