package org.example;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

import static java.lang.Thread.*;

public class Broker {

    private final Queue<Message> queue;
    private static int MAXIMUM_AMOUNT_OF_MESSAGES_IN_QUEUE;

    public Broker(int MAXIMUM_AMOUNT_OF_MESSAGES_IN_QUEUE) {
        queue = new ArrayDeque<>(MAXIMUM_AMOUNT_OF_MESSAGES_IN_QUEUE);
        this.MAXIMUM_AMOUNT_OF_MESSAGES_IN_QUEUE = MAXIMUM_AMOUNT_OF_MESSAGES_IN_QUEUE;
    }

    public synchronized void takeIn(Message message) {
        try {
            while(queue.size() >= MAXIMUM_AMOUNT_OF_MESSAGES_IN_QUEUE) {
                wait();
            }
            queue.add(message);
            notify();
        } catch (InterruptedException interruptedException) {
            currentThread().interrupt();
        }
    }

    public synchronized Message takeOff() {
        try {
            while(queue.isEmpty()) {
                wait();
            }
            Message message = queue.poll();
            notify();
            return message;
        } catch (InterruptedException interruptedException) {
            currentThread().interrupt();
            throw new RuntimeException(interruptedException);
        }
    }
}
