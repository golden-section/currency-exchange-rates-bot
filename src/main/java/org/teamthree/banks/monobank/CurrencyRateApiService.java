package org.teamthree.banks.monobank;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyRateApiService {

    List<CurrencyItem> getRates();

    BigDecimal rateBuy(List<CurrencyItem> currencyItems, Currency currency);

    BigDecimal rateSell(List<CurrencyItem> currencyItems, Currency currency);
}
