package org.teamthree.telegram;

import org.teamthree.telegram.commands.*;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingCommandBot {

    TelegramBot() {
        registerAll(new StartCommand(),
                new InformationCommand(),
                new SettingsCommand(),
                new RefactorCurrencyCommand(),
                new BankChooserCommand(),
                new CurrencyChooserCommand());
    }

    @Override
    public String getBotUsername() {
        return BotConstants.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConstants.BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
            String callbackQuery = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

        }
        if (update.hasCallbackQuery()) {
            String callbackQuery = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
        }
    }
}
