package org.teamthree.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class BotCommands {
    SendMessage message = new SendMessage();

    SendMessage sendMenu(long chatId) {
        message.setChatId(chatId);
        message.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют");

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

        return message;
    }

    SendMessage sendInfo(long chatId) {
        message.setChatId(chatId);
        message.setText("Курс:");
        return message;
    }
}
