package org.teamthree.utils;

import java.util.HashMap;
import java.util.HashSet;
import org.teamthree.banks.Currency;

public class UserUtils {
    private static final HashMap<Long, User> usersMap = new HashMap<>();
    public static User getUser(long chatId) {
        return usersMap.get(chatId);
    }

    public static void addUser(User user) {
        usersMap.put(user.getChatId(), user);
        System.out.println("usersMap = " + usersMap);
        System.out.println("usersMap.size() = " + usersMap.size());
    }

    public static boolean isUserExists(long chatId) {
        System.out.println("usersMap.containsKey(chatID) = " + usersMap.containsKey(chatId));
        return usersMap.containsKey(chatId);
    }

    public static void setCurrencyRefactor(long chatId, int symbolsAfterDot) {
        User user = getUser(chatId);
        UserSettings userSettings = user.getUserSettings();
        userSettings.setSymbolsAfterDot(symbolsAfterDot);
        usersMap.replace(chatId, user);
        System.out.println("usersMap = " + usersMap);
    }

    public static void setUserBank(long chatId, Banks bank) {
        User user = getUser(chatId);
        UserSettings userSettings = user.getUserSettings();
        userSettings.setBank(bank);
        usersMap.replace(chatId, user);
        System.out.println("usersMap = " + usersMap);
    }

    public static void setUserCurrency(long chatId, Currency currency) {
        User user = getUser(chatId);
        UserSettings userSettings = user.getUserSettings();
        HashSet<Currency> updatedCurrencySet = userSettings.getCurrency();
        updatedCurrencySet.add(currency);
        userSettings.setCurrency(updatedCurrencySet);
        usersMap.replace(chatId, user);
        System.out.println("usersMap = " + usersMap);
    }

    public static void unsetUserCurrency(long chatId, Currency currency) {
        User user = getUser(chatId);
        UserSettings userSettings = user.getUserSettings();
        HashSet<Currency> updatedCurrencySet = userSettings.getCurrency();
        updatedCurrencySet.remove(currency);
        userSettings.setCurrency(updatedCurrencySet);
        usersMap.replace(chatId, user);
        System.out.println("usersMap = " + usersMap);
    }

}
