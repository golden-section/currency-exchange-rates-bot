package org.teamthree.utils;

public enum Banks {
    PRIVATBANK("Приватбанк"),
    MONOBANK("Монобанк"),
    NBU("НБУ");

    private final String bankName;

    Banks(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }
}