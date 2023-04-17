package org.teamthree.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalTime;

class TelegramBot extends TelegramLongPollingBot {

    TelegramBot() {
        super(BotConstants.BOT_TOKEN);
    }

    @Override
    public String getBotUsername() {
        return BotConstants.BOT_NAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        BotCommands botCommands = new BotCommands();
        Message message = update.getMessage();

        TimeAlert timeAlert = new TimeAlert();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            if (text.startsWith("time_")) {
                String hourString = text.substring(5);
                try {
                    int hour = Integer.parseInt(hourString);
                    if (hour >= 0 && hour <= 23) {
                        LocalTime time = LocalTime.of(hour, 0);
                        timeAlert.scheduleMessage(chatId, time);
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    SendMessage newMessage = new SendMessage();
                    newMessage.setChatId(chatId);
                    newMessage.setText("Будь ласка використовуйте формат 0-23");
                    executeMessage(newMessage);
                }
            }
            if (message.getText().equals("/start")) {
                executeMessage(botCommands.sendStartMenu(chatId));
            }
        } else if (update.hasCallbackQuery()) {
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            Buttons callbackData = Buttons.valueOf(update.getCallbackQuery().getData());

            switch(callbackData) {
                case BUTTON_INFO -> executeMessage(botCommands.sendInfo(chatId));

                case BUTTON_SETTINGS -> executeMessage(botCommands.sendSettings(chatId));

                case BUTTON_CURRENCY_REFACTOR -> executeMessage(botCommands.defaultRefactorCurrency(chatId, ButtonSetup.lastValueOfCurrencyRefactor));

                case BUTTON_BANK_CHOOSER -> executeMessage(botCommands.defaultChooseBank(chatId, ButtonSetup.lastValueOfBankPicker));

                case BUTTON_CURRENCY_CHECKER -> executeMessage(botCommands.defaultCurrencyPicker(chatId, ButtonSetup.lastValueOfCurrencyPicker));

                case BUTTON_ALERT_TIME -> executeMessage(botCommands.timeAlert(chatId));

                case BUTTON_TWO_CURRENCY -> {
                    ButtonSetup.lastValueOfCurrencyRefactor = 2;
                    executeMessage(botCommands.refactorCurrency(chatId, messageId, ButtonSetup.lastValueOfCurrencyRefactor));
                }

                case BUTTON_THREE_CURRENCY -> {
                    ButtonSetup.lastValueOfCurrencyRefactor = 3;
                    executeMessage(botCommands.refactorCurrency(chatId, messageId, ButtonSetup.lastValueOfCurrencyRefactor));
                }

                case BUTTON_FOUR_CURRENCY -> {
                    ButtonSetup.lastValueOfCurrencyRefactor = 4;
                    executeMessage(botCommands.refactorCurrency(chatId, messageId, ButtonSetup.lastValueOfCurrencyRefactor));
                }

                case BUTTON_PRIVAT_BANK -> {
                    ButtonSetup.lastValueOfBankPicker = 1;
                    executeMessage(botCommands.chooseBank(chatId, messageId, ButtonSetup.lastValueOfBankPicker));
                }

                case BUTTON_MONO_BANK -> {
                    ButtonSetup.lastValueOfBankPicker = 2;
                    executeMessage(botCommands.chooseBank(chatId, messageId, ButtonSetup.lastValueOfBankPicker));
                }

                case BUTTON_NBU_BANK -> {
                    ButtonSetup.lastValueOfBankPicker = 3;
                    executeMessage(botCommands.chooseBank(chatId, messageId, ButtonSetup.lastValueOfBankPicker));
                }

                case BUTTON_USD -> {
                    ButtonSetup.lastValueOfCurrencyPicker = 1;
                    executeMessage(botCommands.currencyPicker(chatId, messageId, ButtonSetup.lastValueOfCurrencyPicker));
                }

                case BUTTON_EUR -> {
                    ButtonSetup.lastValueOfCurrencyPicker = 2;
                    executeMessage(botCommands.currencyPicker(chatId, messageId, ButtonSetup.lastValueOfCurrencyPicker));
                }

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

    void executeMessage(EditMessageText message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

