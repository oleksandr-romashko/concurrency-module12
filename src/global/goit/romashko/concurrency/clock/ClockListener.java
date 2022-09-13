package global.goit.romashko.concurrency.clock;

import java.time.Duration;
import java.time.Instant;

public class ClockListener implements Runnable {
    private final Instant startPoint;
    public ClockListener(Instant startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Thread.currentThread()) {
                try {
                    Thread.currentThread().wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted while ClockListener is waiting: " + e.getMessage());
                }
            }
            Instant end = Instant.now();
            long timeElapsed = Duration.between(startPoint, end).toSeconds();
            if (timeElapsed % 5 != 0) {
                System.out.println(timeElapsed);
            } else {
                System.out.println("Прошло 5 секунд");
            }
        }
    }
}
