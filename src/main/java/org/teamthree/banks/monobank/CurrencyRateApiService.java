package org.teamthree.banks.monobank;

import java.math.BigDecimal;
import java.util.List;
import org.teamthree.banks.Currency;

public interface CurrencyRateApiService {

    List<CurrencyItem> getRates();

    BigDecimal rateBuy(List<CurrencyItem> currencyItems, Currency currency);

    BigDecimal rateSell(List<CurrencyItem> currencyItems, Currency currency);
}
