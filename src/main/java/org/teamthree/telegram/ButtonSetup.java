package org.teamthree.telegram;

import org.teamthree.banks.Currency;
import org.teamthree.utils.Banks;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ButtonSetup {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

    void settingMenuButtons(SendMessage message) {
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();

        InlineKeyboardButton currencyRefactorButton = new InlineKeyboardButton();
        currencyRefactorButton.setText("Кількість знаків після коми");
        currencyRefactorButton.setCallbackData(Buttons.BUTTON_CURRENCY_REFACTOR.name());

        InlineKeyboardButton bankChooserButton = new InlineKeyboardButton();
        bankChooserButton.setText("Банк");
        bankChooserButton.setCallbackData(Buttons.BUTTON_BANK_CHOOSER.name());

        InlineKeyboardButton checkCurrencyButton = new InlineKeyboardButton();
        checkCurrencyButton.setText("Валюти");
        checkCurrencyButton.setCallbackData(Buttons.BUTTON_CURRENCY_CHECKER.name());

        InlineKeyboardButton mainMenuButton = new InlineKeyboardButton();
        mainMenuButton.setText("Головне меню");
        mainMenuButton.setCallbackData(Buttons.BUTTON_INFO.name());

        keyboardButtonsRow1.add(currencyRefactorButton);
        keyboardButtonsRow2.add(bankChooserButton);
        keyboardButtonsRow3.add(checkCurrencyButton);
        keyboardButtonsRow4.add(mainMenuButton);

        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);

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
        } else {
            two_button.setText("2");
        }
        two_button.setCallbackData(Buttons.BUTTON_TWO_CURRENCY.name());

        InlineKeyboardButton three_button = new InlineKeyboardButton();
        if (valueCurrency == 3) {
            three_button.setText("✓ 3");
        } else {
            three_button.setText("3");
        }
        three_button.setCallbackData(Buttons.BUTTON_THREE_CURRENCY.name());

        InlineKeyboardButton four_button = new InlineKeyboardButton();
        if (valueCurrency == 4) {
            four_button.setText("✓ 4");
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
        buttonInfo.setText("Отримати інфо");
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

    InlineKeyboardMarkup bankPickerButtons(SendMessage message, Banks bank) {
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        InlineKeyboardButton privatBankButton = new InlineKeyboardButton();
        if (bank.equals(Banks.PRIVATBANK)) {
            privatBankButton.setText("✓ Приватбанк");
        } else {
            privatBankButton.setText("Приватбанк");
        }
        privatBankButton.setCallbackData(Buttons.BUTTON_PRIVAT_BANK.name());

        InlineKeyboardButton monoBankButton = new InlineKeyboardButton();
        if (bank.equals(Banks.MONOBANK)) {
            monoBankButton.setText("✓ Монобанк");
        } else {
            monoBankButton.setText("Монобанк");
        }
        monoBankButton.setCallbackData(Buttons.BUTTON_MONO_BANK.name());

        InlineKeyboardButton NBUBankButton = new InlineKeyboardButton();
        if (bank.equals(Banks.NBU)) {
            NBUBankButton.setText("✓ НБУ");
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

    InlineKeyboardMarkup currencyPickButton(SendMessage message, HashSet<Currency> currency) {
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();

        InlineKeyboardButton USDButton = new InlineKeyboardButton();
        if (currency.contains(Currency.USD)) {
            USDButton.setText("✓ USD");
        } else
            USDButton.setText("USD");

        USDButton.setCallbackData(Buttons.BUTTON_USD.name());

        InlineKeyboardButton EURButton = new InlineKeyboardButton();
        if (currency.contains(Currency.EUR)) {
            EURButton.setText("✓ EUR");
        } else
            EURButton.setText("EUR");

        EURButton.setCallbackData(Buttons.BUTTON_EUR.name());

        InlineKeyboardButton GBPButton = new InlineKeyboardButton();
        if (currency.contains(Currency.GBP)) {
            GBPButton.setText("✓ GBP");
        } else
            GBPButton.setText("GBP");

        GBPButton.setCallbackData(Buttons.BUTTON_GBP.name());


        InlineKeyboardButton mainMenuButton = new InlineKeyboardButton();
        mainMenuButton.setText("Головне меню");
        mainMenuButton.setCallbackData(Buttons.BUTTON_INFO.name());

        keyboardButtonsRow1.add(USDButton);
        keyboardButtonsRow2.add(EURButton);
        keyboardButtonsRow3.add(GBPButton);
        keyboardButtonsRow4.add(mainMenuButton);
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
        return inlineKeyboardMarkup;
    }
}