package com.codegnan.wallet.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.codegnan.wallet.connection.DBConnection;

public class TransactionOperations {

    // ==============================
    // SEND MONEY
    // ==============================

    public void sendMoney(int senderId) {

        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.println("\n--- Send Money ---");

        System.out.print("Enter Receiver User ID: ");
        int receiverId = sc.nextInt();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {

            System.out.println("Amount must be greater than 0");
            return;
        }

        if (senderId == receiverId) {

            System.out.println("Cannot send money to yourself.");
            return;
        }

        try {

            Connection con = DBConnection.getConnection();

            // Check receiver
            String receiverSql =
                    "SELECT user_id FROM users WHERE user_id = ?";

            PreparedStatement receiverPst =
                    con.prepareStatement(receiverSql);

            receiverPst.setInt(1, receiverId);

            ResultSet receiverRs =
                    receiverPst.executeQuery();

            if (!receiverRs.next()) {

                System.out.println("Receiver User Not Found");

                receiverRs.close();
                receiverPst.close();
                con.close();

                return;
            }

            receiverRs.close();
            receiverPst.close();


            // Check sender wallet
            String balanceSql =
                    "SELECT balance FROM wallet WHERE user_id = ?";

            PreparedStatement balancePst =
                    con.prepareStatement(balanceSql);

            balancePst.setInt(1, senderId);

            ResultSet balanceRs =
                    balancePst.executeQuery();

            if (!balanceRs.next()) {

                System.out.println("Sender Wallet Not Found");

                balanceRs.close();
                balancePst.close();
                con.close();

                return;
            }

            double balance =
                    balanceRs.getDouble("balance");

            balanceRs.close();
            balancePst.close();


            // Check balance
            if (balance < amount) {

                System.out.println("Insufficient Balance");

                con.close();

                return;
            }


            // Deduct from sender
            String deductSql =
                    "UPDATE wallet " +
                    "SET balance = balance - ? " +
                    "WHERE user_id = ?";

            PreparedStatement deductPst =
                    con.prepareStatement(deductSql);

            deductPst.setDouble(1, amount);
            deductPst.setInt(2, senderId);

            deductPst.executeUpdate();

            deductPst.close();


            // Add to receiver
            String addSql =
                    "UPDATE wallet " +
                    "SET balance = balance + ? " +
                    "WHERE user_id = ?";

            PreparedStatement addPst =
                    con.prepareStatement(addSql);

            addPst.setDouble(1, amount);
            addPst.setInt(2, receiverId);

            int result = addPst.executeUpdate();

            addPst.close();


            if (result > 0) {

                // Save transaction
                String transactionSql =
                        "INSERT INTO transactions " +
                        "(sender_id, receiver_id, amount, transaction_type) " +
                        "VALUES (?, ?, ?, ?)";

                PreparedStatement transactionPst =con.prepareStatement(transactionSql);

                transactionPst.setInt(1, senderId);
                transactionPst.setInt(2, receiverId);
                transactionPst.setDouble(3, amount);
                transactionPst.setString(4, "SEND");

                transactionPst.executeUpdate();

                transactionPst.close();

                System.out.println("Money Sent Successfully");

            } else {

                System.out.println("Receiver Wallet Not Found");
            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ==============================
    // TRANSACTION HISTORY
    // ==============================

    public void transactionHistory(int userId) {

        System.out.println("\n--- Transaction History ---");

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT transaction_id, sender_id, receiver_id, " +
                    "amount, transaction_type, transaction_date " +
                    "FROM transactions " +
                    "WHERE sender_id = ? OR receiver_id = ? " +
                    "ORDER BY transaction_date DESC";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(1, userId);
            pst.setInt(2, userId);

            ResultSet rs = pst.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "------------------------------"
                );

                System.out.println(
                        "Transaction ID: "
                        + rs.getInt("transaction_id")
                );

                System.out.println(
                        "Sender ID: "
                        + rs.getInt("sender_id")
                );

                System.out.println(
                        "Receiver ID: "
                        + rs.getInt("receiver_id")
                );

                System.out.println(
                        "Amount: "
                        + rs.getDouble("amount")
                );

                System.out.println(
                        "Type: "
                        + rs.getString("transaction_type")
                );

                System.out.println(
                        "Date: "
                        + rs.getTimestamp("transaction_date")
                );
            }

            if (!found) {

                System.out.println(
                        "No Transactions Found"
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