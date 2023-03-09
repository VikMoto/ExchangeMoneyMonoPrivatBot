package ExchangeMoneyBot.currencyGet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import module13.ExchangeMoneyBot.Utils.CurrencyUtils;
import module13.ExchangeMoneyBot.currencyGet.DTO.CurrencyRateDto;
import module13.ExchangeMoneyBot.currencyGet.DTO.CurrencyRateResponsePrivat;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyRetrievalPrivatService implements CurrencyRetrievalService {

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
        List<CurrencyRateResponsePrivat> currencyItems = new ArrayList<>();
        Type collectionType = new TypeToken<List<CurrencyRateResponsePrivat>>(){}.getType();
        String result = "";
        List<CurrencyRateResponsePrivat> listCurrPrivat =
                CurrencyUtils.convertResponseToList(gson, response, collectionType);

        List<CurrencyRateDto> currencyRateDto = listCurrPrivat.stream()
                .map(dto -> new CurrencyRateDto(dto.getCcy(), dto.getBuy(), dto.getSale()))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());


        System.out.println("currencyRateDto Privat = " + currencyRateDto);

        return currencyRateDto;
    }

}
