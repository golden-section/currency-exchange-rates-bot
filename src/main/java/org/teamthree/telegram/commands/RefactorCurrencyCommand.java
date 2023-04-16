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

public class RefactorCurrencyCommand extends BotCommand {
    public RefactorCurrencyCommand() {
        super("refactor", "u can change sout currency after .");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = new SendMessage();
        message.setText("Оберіть скільки знаків після коми");
        message.setChatId(chat.getId());

        InlineKeyboardButton TWO_BUTTON = InlineKeyboardButton
                .builder()
                .text("2")
                .callbackData(String.valueOf(BotButtons.TWO_BUTTON))
                .build();

        InlineKeyboardButton THREE_BUTTON = InlineKeyboardButton
                .builder()
                .text("3")
                .callbackData(String.valueOf(BotButtons.THREE_BUTTON))
                .build();

        InlineKeyboardButton FOUR_BUTTON = InlineKeyboardButton
                .builder()
                .text("4")
                .callbackData(String.valueOf(BotButtons.FOUR_BUTTON))
                .build();

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder()
                .keyboard(Arrays.asList(
                        Collections.singletonList(TWO_BUTTON),
                        Collections.singletonList(THREE_BUTTON),
                        Collections.singletonList(FOUR_BUTTON)
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
