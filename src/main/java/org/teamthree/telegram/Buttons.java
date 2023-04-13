package org.teamthree.telegram;

enum Buttons {
    BUTTON_INFO("button_info"),
    BUTTON_SETTINGS("button_settings"),
    BUTTON_CURRENCY_REFACTOR("button_currency_refactor"),
    BUTTON_BANK_CHOOSER("bank_chooser_button"),
    BUTTON_CURRENCY_CHECKER("currency_checker_button"),
    BUTTON_ALERT_TIME("alert_time_button");

    private final String text;

    Buttons(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}


