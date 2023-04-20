package org.teamthree.banks.nbu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.teamthree.banks.Currency;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

public class NbuApiIntegration {

    private static final String NBU_API_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public static BigDecimal getCurrentRate(Currency currency) {
        try {
            String json = Jsoup.connect(NBU_API_URL).ignoreContentType(true).execute().body();

            Gson gson = new Gson();
            Type listType = new TypeToken<List<CurrencyRate>>(){}.getType();
            List<CurrencyRate> currencyRates = gson.fromJson(json, listType);

            for (CurrencyRate rate : currencyRates) {
                if (rate.getCc().equalsIgnoreCase(currency.name())) {
                    return rate.getRate();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}