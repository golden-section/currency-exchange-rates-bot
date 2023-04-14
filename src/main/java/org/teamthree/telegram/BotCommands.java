package org.teamthree.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import static java.lang.Math.toIntExact;

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
        message.setChatId(chatId);
        message.setText("""
                Курс у [Bank]: [Currency]
                Купівля: [moneyBuy]
                Продаж: [moneySell]""");
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

    EditMessageText refactorCurrency(long chatId, long messageId, int value) {
        EditMessageText newMessage = new EditMessageText();
        newMessage.setChatId(chatId);
        newMessage.setMessageId(toIntExact(messageId));
        newMessage.setText("Оберіть кількість знаків після коми:");
        newMessage.setReplyMarkup(buttonSetup.refactorCurrencyButtons(message, value));

        return newMessage;
    }

    SendMessage chooseBank() {
        return null;
    }

    SendMessage currencyChecker() {
        return null;
    }

    SendMessage timeAlert() {
        return null;
    }
}