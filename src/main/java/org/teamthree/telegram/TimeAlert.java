package org.teamthree.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeAlert {
    Timer timer;

    public TimeAlert(int hours, long chatId) {
        timer = new Timer();
        timer.schedule(new RemindTask(chatId), getTime(hours));
    }

    class RemindTask extends TimerTask {
        long chatId;
        RemindTask(long chatId) {
            this.chatId = chatId;
        }
        public void run() {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Время уведомления");
        }
    }

    private Date getTime(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();
        if (time.before(new Date())) {
            time = new Date(time.getTime() + 24 * 60 * 60 * 1000);
        }
        return time;
    }
}
