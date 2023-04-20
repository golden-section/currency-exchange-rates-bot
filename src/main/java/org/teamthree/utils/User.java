package org.teamthree.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private String name;
    private long chatId;
    private UserSettings userSettings;

public User(String name, long chatId, UserSettings userSettings) {
        this.name = name;
        this.chatId = chatId;
        this.userSettings = userSettings;
    }
}