package org.example;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

public class TakingInTask implements Runnable{

    private final Broker broker;
    private MessageProducing messageProducing;
    private static final String TEMPLATE_FOR_CREATING_MESSAGES = "Message '%s' has been moved to Broker\n";
    private int timeToSleep;

    TakingOutTask takingOutTask;

    public TakingInTask(Broker broker, int timeToSleep) {
        this.broker = broker;
        this.timeToSleep = timeToSleep;
        messageProducing = new MessageProducing();
    }

    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                Message message = messageProducing.createMessage();
                broker.takeIn(message);
                System.out.printf(String.format(TEMPLATE_FOR_CREATING_MESSAGES, message.toString()));
                TimeUnit.SECONDS.sleep(timeToSleep);
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    static final class MessageProducing {
        private static int NUMBER_OF_MESSAGE = 1;
        private static final String TEMPLATE_FOR_CREATING_MESSAGES = "Message%d";


        public Message createMessage() {
            return new Message(String.format(TEMPLATE_FOR_CREATING_MESSAGES, NUMBER_OF_MESSAGE++));
        }

    }

}
