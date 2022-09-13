package global.goit.romashko.concurrency.fizz_buzz;

import java.util.function.IntConsumer;

public class FizzBuzzDemo {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);

        Runnable fizzPrinter = () -> System.out.print("fizz, ");
        Runnable buzzPrinter = () -> System.out.print("buzz, ");
        Runnable fizzBuzzPrinter = () -> System.out.print("fizzbuzz, ");
        IntConsumer numberConsumer = number -> System.out.print(number + ", ");

        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz(fizzPrinter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz(buzzPrinter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(fizzBuzzPrinter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number(numberConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}
