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
        message.setText("Курс у [Bank]: [Currency]\n" +
                "Купівля: [moneyBuy]\n" +
                "Продаж: [moneySell]");
        startMenuButtons();
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
        buttonInfo.setCallbackData(Buttons.BUTTON_INFO.getText());

        InlineKeyboardButton buttonSettings = new InlineKeyboardButton();
        buttonSettings.setText("Налаштування");
        buttonSettings.setCallbackData(Buttons.BUTTON_SETTINGS.getText());

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
        List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();

        InlineKeyboardButton currencyRefactorButton = new InlineKeyboardButton();
        currencyRefactorButton.setText("К-ть знаків після коми");
        currencyRefactorButton.setCallbackData(Buttons.BUTTON_CURRENCY_REFACTOR.getText());

        InlineKeyboardButton bankChooserButton = new InlineKeyboardButton();
        bankChooserButton.setText("Банк");
        bankChooserButton.setCallbackData(Buttons.BUTTON_BANK_CHOOSER.getText());

        InlineKeyboardButton checkCurrencyButton = new InlineKeyboardButton();
        checkCurrencyButton.setText("Валюти");
        checkCurrencyButton.setCallbackData(Buttons.BUTTON_CURRENCY_CHECKER.getText());

        InlineKeyboardButton timeAlertButton = new InlineKeyboardButton();
        timeAlertButton.setText("Час сповіщення");
        timeAlertButton.setCallbackData(Buttons.BUTTON_ALERT_TIME.getText());

        InlineKeyboardButton mainMenuButton = new InlineKeyboardButton();
        mainMenuButton.setText("Головне меню");
        mainMenuButton.setCallbackData(Buttons.BUTTON_INFO.getText());


        keyboardButtonsRow1.add(currencyRefactorButton);
        keyboardButtonsRow2.add(bankChooserButton);
        keyboardButtonsRow3.add(checkCurrencyButton);
        keyboardButtonsRow4.add(timeAlertButton);
        keyboardButtonsRow5.add(mainMenuButton);

        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        rowList.add(keyboardButtonsRow5);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }
}
