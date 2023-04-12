package org.teamthree.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class BotCommands {
    SendMessage message = new SendMessage();

    SendMessage sendStartMenu(long chatId) {
        message.setChatId(chatId);
        message.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют.");
        startMenuButtons();
        return message;
    }

    SendMessage sendInfo(long chatId) {
        message.setChatId(chatId);
        message.setText("""
                Курс у Bank:  Currency
                Купівля:  moneyBuy
                Продаж: moneySell""");
        return message;
    }

    SendMessage sendSettings(long chatId) {
        message.setChatId(chatId);
        message.setText("Налаштування");
        settingMenuButtons();
        return message;
    }

    SendMessage refactorCurrency() {
        return null;
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

    private void startMenuButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        InlineKeyboardButton buttonInfo = new InlineKeyboardButton();
        buttonInfo.setText("Отримати інфу");
        buttonInfo.setCallbackData("button_info");

        InlineKeyboardButton buttonSettings = new InlineKeyboardButton();
        buttonSettings.setText("Налаштування");
        buttonSettings.setCallbackData("button_setting");

        keyboardButtonsRow1.add(buttonInfo);
        keyboardButtonsRow2.add(buttonSettings);
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    private void settingMenuButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();

        InlineKeyboardButton currencyRefactorButton = new InlineKeyboardButton();
        currencyRefactorButton.setText("К-ть знаків після коми");
        currencyRefactorButton.setCallbackData("currency_refactor_button");

        InlineKeyboardButton bankChooserButton = new InlineKeyboardButton();
        bankChooserButton.setText("Банк");
        bankChooserButton.setCallbackData("bank_chooser_button");

        InlineKeyboardButton checkCurrencyButton = new InlineKeyboardButton();
        checkCurrencyButton.setText("Валюти");
        checkCurrencyButton.setCallbackData("check_currency_button");

        InlineKeyboardButton timeAlertButton = new InlineKeyboardButton();
        timeAlertButton.setText("Час сповіщення");
        timeAlertButton.setCallbackData("time_alert_button");

        keyboardButtonsRow1.add(currencyRefactorButton);
        keyboardButtonsRow2.add(bankChooserButton);
        keyboardButtonsRow3.add(checkCurrencyButton);
        keyboardButtonsRow4.add(timeAlertButton);

        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }
}
