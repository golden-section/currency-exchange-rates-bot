package org.teamthree.banks.privatbank;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.teamthree.banks.Currency;
import org.teamthree.banks.CurrencyItem;
import org.teamthree.banks.CurrencyRateService;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PrivatBankCurrencyService implements CurrencyRateService {
    private final String url = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";
    private final String urlGBP = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=4";
    @SneakyThrows
    @Override
    public BigDecimal getRateBuy(Currency currency) {
        String response = Jsoup
                .connect(urlGBP)
                .ignoreContentType(true)
                .get()
                .body()
                .text();
        String response1 = Jsoup
                .connect(url)
                .ignoreContentType(true)
                .get()
                .body()
                .text();
        Type typeToken = TypeToken
                .getParameterized(List.class, CurrencyItem.class)
                .getType();
        Gson gson = new Gson();
        List<CurrencyItem> currencyItems = gson.fromJson(response, typeToken);
        Type listType = new TypeToken<List<CurrencyItem>>() {}.getType();
        List<CurrencyItem> currencyItems2 = gson.fromJson(response1, listType);
        currencyItems.addAll(currencyItems2);
        List<CurrencyItem> filteredCurrencyItems = new ArrayList<>();
        for (CurrencyItem currencyItem : currencyItems) {
            if (currencyItem.getCcy() != null &&(currencyItem.getCcy().equals(Currency.USD) ||
                    currencyItem.getCcy().equals(Currency.EUR) ||
                    currencyItem.getCcy().equals(Currency.GBP))) {
                filteredCurrencyItems.add(currencyItem);
            }
        }

        return filteredCurrencyItems
                .stream()
                .filter(it->it.getCcy().equals(currency))
                .filter(it -> it.getBase_ccy().equals(Currency.UAH))
                .map(CurrencyItem::getBuy)
                .findFirst()
                .orElseThrow();
    }
    @SneakyThrows
    @Override
    public BigDecimal getRateSell(Currency currency) {
        String response = Jsoup
                .connect(urlGBP)
                .ignoreContentType(true)
                .get()
                .body()
                .text();
        String response1 = Jsoup
                .connect(url)
                .ignoreContentType(true)
                .get()
                .body()
                .text();
        Type typeToken = TypeToken
                .getParameterized(List.class, CurrencyItem.class)
                .getType();
        Gson gson = new Gson();
        List<CurrencyItem> currencyItems = gson.fromJson(response, typeToken);
        Type listType = new TypeToken<List<CurrencyItem>>() {}.getType();
        List<CurrencyItem> currencyItems2 = gson.fromJson(response1, listType);
        currencyItems.addAll(currencyItems2);
        List<CurrencyItem> filteredCurrencyItems = new ArrayList<>();
        for (CurrencyItem currencyItem : currencyItems) {
            if (currencyItem.getCcy() != null &&(currencyItem.getCcy().equals(Currency.USD) ||
                    currencyItem.getCcy().equals(Currency.EUR) ||
                    currencyItem.getCcy().equals(Currency.GBP))) {
                filteredCurrencyItems.add(currencyItem);
            }
        }

        return filteredCurrencyItems
                .stream()
                .filter(it->it.getCcy().equals(currency))
                .filter(it -> it.getBase_ccy().equals(Currency.UAH))
                .map(CurrencyItem::getSale)
                .findFirst()
                .orElseThrow();
    }
}