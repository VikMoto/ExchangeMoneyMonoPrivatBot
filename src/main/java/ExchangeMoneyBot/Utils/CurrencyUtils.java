package ExchangeMoneyBot.Utils;

import com.google.gson.Gson;
import module13.ExchangeMoneyBot.currencyGet.DTO.CurrencyRateResponse;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.List;

public class CurrencyUtils {
    @Nullable
    public static <T extends CurrencyRateResponse> List<T> convertResponseToList(Gson gson, String response, Type collectionType) {

       List<T> listCurr = gson.fromJson(response, collectionType);
        return listCurr;
    }
}
