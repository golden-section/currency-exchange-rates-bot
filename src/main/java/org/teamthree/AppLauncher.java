package org.teamthree;

import org.teamthree.telegram.TelegramBotService;


public class AppLauncher {
    public static void main(String[] args) {
        new TelegramBotService();
        System.out.println("Running!");
    }
}