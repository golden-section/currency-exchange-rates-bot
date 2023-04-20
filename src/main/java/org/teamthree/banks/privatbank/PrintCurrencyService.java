package org.teamthree.banks.privatbank;

import org.teamthree.banks.Currency;

import java.io.IOException;
import java.math.BigDecimal;

public class PrintCurrencyService {
    CurrencyService currencyService = new PrivatBankCurrencyService();
    public BigDecimal salesRates(Currency currency) {
        BigDecimal salesRate;
        try {
            salesRate = currencyService.getSalesRate(currency);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return salesRate;
    }
    public BigDecimal purchaseRates (Currency currency) {
        BigDecimal purchaseRate;
        try {
            purchaseRate = currencyService.getPurchaseRate(currency);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return purchaseRate;
    }
}
