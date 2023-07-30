package org.example;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

public class TakingOutTask implements Runnable{

    private final Broker broker;
    private static final String TEMPLATE_FOR_TAKING_MESSAGES = "Message '%s' has been unMoved to Broker\n";
    private int timeToSleep;

    public TakingOutTask(Broker broker, int timeToSleep) {
        this.broker = broker;
        this.timeToSleep = timeToSleep;
    }

    @Override
    public void run() {
        try {
            while(!currentThread().isInterrupted()) {
                TimeUnit.SECONDS.sleep(timeToSleep);
                Message message = broker.takeOff();
                System.out.printf(String.format(TEMPLATE_FOR_TAKING_MESSAGES, message.toString()));
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }
}
