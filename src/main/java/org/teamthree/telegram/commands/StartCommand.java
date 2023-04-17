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

public class StartCommand extends BotCommand {
    public StartCommand() {
        super("start", "Start command");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = new SendMessage();
        message.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют.");
        message.setChatId(chat.getId());

        InlineKeyboardButton infoButton = InlineKeyboardButton
                .builder()
                .text("Отримати інформацію")
                .callbackData(String.valueOf(BotButtons.INFO_BUTTON))
                .build();

        InlineKeyboardButton settingButton = InlineKeyboardButton
                .builder()
                .text("Налаштування")
                .callbackData(String.valueOf(BotButtons.SETTINGS_BUTTON))
                .build();

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder()
                .keyboard(Arrays.asList(
                        Collections.singletonList(infoButton),
                        Collections.singletonList(settingButton)
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
