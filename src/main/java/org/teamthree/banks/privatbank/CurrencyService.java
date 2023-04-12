package org.teamthree.banks.privatbank;

import java.io.IOException;
import java.math.BigDecimal;

public interface CurrencyService {
    BigDecimal getPurchaseRate(Currency currency) throws IOException;

    BigDecimal getSalesRate(Currency currency) throws IOException;
}
