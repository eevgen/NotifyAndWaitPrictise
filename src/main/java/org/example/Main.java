package org.example;

import java.util.Arrays;

public class Main {

    private static final int TIME_TO_SLEEP_BEFORE_TAKING_IN = 1;
    private static final int TIME_TO_SLEEP_BEFORE_TAKING_OFF = 10;

    public static void main(String[] args) {

        Broker broker = new Broker(5);

        Thread takingInThread = new Thread(new TakingInTask(broker, TIME_TO_SLEEP_BEFORE_TAKING_IN));
        Thread takingOffThread = new Thread(new TakingOutTask(broker, TIME_TO_SLEEP_BEFORE_TAKING_OFF));

        startingThreads(takingInThread, takingOffThread);

    }

    public static void startingThreads(Thread... threads) {
        Arrays.stream(threads).forEach(Thread::start);
    }
}