package bank.main;

import bank.details.BankDetails;
import bank.utils.InputUtils;

import java.util.Scanner;

public class StaffMenu {
    public static BankDetails[] staffMenu(BankDetails[] C) {
        Scanner sc = new Scanner(System.in);
        int ch;
        do {
            System.out.println("\n***Staff Menu***");
            System.out.println("1. Display all account details\n2. Search by Account number\n3. Deposit the amount\n4. Withdraw the amount\n5. Add new user\n6. Exit");
            ch = InputUtils.getIntInput("Enter your choice: ");
            switch (ch) {
                case 1:
                    for (BankDetails b : C) {
                        b.showAccount();
                    }
                    break;
                case 2:
                    String ac_no = InputUtils.getStringInput("Enter account no. you want to search: ");
                    boolean found = false;
                    for (BankDetails b : C) {
                        found = b.search(ac_no);
                        if (found) break;
                    }
                    if (!found) System.out.println("Search failed! Account doesn't exist..!!");
                    break;
                case 3:
                    ac_no = InputUtils.getStringInput("Enter Account no.: ");
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
                    ac_no = InputUtils.getStringInput("Enter Account No.: ");
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
                    int n = InputUtils.getIntInput("How many number of customers do you want to input? ");
                    BankDetails newC[] = new BankDetails[C.length + n];
                    System.arraycopy(C, 0, newC, 0, C.length);
                    for (int i = C.length; i < newC.length; i++) {
                        newC[i] = new BankDetails();
                        newC[i].openAccount();
                    }
                    C = newC;
                    break;
                case 6:
                    return C;
                default:
                    System.out.println("Invalid choice! Please enter again.");
            }
        } while (true);
    }
}
