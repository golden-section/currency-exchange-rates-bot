package org.teamthree.telegram;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeAlert {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    BotCommands botCommands = new BotCommands();
    void scheduleMessage(long chatId, LocalTime time) {
        Runnable sendTask = () -> botCommands.sendInfo(chatId);
        long delayInSeconds = time.toSecondOfDay() - LocalTime.now().toSecondOfDay();
        if (delayInSeconds < 0) {
            delayInSeconds += 24 * 60 * 60;
        }
        scheduler.schedule(sendTask, delayInSeconds, TimeUnit.SECONDS);
    }
}