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

public class CurrencyChooserCommand extends BotCommand {
    public CurrencyChooserCommand() {
        super("currency", "you can choose currency from variables");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = new SendMessage();
        message.setText("Оберіть валюту");
        message.setChatId(chat.getId());

        InlineKeyboardButton USDButton = InlineKeyboardButton
                .builder()
                .text("USD")
                .callbackData(String.valueOf(BotButtons.USD_BUTTON))
                .build();

        InlineKeyboardButton monoBankButton = InlineKeyboardButton
                .builder()
                .text("EUR")
                .callbackData(String.valueOf(BotButtons.EUR_BUTTON))
                .build();

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder()
                .keyboard(Arrays.asList(
                        Collections.singletonList(USDButton),
                        Collections.singletonList(monoBankButton)
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
