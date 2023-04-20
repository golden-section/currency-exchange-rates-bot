package org.teamthree.banks;

import java.math.BigDecimal;

public interface CurrencyRateService {
    BigDecimal getRateBuy(Currency currency);

    BigDecimal getRateSell(Currency currency);
}
