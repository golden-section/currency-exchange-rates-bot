package org.teamthree.telegram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class BotTokenGetter {
    protected static String getToken() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src/main/java/org/teamthree/telegram/token_config.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scanner.nextLine();
    }
}
