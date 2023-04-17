package org.teamthree.telegram.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TimeAlertCommand extends BotCommand {
    public TimeAlertCommand() {
        super("alert", "you can choose notification time");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = new SendMessage();
        message.setText("Виберіть час сповіщення ");
        message.setChatId(chat.getId());

        KeyboardButton[] buttons = new KeyboardButton[24];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new KeyboardButton(String.valueOf(i + 1));
        }

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        for (KeyboardButton button : buttons) {
            row.add(button);
            if (row.size() == 6) {
                keyboard.add(row);
                row = new KeyboardRow();
            }
        }
        if (row.size() > 0) {
            keyboard.add(row);
        }

        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setKeyboard(keyboard);
        replyMarkup.setOneTimeKeyboard(true);
        replyMarkup.setResizeKeyboard(true);
        message.setReplyMarkup(replyMarkup);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
