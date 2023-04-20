package org.teamthree.banks.monobank;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.teamthree.banks.Currency;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static org.teamthree.banks.Currency.*;

public class MonobankCurrencyRateService implements CurrencyRateApiService {

    String url = "https://api.monobank.ua/bank/currency";

    public List<CurrencyItem> getRates() {

        String response;
        try {
            response = Jsoup
                    .connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return convertResponse(response);


    }

    @Override
    public BigDecimal rateBuy(List<CurrencyItem> currencyItems, Currency currency) {

        return currencyItems.stream()
                .filter(item -> item.getOriginal() == currency)
                .filter(item -> item.getToConvert() == UAH)
                .map(CurrencyItem::getRateBuy)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public BigDecimal rateSell(List<CurrencyItem> currencyItems, Currency currency) {
        return currencyItems.stream()
                .filter(item -> item.getOriginal() == currency)
                .filter(item -> item.getToConvert() == UAH)
                .map(CurrencyItem::getRateSell)
                .findFirst()
                .orElseThrow(ArithmeticException::new);

    }

    private List<CurrencyItem> convertResponse(String response) {
        Type typeToken = TypeToken
                .getParameterized(List.class, CurrencyItem.class)
                .getType();
        List<CurrencyItem> currencyItems = new Gson().fromJson(response, typeToken);

        Map<Integer, Currency> currencyMap = Map.of(
                980, UAH,
                840, USD,
                978, EUR,
                826, GBP
        );
        return currencyItems.stream()
                .peek(item -> {
                    if (currencyMap.containsKey(item.getCurrencyCodeA())) {
                        item.setOriginal(currencyMap.get(item.getCurrencyCodeA()));
                    }
                })
                .peek(item -> {
                    if (currencyMap.containsKey(item.getCurrencyCodeB())) {
                        item.setToConvert(currencyMap.get(item.getCurrencyCodeB()));
                    }
                })
                .filter(item -> item.getOriginal() != null)
                .filter(item -> item.getToConvert() == UAH)
                .collect(Collectors.toList());
    }
}