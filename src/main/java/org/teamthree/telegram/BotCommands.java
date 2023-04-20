package org.teamthree.telegram;

import org.teamthree.banks.monobank.CurrencyItem;
import org.teamthree.banks.monobank.MonobankCurrencyRateService;
import org.teamthree.banks.nbu.NbuApiIntegration;
import org.teamthree.banks.privatbank.PrintCurrencyService;
import org.teamthree.utils.Banks;
import org.teamthree.utils.UserSettings;
import org.teamthree.utils.UserUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.teamthree.banks.Currency;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;

public class BotCommands {
    ButtonSetup buttonSetup = new ButtonSetup();
    SendMessage message = new SendMessage();

    SendMessage sendStartMenu(long chatId) {
        message.setChatId(chatId);
        message.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют.");
        buttonSetup.startMenuButtons(message);
        return message;
    }

    SendMessage sendInfo(long chatId) {
        UserSettings userSettings = UserUtils.getUser(chatId).getUserSettings();
        Banks bank = userSettings.getBank();
        HashSet<Currency> currencies = userSettings.getCurrency();
        int symbolsAfterDot = userSettings.getSymbolsAfterDot();
        int notificationTime = userSettings.getNotificationTime();
        StringBuilder result = new StringBuilder();

        result.append("Курс в ").append(bank.getBankName()).append("\n");

        if(currencies.size() == 0) {
            result = new StringBuilder();
            result.append("Валюта не вибрана").append("\n");
        }

        if(bank.equals(Banks.PRIVATBANK)) {
            PrintCurrencyService printCurrencyService = new PrintCurrencyService();
            for(Currency currency : currencies) {
                BigDecimal sell = printCurrencyService.salesRates(currency).setScale(symbolsAfterDot, RoundingMode.HALF_UP);
                BigDecimal buy = printCurrencyService.purchaseRates(currency).setScale(symbolsAfterDot, RoundingMode.HALF_UP);
                result.append("Купівля: ").append(currency.getCurrencyName()).append(": ").append(sell).append("\n");
                result.append("Продаж: ").append(currency.getCurrencyName()).append(": ").append(buy).append("\n");
            }
        }

        if(bank.equals(Banks.NBU)) {
            for(Currency currency : currencies) {
                BigDecimal rate = NbuApiIntegration.getCurrentRate(currency).setScale(symbolsAfterDot, RoundingMode.HALF_UP);
                result.append("Купівля/Продаж: ").append(currency.getCurrencyName()).append(": ").append(rate).append("\n");
            }
        }

        if(bank.equals(Banks.MONOBANK)) {
            MonobankCurrencyRateService mono = new MonobankCurrencyRateService();
            List<CurrencyItem> rates = mono.getRates();
            for(Currency currency : currencies) {
                BigDecimal sell = mono.rateSell(rates, currency).setScale(symbolsAfterDot, RoundingMode.HALF_UP);
                BigDecimal buy = mono.rateBuy(rates, currency).setScale(symbolsAfterDot, RoundingMode.HALF_UP);
                result.append("Купівля: ").append(currency.getCurrencyName()).append(": ").append(sell).append("\n");
                result.append("Продаж: ").append(currency.getCurrencyName()).append(": ").append(buy).append("\n");
            }
        }

        message.setChatId(chatId);
        message.setText(result.toString());
        buttonSetup.startMenuButtons(message);
        return message;
    }

    SendMessage sendSettings(long chatId) {
        message.setChatId(chatId);
        message.setText("Налаштування:");
        buttonSetup.settingMenuButtons(message);
        return message;
    }

    SendMessage defaultRefactorCurrency(long chatId, int value) {
        message.setChatId(chatId);
        message.setText("Оберіть кількість знаків після коми:");
        buttonSetup.refactorCurrencyButtons(message, value);
        return message;
    }

    EditMessageText refactorCurrency(long chatId, int messageId, int value) {
        EditMessageText newMessage = new EditMessageText();
        newMessage.setChatId(chatId);
        newMessage.setMessageId(messageId);
        newMessage.setText("Оберіть кількість знаків після коми:");
        newMessage.setReplyMarkup(buttonSetup.refactorCurrencyButtons(message, value));

        return newMessage;
    }

    SendMessage defaultChooseBank(long chatId, Banks bank) {
        message.setChatId(chatId);
        message.setText("Оберіть банк для використання:");
        buttonSetup.bankPickerButtons(message, bank);
        return message;
    }

    EditMessageText chooseBank(long chatId, int messageId, Banks bank) {
        EditMessageText newMessage = new EditMessageText();
        newMessage.setChatId(chatId);
        newMessage.setMessageId(messageId);
        newMessage.setText("Оберіть банк для використання:");
        newMessage.setReplyMarkup(buttonSetup.bankPickerButtons(message, bank));

        return newMessage;
    }

    SendMessage defaultCurrencyPicker(long chatId, HashSet<Currency> currency) {
        message.setChatId(chatId);
        message.setText("Оберіть валюту:");
        buttonSetup.currencyPickButton(message, currency);
        return message;
    }

    EditMessageText currencyPicker(long chatId, int messageId, HashSet<Currency> currency) {
        EditMessageText newMessage = new EditMessageText();
        newMessage.setChatId(chatId);
        newMessage.setMessageId(messageId);
        newMessage.setText("Оберіть валюту:");
        newMessage.setReplyMarkup(buttonSetup.currencyPickButton(message, currency));

        return newMessage;
    }

    SendMessage timeAlert(long chatId) {
        message.setChatId(chatId);
        message.setText("Напишіть час time_(від 0-23) на який хочете щоб приходили повідомлення:");
        return message;
    }
}