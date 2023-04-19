package org.teamthree.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

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

    EditMessageText refactorCurrency(long chatId, int messageId, int value) {
        EditMessageText newMessage = new EditMessageText();
        newMessage.setChatId(chatId);
        newMessage.setMessageId(messageId);
        newMessage.setText("Оберіть кількість знаків після коми:");
        newMessage.setReplyMarkup(buttonSetup.refactorCurrencyButtons(message, value));

        return newMessage;
    }

    SendMessage defaultChooseBank(long chatId, int value) {
        message.setChatId(chatId);
        message.setText("Оберіть банк для використання:");
        buttonSetup.bankPickerButtons(message, value);
        return message;
    }

    EditMessageText chooseBank(long chatId, int messageId, int value) {
        EditMessageText newMessage = new EditMessageText();
        newMessage.setChatId(chatId);
        newMessage.setMessageId(messageId);
        newMessage.setText("Оберіть банк для використання:");
        newMessage.setReplyMarkup(buttonSetup.bankPickerButtons(message, value));

        return newMessage;
    }

    SendMessage defaultCurrencyPicker(long chatId, int value) {
        message.setChatId(chatId);
        message.setText("Оберіть валюту:");
        buttonSetup.currencyPickButton(message, value);
        return message;
    }

    EditMessageText currencyPicker(long chatId, int messageId, int value) {
        EditMessageText newMessage = new EditMessageText();
        newMessage.setChatId(chatId);
        newMessage.setMessageId(messageId);
        newMessage.setText("Оберіть валюту:");
        newMessage.setReplyMarkup(buttonSetup.currencyPickButton(message, value));

        return newMessage;
    }

    SendMessage timeAlert(long chatId) {
        message.setChatId(chatId);
        message.setText("Напишіть час time_(від 0-23) на який хочете щоб приходили повідомлення:");
        return message;
    }
}