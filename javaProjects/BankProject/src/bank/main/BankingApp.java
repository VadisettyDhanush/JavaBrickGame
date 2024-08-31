package bank.main;

import bank.details.BankDetails;
import bank.utils.InputUtils;

public class BankingApp {
    public static void main(String[] args) {
        BankDetails[] C = new BankDetails[0];
        int ch;
        do {
            System.out.println("\n***Banking System Application***");
            System.out.println("1. Staff Login\n2. User Login\n3. Exit");
            ch = InputUtils.getIntInput("Enter your choice: ");
            switch (ch) {
                case 1:
                    C = StaffMenu.staffMenu(C);
                    break;
                case 2:
                    UserMenu.userMenu(C);
                    break;
                case 3:
                    System.out.println("See you soon...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter again.");
            }
        } while (ch != 3);
    }
}
