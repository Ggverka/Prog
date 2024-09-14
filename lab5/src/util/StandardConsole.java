package util;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.util.Scanner;
public class StandardConsole implements Console {
    private static final String P = "~ ";
    private static Scanner fileScanner = null;
    private static Scanner defScanner = new Scanner(System.in);

    public StandardConsole(){

    }

    public void print(Object obj) {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print(obj);

    }

    public void println(Object obj) {
        System.out.println(obj);
    }

    public void printError(Object obj) {
        System.err.println("Error: " + obj);
    }

    public String readln() throws NoSuchElementException, IllegalStateException {
        return (fileScanner!=null?fileScanner:defScanner).nextLine();
    }

    public void printTable(Object elementLeft, Object elementRight) {
        System.out.printf(" %-55s%-1s%n", elementLeft, elementRight);
    }

    public void prompt() {
        print(P);
    }

    public String getPrompt() {
        return P;
    }
}
