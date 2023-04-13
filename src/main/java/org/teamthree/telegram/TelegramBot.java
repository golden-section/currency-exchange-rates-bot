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
        Message message = update.getMessage();

        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            if (message.getText().equals("/start")) {
                executeMessage(botCommands.sendStartMenu(chatId));
            }
        } else if (update.hasCallbackQuery()) {
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            Buttons callbackData = Buttons.valueOf(update.getCallbackQuery().getData().toUpperCase());

            switch(callbackData) {
                case BUTTON_INFO -> executeMessage(botCommands.sendInfo(chatId));

                case BUTTON_SETTINGS -> executeMessage(botCommands.sendSettings(chatId));

                case BUTTON_CURRENCY_REFACTOR -> executeMessage(botCommands.refactorCurrency());

                case BUTTON_BANK_CHOOSER -> executeMessage(botCommands.chooseBank());

                case BUTTON_CURRENCY_CHECKER -> executeMessage(botCommands.currencyChecker());

                case BUTTON_ALERT_TIME -> executeMessage(botCommands.timeAlert());

                default -> System.out.println("Unknown command");
            }
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

