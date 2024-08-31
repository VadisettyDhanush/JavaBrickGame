package bank.details;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankDetails {
    private String accno;
    private String name;
    private String acc_type;
    private long balance;
    private String password;

    Scanner sc = new Scanner(System.in);

    // method to open new account
    public void openAccount() {
        try {
            System.out.print("Enter Account No: ");
            accno = sc.next();
            System.out.print("Enter Account type: ");
            acc_type = sc.next();
            System.out.print("Enter Name: ");
            name = sc.next();
            System.out.print("Enter Amount deposited: ");
            balance = sc.nextLong();
            System.out.print("Enter Password: ");
            password = sc.next();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            sc.next(); // Clear the invalid input
            openAccount(); // Retry opening account
        }
    }

    // method to display account details
    public void showAccount() {
        System.out.println("Name of account holder: " + name);
        System.out.println("Account no.: " + accno);
        System.out.println("Account type: " + acc_type);
        System.out.println("Balance: " + balance);
    }

    // method to deposit money
    public void deposit() {
        try {
            System.out.print("Enter the amount you want to deposit: ");
            long amt = sc.nextLong();
            balance = balance + amt;
            System.out.println("Balance after deposit: " + balance);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please try again.");
            sc.next(); // Clear the invalid input
            deposit(); // Retry deposit
        }
    }

    // method to withdraw money
    public void withdrawal() {
        try {
            System.out.print("Enter the amount you want to withdraw: ");
            long amt = sc.nextLong();
            if (balance >= amt) {
                balance = balance - amt;
                System.out.println("Balance after withdrawal: " + balance);
            } else {
                System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please try again.");
            sc.next(); // Clear the invalid input
            withdrawal(); // Retry withdrawal
        }
    }

    // method to search an account number
    public boolean search(String ac_no) {
        if (accno.equals(ac_no)) {
            showAccount();
            return true;
        }
        return false;
    }

    // method to validate password
    public boolean validatePassword(String pass) {
        return password.equals(pass);
    }

    public String getAccNo() {
        return accno;
    }
}

