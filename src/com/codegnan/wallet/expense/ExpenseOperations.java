package com.codegnan.wallet.expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.Scanner;

import com.codegnan.wallet.connection.DBConnection;

public class ExpenseOperations {

    // ==============================
    // ADD EXPENSE
    // ==============================

    public void addExpense(int userId) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- Add Expense ---");

        System.out.print("Enter Category: ");
        String category = sc.next();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than 0");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Expense Date (YYYY-MM-DD): ");
        String expenseDate = sc.nextLine();

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        try {  

            Connection con = DBConnection.getConnection();

            String sql =
                    "INSERT INTO expenses " +
                    "(user_id, category, amount, expense_date, description) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(1, userId);
            pst.setString(2, category);
            pst.setDouble(3, amount);
            pst.setDate(4, Date.valueOf(expenseDate));
            pst.setString(5, description);

            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("Expense Added Successfully");
            }

            pst.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ==============================
    // VIEW EXPENSES
    // ==============================

    public void viewExpenses(int userId) {

        System.out.println("\n--- View Expenses ---");

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT expense_id, category, amount, " +
                    "expense_date, description " +
                    "FROM expenses " +
                    "WHERE user_id = ? " +
                    "ORDER BY expense_date DESC";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "------------------------------"
                );

                System.out.println(
                        "Expense ID: "
                        + rs.getInt("expense_id")
                );

                System.out.println(
                        "Category: "
                        + rs.getString("category")
                );

                System.out.println(
                        "Amount: "
                        + rs.getDouble("amount")
                );

                System.out.println(
                        "Date: "
                        + rs.getDate("expense_date")
                );

                System.out.println(
                        "Description: "
                        + rs.getString("description")
                );
            }

            if (!found) {
                System.out.println("No Expenses Found");
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ==============================
    // MONTHLY EXPENSE REPORT
    // ==============================

    public void monthlyExpenseReport(int userId) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- Monthly Expense Report ---");

        System.out.print("Enter Year: ");
        int year = sc.nextInt();

        System.out.print("Enter Month (1-12): ");
        int month = sc.nextInt();

        if (month < 1 || month > 12) {

            System.out.println(
                    "Month must be between 1 and 12"
            );

            return;
        }

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT category, SUM(amount) AS total " +
                    "FROM expenses " +
                    "WHERE user_id = ? " +
                    "AND YEAR(expense_date) = ? " +
                    "AND MONTH(expense_date) = ? " +
                    "GROUP BY category";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(1, userId);
            pst.setInt(2, year);
            pst.setInt(3, month);

            ResultSet rs = pst.executeQuery();

            boolean found = false;
            double grandTotal = 0;

            System.out.println(
                    "\nCategory-wise Expenses"
            );

            System.out.println(
                    "------------------------------"
            );

            while (rs.next()) {

                found = true;

                String category =
                        rs.getString("category");

                double total =
                        rs.getDouble("total");

                System.out.println(
                        category + " : " + total
                );

                grandTotal += total;
            }

            if (found) {

                System.out.println(
                        "------------------------------"
                );

                System.out.println(
                        "Total Expense : " + grandTotal
                );

            } else {

                System.out.println(
                        "No Expenses Found for this Month"
                );
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}