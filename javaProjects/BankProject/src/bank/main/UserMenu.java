package bank.main;

import bank.details.BankDetails;
import bank.utils.InputUtils;

public class UserMenu {
    public static void userMenu(BankDetails[] C) {
        String ac_no = InputUtils.getStringInput("Enter your Account no.: ");
        String password = InputUtils.getStringInput("Enter your Password: ");
        boolean found = false;
        for (BankDetails b : C) {
            found = b.search(ac_no) && b.validatePassword(password);
            if (found) {
                userOperations(b);
                break;
            }
        }
        if (!found) System.out.println("Login failed! Account number or password is incorrect.");
    }

    private static void userOperations(BankDetails b) {
        int ch;
        do {
            System.out.println("\n***User Menu***");
            System.out.println("1. Display account details\n2. Deposit the amount\n3. Withdraw the amount\n4. Exit");
            ch = InputUtils.getIntInput("Enter your choice: ");
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
        } while (true);
    }
}
