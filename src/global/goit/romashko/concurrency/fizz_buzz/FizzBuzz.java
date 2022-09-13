package global.goit.romashko.concurrency.fizz_buzz;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private final int n;
    private final Semaphore semaphoreFizz;
    private final Semaphore semaphoreBuzz;
    private final Semaphore semaphoreFizzBuzz;
    private final Semaphore semaphoreNumber;

    public FizzBuzz(int n) {
        this.n = n;
        semaphoreFizz = new Semaphore(0);
        semaphoreBuzz = new Semaphore(0);
        semaphoreFizzBuzz = new Semaphore(0);
        semaphoreNumber = new Semaphore(1);
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int counter = 3; counter <= n; counter += 3) {
            semaphoreFizz.acquire();
            printFizz.run();

            if ((counter + 3) % 5 == 0) {
                counter += 3;
            }

            semaphoreNumber.release();
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int counter = 5; counter <= n; counter += 5) {
            semaphoreBuzz.acquire();
            printBuzz.run();

            if ((counter + 5) % 3 == 0) {
                counter += 5;
            }

            semaphoreNumber.release();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int counter = 15; counter <= n; counter += 15) {
            semaphoreFizzBuzz.acquire();
            printFizzBuzz.run();

            semaphoreNumber.release();
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int counter = 1; counter <= n; counter++) {
            semaphoreNumber.acquire();

            if (counter % 15 == 0) {
                semaphoreFizzBuzz.release();
            } else if (counter % 5 == 0) {
                semaphoreBuzz.release();
            } else if (counter % 3 == 0) {
                semaphoreFizz.release();
            } else {
                printNumber.accept(counter);

                semaphoreNumber.release();
            }
        }
    }
}
