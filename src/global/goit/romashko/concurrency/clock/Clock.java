package global.goit.romashko.concurrency.clock;

import java.time.Instant;

//Solution to Task 1
public class Clock implements Runnable {
    private static final int TIMING_MILLIS = 1000;
    private final Instant startPoint;

    public Clock(Instant startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void run() {
        ClockListener clockListener = new ClockListener(startPoint);
        Thread printThread = new Thread(clockListener);
        printThread.setDaemon(true);

        printThread.start();

        while (true) {
            synchronized (printThread) {
                printThread.notify();
            }

            try {
                Thread.sleep(TIMING_MILLIS);
            } catch (InterruptedException e) {
                System.out.println("Interrupted while clock is sleeping: " + e.getMessage());
            }
        }
    }
}
