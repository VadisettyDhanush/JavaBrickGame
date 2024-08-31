package bank.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {
    private static Scanner sc = new Scanner(System.in);

    public static int getIntInput(String message) {
        int input = -1;
        while (input == -1) {
            try {
                System.out.print(message);
                input = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the invalid input
            }
        }
        return input;
    }

    public static String getStringInput(String message) {
        System.out.print(message);
        return sc.next();
    }
}
