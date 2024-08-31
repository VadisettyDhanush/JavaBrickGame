import java.util.InputMismatchException;
import java.util.Scanner;

class BankDetails {
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

public class BankingApp {
    public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);
        BankDetails[] C = new BankDetails[0];

        int ch;
        do {
            System.out.println("\n***Banking System Application***");
            System.out.println("1. Staff Login\n2. User Login\n3. Exit");
            System.out.print("Enter your choice: ");
            try {
                ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        C = staffMenu(C);
                        break;
                    case 2:
                        userMenu(C);
                        break;
                    case 3:
                        System.out.println("See you soon...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the invalid input
                ch = -1; // Reset ch to avoid exiting the loop
            }
        } while (ch != 3);
    }

    private static BankDetails[] staffMenu(BankDetails[] C) {
        Scanner sc = new Scanner(System.in);
        int ch;
        do {
            System.out.println("\n***Staff Menu***");
            System.out.println("1. Display all account details\n2. Search by Account number\n3. Deposit the amount\n4. Withdraw the amount\n5. Add new user\n6. Exit");
            System.out.print("Enter your choice: ");
            try {
                ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        for (BankDetails b : C) {
                            b.showAccount();
                        }
                        break;
                    case 2:
                        System.out.print("Enter account no. you want to search: ");
                        String ac_no = sc.next();
                        boolean found = false;
                        for (BankDetails b : C) {
                            found = b.search(ac_no);
                            if (found) break;
                        }
                        if (!found) System.out.println("Search failed! Account doesn't exist..!!");
                        break;
                    case 3:
                        System.out.print("Enter Account no.: ");
                        ac_no = sc.next();
                        found = false;
                        for (BankDetails b : C) {
                            found = b.search(ac_no);
                            if (found) {
                                b.deposit();
                                break;
                            }
                        }
                        if (!found) System.out.println("Search failed! Account doesn't exist..!!");
                        break;
                    case 4:
                        System.out.print("Enter Account No.: ");
                        ac_no = sc.next();
                        found = false;
                        for (BankDetails b : C) {
                            found = b.search(ac_no);
                            if (found) {
                                b.withdrawal();
                                break;
                            }
                        }
                        if (!found) System.out.println("Search failed! Account doesn't exist..!!");
                        break;
                    case 5:
                        System.out.print("How many number of customers do you want to input? ");
                        try {
                            int n = sc.nextInt();
                            BankDetails newC[] = new BankDetails[C.length + n];
                            System.arraycopy(C, 0, newC, 0, C.length);
                            for (int i = C.length; i < newC.length; i++) {
                                newC[i] = new BankDetails();
                                newC[i].openAccount();
                            }
                            C = newC;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid number. Please try again.");
                            sc.next(); // Clear the invalid input
                        }
                        break;
                    case 6:
                        return C;
                    default:
                        System.out.println("Invalid choice! Please enter again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the invalid input
            }
        } while (true);
    }

    private static void userMenu(BankDetails[] C) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter your Account no.: ");
            String ac_no = sc.next();
            System.out.print("Enter your Password: ");
            String password = sc.next();
            boolean found = false;
            for (BankDetails b : C) {
                found = b.search(ac_no) && b.validatePassword(password);
                if (found) {
                    userOperations(b);
                    break;
                }
            }
            if (!found) System.out.println("Login failed! Account number or password is incorrect.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            sc.next(); // Clear the invalid input
        }
    }

    private static void userOperations(BankDetails b) {
        Scanner sc = new Scanner(System.in);
        int ch;
        do {
            System.out.println("\n***User Menu***");
            System.out.println("1. Display account details\n2. Deposit the amount\n3. Withdraw the amount\n4. Exit");
            System.out.print("Enter your choice: ");
            try {
                ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        b.showAccount();
                        break;
                    case 2:
                        b.deposit();
                        break;
                    case 3:
                        b.withdrawal();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice! Please enter again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the invalid input
            }
        } while (true);
    }
}
