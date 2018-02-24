/*
 *Java Level 3, Lesson 4
 *@author Aleksey Antonov
 *@link https://github.com/ki11qwerty
 *
 * 1. Create 3 threads, each of which prints the letter (A, B and C) 5 times,
 *    the order should be exactly ABCABCABC. Use wait/notify/notifyAll
 * 2. Write a method in which 3 threads write data to a file line by line
 *    (for 10 records, with a period of 20 ms)
 * 3. Write a MFD class, where you can print and scan documents at the same
 *    time, but you can not print two documents or scan at the same time
 *    (when printing, messages are output to the console "1, 2, 3, ... pages"
 *    printed, when scanning the same, only "scanned ...", output
 *    to the console with a period of 50 ms)
 */


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    volatile char currentLetter = 'A';
    private final Object ob = new Object();

    public static void main(String[] args) {
        Main ma = new Main();
        String line1 = "Hello";
        String line2 = "World";
        String line3 = "!";

//exercise 1
        new Thread(() -> ma.printA()).start();
        new Thread(() -> ma.printB()).start();
        new Thread(() -> ma.printC()).start();
//exercise 2
        new Thread(() -> ma.writeLines(line1, new File("myWords.txt"))).start();
        new Thread(() -> ma.writeLines(line2, new File("myWords.txt"))).start();
        new Thread(() -> ma.writeLines(line3, new File("MyWords.txt"))).start();
    }

    void printA() {
        synchronized (ob) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A')
                        ob.wait();
                    System.out.print("A");
                    currentLetter = 'B';
                    ob.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    void printB() {
        synchronized (ob) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B')
                        ob.wait();
                    System.out.print("B");
                    currentLetter = 'C';
                    ob.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    void printC() {
        synchronized (ob) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C')
                        ob.wait();
                    System.out.print("C");
                    currentLetter = 'A';
                    ob.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    void writeLines(String str, File file) {
        synchronized (file) {
            try (FileWriter fw = new FileWriter(file, true)) {
                for (int i = 0; i < 10; i++) {
                    fw.write(str + "\n");
                    fw.flush();
                    Thread.sleep(20);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

