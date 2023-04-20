package org.teamthree.banks.nbu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.teamthree.banks.Currency;
import org.teamthree.banks.CurrencyItem;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

public class NbuApiIntegration {

    private static final String NBU_API_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @SneakyThrows
    public static BigDecimal getCurrentRate(Currency currency) {

            String json = Jsoup.connect(NBU_API_URL).ignoreContentType(true).execute().body();

            Gson gson = new Gson();
            Type listType = new TypeToken<List<CurrencyItem>>(){}.getType();
            List<CurrencyItem> currencyRates = gson.fromJson(json, listType);

            return currencyRates.stream()
                    .filter(it->it.getCc().equals(currency.name()))
                    .map(CurrencyItem::getRate)
                    .findFirst()
                    .orElseThrow();
    }
}