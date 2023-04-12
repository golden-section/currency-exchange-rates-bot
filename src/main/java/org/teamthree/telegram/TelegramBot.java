package org.teamthree.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

class TelegramBot extends TelegramLongPollingBot {

    TelegramBot() {
        super(BotTokenGetter.getToken());
    }

    @Override
    public String getBotUsername() {
        return "currency_exchange_rates_tm3_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        BotCommands botCommands = new BotCommands();
        long chatId = update.getMessage().getChatId();
        Message message = update.getMessage();

        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                if (message.getText().equals("/start")) {
                    executeMessage(botCommands.sendMenu(chatId));
                }
            }
        } else if (update.hasCallbackQuery()) {
            System.out.println("Pressed");
        }
    }


    void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}

