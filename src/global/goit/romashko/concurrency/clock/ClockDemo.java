package global.goit.romashko.concurrency.clock;

import java.time.Instant;

public class ClockDemo {

    public static void main(String[] args) {
        final Instant start = Instant.now();
        Clock clock = new Clock(start);
        Thread clockThread = new Thread(clock);
        clockThread.start();
    }
}