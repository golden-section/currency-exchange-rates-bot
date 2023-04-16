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

public class InformationCommand extends BotCommand {
    public InformationCommand() {
        super("information", "get useful information about currency");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        //TODO вивід валюти
        SendMessage message = new SendMessage();
        message.setText("Курс в Приватбанк: USD/UAN\nЗакупівля: 27.55\nПродаж: 27.95");
        message.setChatId(chat.getId());

        InlineKeyboardButton settingButton = InlineKeyboardButton
                .builder()
                .text("Налаштування")
                .callbackData(String.valueOf(BotButtons.SETTINGS_BUTTON))
                .build();

        InlineKeyboardButton backToMenuButton = InlineKeyboardButton
                .builder()
                .text("Назад")
                .callbackData(String.valueOf(BotButtons.BACK_BUTTON))
                .build();
        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder()
                .keyboard(Arrays.asList(
                        Collections.singletonList(settingButton),
                        Collections.singletonList(backToMenuButton)
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
