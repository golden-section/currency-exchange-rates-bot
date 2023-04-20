package org.teamthree.telegram;

import org.teamthree.utils.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.teamthree.banks.Currency;

import java.time.LocalTime;

import static org.teamthree.utils.UserUtils.*;

class TelegramBot extends TelegramLongPollingBot {

    TelegramBot() {
        super(System.getenv("TOKEN"));
    }

    @Override
    public String getBotUsername() {
        return System.getenv("BOT_USERNAME");
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
                if(!UserUtils.isUserExists(message.getChatId())) {
                    UserUtils.addUser(new User(message.getChat().getFirstName(), message.getChatId(), UserSettings.defaultSettings()));
                }
                executeMessage(botCommands.sendStartMenu(chatId));
            }
        } else if (update.hasCallbackQuery()) {
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            Buttons callbackData = Buttons.valueOf(update.getCallbackQuery().getData());
            User user = UserUtils.getUser(chatId);

            switch(callbackData) {
                case BUTTON_INFO -> executeMessage(botCommands.sendInfo(chatId));

                case BUTTON_SETTINGS -> executeMessage(botCommands.sendSettings(chatId));

                case BUTTON_CURRENCY_REFACTOR -> executeMessage(botCommands.defaultRefactorCurrency(chatId, user.getUserSettings().getSymbolsAfterDot()));

                case BUTTON_BANK_CHOOSER -> executeMessage(botCommands.defaultChooseBank(chatId, user.getUserSettings().getBank()));

                case BUTTON_CURRENCY_CHECKER -> executeMessage(botCommands.defaultCurrencyPicker(chatId, user.getUserSettings().getCurrency()));

                case BUTTON_ALERT_TIME -> executeMessage(botCommands.timeAlert(chatId));

                case BUTTON_TWO_CURRENCY -> {
                    setCurrencyRefactor(chatId, 2);
                    executeMessage(botCommands.refactorCurrency(chatId, messageId, 2));
                }

                case BUTTON_THREE_CURRENCY -> {
                    setCurrencyRefactor(chatId, 3);
                    executeMessage(botCommands.refactorCurrency(chatId, messageId, 3));
                }

                case BUTTON_FOUR_CURRENCY -> {
                    setCurrencyRefactor(chatId, 4);
                    executeMessage(botCommands.refactorCurrency(chatId, messageId, 4));
                }

                case BUTTON_PRIVAT_BANK -> {
                    UserUtils.setUserBank(chatId, Banks.PRIVATBANK);
                    executeMessage(botCommands.chooseBank(chatId, messageId, user.getUserSettings().getBank()));
                }

                case BUTTON_MONO_BANK -> {
                    UserUtils.setUserBank(chatId, Banks.MONOBANK);
                    executeMessage(botCommands.chooseBank(chatId, messageId, user.getUserSettings().getBank()));
                }

                case BUTTON_NBU_BANK -> {
                    UserUtils.setUserBank(chatId, Banks.NBU);
                    executeMessage(botCommands.chooseBank(chatId, messageId, user.getUserSettings().getBank()));
                }

                case BUTTON_USD -> {
                    if(user.getUserSettings().getCurrency().contains(Currency.USD)) {
                        unsetUserCurrency(chatId, Currency.USD);
                    } else {
                        setUserCurrency(chatId, Currency.USD);
                    }
                    executeMessage(botCommands.currencyPicker(chatId, messageId, user.getUserSettings().getCurrency()));
                }

                case BUTTON_EUR -> {
                    if(user.getUserSettings().getCurrency().contains(Currency.EUR)) {
                        unsetUserCurrency(chatId, Currency.EUR);
                    } else {
                        setUserCurrency(chatId, Currency.EUR);
                    }

                    executeMessage(botCommands.currencyPicker(chatId, messageId, user.getUserSettings().getCurrency()));
                }

                case BUTTON_GBP -> {
                    if(user.getUserSettings().getCurrency().contains(Currency.GBP)) {
                        unsetUserCurrency(chatId, Currency.GBP);
                    } else {
                        setUserCurrency(chatId, Currency.GBP);
                    }
                    executeMessage(botCommands.currencyPicker(chatId, messageId, user.getUserSettings().getCurrency()));
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