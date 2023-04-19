package org.teamthree.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ButtonSetup {
    static int lastValueOfCurrencyPicker = 1;
    static int lastValueOfCurrencyRefactor = 2;
    static int lastValueOfBankPicker = 1;
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

    void settingMenuButtons(SendMessage message) {
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();

        InlineKeyboardButton currencyRefactorButton = new InlineKeyboardButton();
        currencyRefactorButton.setText("К-ть знаків після коми");
        currencyRefactorButton.setCallbackData(Buttons.BUTTON_CURRENCY_REFACTOR.name());

        InlineKeyboardButton bankChooserButton = new InlineKeyboardButton();
        bankChooserButton.setText("Банк");
        bankChooserButton.setCallbackData(Buttons.BUTTON_BANK_CHOOSER.name());

        InlineKeyboardButton checkCurrencyButton = new InlineKeyboardButton();
        checkCurrencyButton.setText("Валюти");
        checkCurrencyButton.setCallbackData(Buttons.BUTTON_CURRENCY_CHECKER.name());

        InlineKeyboardButton timeAlertButton = new InlineKeyboardButton();
        timeAlertButton.setText("Час сповіщення");
        timeAlertButton.setCallbackData(Buttons.BUTTON_ALERT_TIME.name());

        InlineKeyboardButton mainMenuButton = new InlineKeyboardButton();
        mainMenuButton.setText("Головне меню");
        mainMenuButton.setCallbackData(Buttons.BUTTON_INFO.name());

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

    InlineKeyboardMarkup refactorCurrencyButtons(SendMessage message, int valueCurrency) {
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        InlineKeyboardButton two_button = new InlineKeyboardButton();
        if (valueCurrency == 2) {
            two_button.setText("✓ 2");
            lastValueOfCurrencyRefactor = 2;
        } else {
            two_button.setText("2");
        }
        two_button.setCallbackData(Buttons.BUTTON_TWO_CURRENCY.name());

        InlineKeyboardButton three_button = new InlineKeyboardButton();
        if (valueCurrency == 3) {
            three_button.setText("✓ 3");
            lastValueOfCurrencyRefactor = 3;
        } else {
            three_button.setText("3");
        }
        three_button.setCallbackData(Buttons.BUTTON_THREE_CURRENCY.name());

        InlineKeyboardButton four_button = new InlineKeyboardButton();
        if (valueCurrency == 4) {
            four_button.setText("✓ 4");
            lastValueOfCurrencyRefactor = 4;
        } else {
            four_button.setText("4");
        }
        four_button.setCallbackData(Buttons.BUTTON_FOUR_CURRENCY.name());
        InlineKeyboardButton mainMenuButton = new InlineKeyboardButton();
        mainMenuButton.setText("Головне меню");
        mainMenuButton.setCallbackData(Buttons.BUTTON_INFO.name());

        keyboardButtonsRow1.add(two_button);
        keyboardButtonsRow2.add(three_button);
        keyboardButtonsRow3.add(four_button);
        keyboardButtonsRow4.add(mainMenuButton);
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
        return inlineKeyboardMarkup;
    }

    void startMenuButtons(SendMessage message) {
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        InlineKeyboardButton buttonInfo = new InlineKeyboardButton();
        buttonInfo.setText("Отримати інфу");
        buttonInfo.setCallbackData(Buttons.BUTTON_INFO.name());

        InlineKeyboardButton buttonSettings = new InlineKeyboardButton();
        buttonSettings.setText("Налаштування");
        buttonSettings.setCallbackData(Buttons.BUTTON_SETTINGS.name());

        keyboardButtonsRow1.add(buttonInfo);
        keyboardButtonsRow2.add(buttonSettings);
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    InlineKeyboardMarkup bankPickerButtons(SendMessage message, int valueCurrency) {
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        InlineKeyboardButton privatBankButton = new InlineKeyboardButton();
        if (valueCurrency == 1) {
            privatBankButton.setText("✓ Приватбанк");
            lastValueOfBankPicker = 1;
        } else {
            privatBankButton.setText("Приватбанк");
        }
        privatBankButton.setCallbackData(Buttons.BUTTON_PRIVAT_BANK.name());

        InlineKeyboardButton monoBankButton = new InlineKeyboardButton();
        if (valueCurrency == 2) {
            monoBankButton.setText("✓ Монобанк");
            lastValueOfBankPicker = 2;
        } else {
            monoBankButton.setText("Монобанк");
        }
        monoBankButton.setCallbackData(Buttons.BUTTON_MONO_BANK.name());

        InlineKeyboardButton NBUBankButton = new InlineKeyboardButton();
        if (valueCurrency == 3) {
            NBUBankButton.setText("✓ НБУ");
            lastValueOfBankPicker = 3;
        } else {
            NBUBankButton.setText("НБУ");
        }
        NBUBankButton.setCallbackData(Buttons.BUTTON_NBU_BANK.name());
        InlineKeyboardButton mainMenuButton = new InlineKeyboardButton();
        mainMenuButton.setText("Головне меню");
        mainMenuButton.setCallbackData(Buttons.BUTTON_INFO.name());

        keyboardButtonsRow1.add(privatBankButton);
        keyboardButtonsRow2.add(monoBankButton);
        keyboardButtonsRow3.add(NBUBankButton);
        keyboardButtonsRow4.add(mainMenuButton);
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
        return inlineKeyboardMarkup;
    }

    InlineKeyboardMarkup currencyPickButton(SendMessage message, int valueCurrency) {
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();

        InlineKeyboardButton USDButton = new InlineKeyboardButton();
        if (valueCurrency == 1) {
            USDButton.setText("✓ USD");
            lastValueOfCurrencyRefactor = 2;
        } else {
            USDButton.setText("USD");
        }
        USDButton.setCallbackData(Buttons.BUTTON_USD.name());

        InlineKeyboardButton EURButton = new InlineKeyboardButton();
        if (valueCurrency == 2) {
            EURButton.setText("✓ EUR");
            lastValueOfBankPicker = 2;
        } else {
            EURButton.setText("EUR");
        }
        EURButton.setCallbackData(Buttons.BUTTON_EUR.name());


        InlineKeyboardButton mainMenuButton = new InlineKeyboardButton();
        mainMenuButton.setText("Головне меню");
        mainMenuButton.setCallbackData(Buttons.BUTTON_INFO.name());

        keyboardButtonsRow1.add(USDButton);
        keyboardButtonsRow2.add(EURButton);
        keyboardButtonsRow3.add(mainMenuButton);
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
        return inlineKeyboardMarkup;
    }
}
