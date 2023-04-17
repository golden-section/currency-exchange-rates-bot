package org.teamthree.telegram.commands;

import org.teamthree.telegram.BotButtons;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;

public class SettingsCommand extends BotCommand {

    public SettingsCommand() {
        super("settings", "settings menu");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = new SendMessage();
        message.setText("Налаштування");
        message.setChatId(chat.getId());

        InlineKeyboardButton refactorCurrencyButton = InlineKeyboardButton
                .builder()
                .text("К-ть знаків після коми")
                .callbackData(String.valueOf(BotButtons.REFACTOR_CURRENCY_BUTTON))
                .build();

        InlineKeyboardButton chooseBankButton = InlineKeyboardButton
                .builder()
                .text("Банк")
                .callbackData(String.valueOf(BotButtons.CHOOSE_BANK_BUTTON))
                .build();

        InlineKeyboardButton pickCurrencyButton = InlineKeyboardButton
                .builder()
                .text("Валюти")
                .callbackData(String.valueOf(BotButtons.PICK_CURRENCY_BUTTON))
                .build();

        InlineKeyboardButton timeAlertButton = InlineKeyboardButton
                .builder()
                .text("Час сповіщення")
                .callbackData(String.valueOf(BotButtons.TIME_ALERT_BUTTON))
                .build();

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder()
                .keyboard(Arrays.asList(
                        Collections.singletonList(refactorCurrencyButton),
                        Collections.singletonList(chooseBankButton),
                        Collections.singletonList(pickCurrencyButton),
                        Collections.singletonList(timeAlertButton)
                ))
                .build();

        message.setReplyMarkup(keyboard);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
