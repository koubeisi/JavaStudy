package org.example.timer;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author PC
 * @since 2024/10/10
 */
public class TimerTest {



    @Test
    public void timerTasker() throws InterruptedException {
        var time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }, 3000);

        TimeUnit.SECONDS.sleep(5);
    }
}
