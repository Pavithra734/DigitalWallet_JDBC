package com.codegnan.wallet.wallet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.codegnan.wallet.connection.DBConnection;

public class WalletOperations {

    // ==============================
    // CREATE WALLET
    // ==============================

    private Statement DBConnection;


	public void createWallet(int userId) {

        System.out.println("\n--- Create Wallet ---");

        try {

            Connection con = DBConnection.getConnection();

            // Check whether wallet already exists
            String checkSql = "SELECT * FROM wallet WHERE user_id = ?";

            PreparedStatement checkPst =
                    con.prepareStatement(checkSql);

            checkPst.setInt(1, userId);

            ResultSet rs = checkPst.executeQuery();

            if (rs.next()) {

                System.out.println(
                    "Wallet already exists for this user."
                );

            } else {

                String sql =
                    "INSERT INTO wallet(user_id, balance) VALUES (?, ?)";

                PreparedStatement pst =
                        con.prepareStatement(sql);

                pst.setInt(1, userId);
                pst.setDouble(2, 0);

                int result = pst.executeUpdate();

                if (result > 0) {

                    System.out.println(
                        "Wallet Created Successfully"
                    );
                }

                pst.close();
            }

            rs.close();
            checkPst.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ==============================
    // ADD MONEY
    // ==============================

    public void addMoney(int userId) {

        System.out.println("\n--- Add Money ---");

        System.out.print("Enter Amount: ");

        // We will temporarily create Scanner here
        java.util.Scanner sc =
                new java.util.Scanner(System.in);

        double amount = sc.nextDouble();

        if (amount <= 0) {

            System.out.println(
                "Amount must be greater than 0"
            );

            return;
        }

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                "UPDATE wallet " +
                "SET balance = balance + ? " +
                "WHERE user_id = ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setDouble(1, amount);
            pst.setInt(2, userId);

            int result = pst.executeUpdate();

            if (result > 0) {

                System.out.println(
                    "Money Added Successfully"
                );

            } else {

                System.out.println(
                    "Wallet Not Found"
                );
            }

            pst.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ==============================
    // CHECK BALANCE
    // ==============================

    public void checkBalance(int userId) {

        System.out.println("\n--- Check Balance ---");

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                "SELECT balance FROM wallet WHERE user_id = ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                double balance =
                        rs.getDouble("balance");

                System.out.println(
                    "Current Balance: " + balance
                );

            } else {

                System.out.println(
                    "Wallet Not Found"
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


