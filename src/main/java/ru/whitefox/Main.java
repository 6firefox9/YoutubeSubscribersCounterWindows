package ru.whitefox;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args){
        YoutubeFrame.init();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            YoutubeFrame.updateUI();
        }, 0, Options.frequency, TimeUnit.SECONDS);

    }
}