package org.teamthree.telegram;

import java.io.*;
import java.util.Properties;


class BotTokenGetter {
    protected static String getToken() {
        Properties properties = new Properties();
        FileInputStream token;
        try {
            token = new FileInputStream("application.properties");
            properties.load(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty("botToken");
    }
}
