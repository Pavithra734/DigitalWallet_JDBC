package com.codegnan.wallet.main;

import java.util.Scanner;

import com.codegnan.wallet.user.UserOperations;
import com.codegnan.wallet.wallet.WalletOperations;
import com.codegnan.wallet.transaction.TransactionOperations;
import com.codegnan.wallet.expense.ExpenseOperations;

public class DigitalWalletApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserOperations user = new UserOperations();
        WalletOperations wallet = new WalletOperations();
        TransactionOperations transaction = new TransactionOperations();
        ExpenseOperations expense = new ExpenseOperations();

        int loggedInUserId = -1;
        String loggedInUserName = "";

        while (true) {

            System.out.println("\n====================================");
            System.out.println("       DIGITAL WALLET APPLICATION");
            System.out.println("====================================");

            // ==============================
            // BEFORE LOGIN
            // ==============================

            if (loggedInUserId == -1) {

                System.out.println("1. Register User");
                System.out.println("2. Login User");
                System.out.println("3. Exit");

                System.out.print("\nEnter your choice: ");

                int choice = sc.nextInt();

                switch (choice) {

                    case 1:

                        user.registerUser();
                        break;

                    case 2:

                        loggedInUserId = user.loginUser();

                        break;

                    case 3:

                        System.out.println(
                                "Thank you for using Digital Wallet Application!"
                        );

                        sc.close();
                        return;

                    default:

                        System.out.println(
                                "Invalid Choice. Please try again."
                        );
                }

            }

            // ==============================
            // AFTER LOGIN
            // ==============================

            else {

                System.out.println(
                        "Logged In User ID: " + loggedInUserId
                );

                System.out.println();

                System.out.println("1. Create Wallet");
                System.out.println("2. Add Money");
                System.out.println("3. Check Balance");
                System.out.println("4. Send Money");
                System.out.println("5. Transaction History");
                System.out.println("6. Add Expense");
                System.out.println("7. View Expenses");
                System.out.println("8. Monthly Expense Report");
                System.out.println("9. Logout");
                System.out.println("10. Exit");

                System.out.print("\nEnter your choice: ");

                int choice = sc.nextInt();

                switch (choice) {

                    case 1:

                        wallet.createWallet(loggedInUserId);
                        break;

                    case 2:

                        wallet.addMoney(loggedInUserId);
                        break;

                    case 3:

                        wallet.checkBalance(loggedInUserId);
                        break;

                    case 4:

                        transaction.sendMoney(loggedInUserId);
                        break;

                    case 5:

                        transaction.transactionHistory(
                                loggedInUserId
                        );
                        break;

                    case 6:

                        expense.addExpense(loggedInUserId);
                        break;

                    case 7:

                        expense.viewExpenses(
                                loggedInUserId
                        );
                        break;

                    case 8:

                        expense.monthlyExpenseReport(
                                loggedInUserId
                        );
                        break;

                    case 9:

                        System.out.println(
                                "Logout Successful"
                        );

                        loggedInUserId = -1;
                        loggedInUserName = "";

                        break;

                    case 10:

                        System.out.println(
                                "Thank you for using Digital Wallet Application!"
                        );

                        sc.close();
                        return;

                    default:

                        System.out.println(
                                "Invalid Choice. Please try again."
                        );
                }
            }
        }
    }
}