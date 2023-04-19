package org.teamthree;

import org.teamthree.telegram.BotRegister;

public class AppLauncher {
    public static void main(String[] args) {
        BotRegister botRegister = new BotRegister();
        botRegister.register();
        System.out.println("Running!");
    }
}