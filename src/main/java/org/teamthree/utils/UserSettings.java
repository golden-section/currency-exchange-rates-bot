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
    private byte symbolsAfterDot;

    public UserSettings(Banks bank, HashSet<Currency> currency, byte symbolsAfterDot) {
        this.bank = bank;
        this.currency = currency;
        this.symbolsAfterDot = symbolsAfterDot;
    }

    public static UserSettings defaultSettings() {
        return new UserSettings(Banks.PRIVATBANK, new HashSet<>(List.of(Currency.USD)), (byte) 2);
    }
}