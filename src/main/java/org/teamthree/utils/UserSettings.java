package org.teamthree.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.HashSet;
import java.util.List;
import org.teamthree.banks.Currency;

@Getter
@Setter
@ToString
public class UserSettings {

    private Banks bank;
    private HashSet<Currency> currency;
    private int symbolsAfterDot;
    private int notificationTime;

    public UserSettings(Banks bank, HashSet<Currency> currency, int symbolsAfterDot, int notificationTime) {
        this.bank = bank;
        this.currency = currency;
        this.symbolsAfterDot = symbolsAfterDot;
        this.notificationTime = notificationTime;
    }

    public static UserSettings defaultSettings() {
        return new UserSettings(Banks.PRIVATBANK, new HashSet<>(List.of(Currency.USD)), 2, -1);
    }
}