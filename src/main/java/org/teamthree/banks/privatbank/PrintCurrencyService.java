package org.teamthree.banks.privatbank;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
    public String convert(BigDecimal salesRate, BigDecimal purchaseRate, Currency currency){
        String templateOfMessage = "Purchase rate UAH => ${currency} = ${purchaseRate}\nSales rate ${currency} => UAH = ${salesRate}";
        BigDecimal roundedRate = salesRate.setScale(2, RoundingMode.HALF_UP);
        BigDecimal roundedRate2 = purchaseRate.setScale(2, RoundingMode.HALF_UP);
        return templateOfMessage
                .replace("${currency}", currency.name())
                .replace("${salesRate}",roundedRate +"")
                .replace("${purchaseRate}", roundedRate2 +"");
    }
}
