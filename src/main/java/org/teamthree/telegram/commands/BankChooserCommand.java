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

public class BankChooserCommand extends BotCommand {
    public BankChooserCommand() {
        super("bank", "you can choose bank from variables");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = new SendMessage();
        message.setText("Оберіть банк");
        message.setChatId(chat.getId());

        InlineKeyboardButton privatBankButton = InlineKeyboardButton
                .builder()
                .text("ПриватБанк")
                .callbackData(String.valueOf(BotButtons.PRIVAT_BANK_BUTTON))
                .build();

        InlineKeyboardButton monoBankButton = InlineKeyboardButton
                .builder()
                .text("МоноБанк")
                .callbackData(String.valueOf(BotButtons.MONO_BANK_BUTTON))
                .build();

        InlineKeyboardButton NBUButton = InlineKeyboardButton
                .builder()
                .text("НБУ")
                .callbackData(String.valueOf(BotButtons.NBU_BANK_BUTTON))
                .build();


        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder()
                .keyboard(Arrays.asList(
                        Collections.singletonList(privatBankButton),
                        Collections.singletonList(monoBankButton),
                        Collections.singletonList(NBUButton)
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
