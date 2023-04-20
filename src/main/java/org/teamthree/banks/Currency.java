package org.teamthree.banks;

public enum Currency {
    UAH("Гривня"),
    USD("Долар США"),
    EUR("Євро"),
    GBP("Фунт стерлінгів");

    private final String currencyName;

    Currency(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyName() {
        return currencyName;
    }
}
