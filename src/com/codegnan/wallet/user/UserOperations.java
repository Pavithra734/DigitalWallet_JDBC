package com.codegnan.wallet.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.codegnan.wallet.connection.DBConnection;

public class UserOperations {
	
	public int loginUser() {

	    Scanner sc = new Scanner(System.in);

	    System.out.println("\n--- User Login ---");

	    System.out.print("Enter Email: ");
	    String email = sc.nextLine();

	    System.out.print("Enter Password: ");
	    String password = sc.nextLine();

	    try {

	        Connection con = DBConnection.getConnection();

	        String sql = "SELECT user_id, name FROM users "
	                   + "WHERE email = ? AND password = ?";

	        PreparedStatement pst = con.prepareStatement(sql);

	        pst.setString(1, email);
	        pst.setString(2, password);

	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {

	            int userId = rs.getInt("user_id");
	            String name = rs.getString("name");

	            System.out.println("Login Successful");
	            System.out.println("Welcome " + name);

	            rs.close();
	            pst.close();
	            con.close();

	            return userId;

	        } else {

	            System.out.println("Invalid Email or Password");
	        }

	        rs.close();
	        pst.close();
	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return -1;
	}
	
    public void registerUser() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- User Registration ---");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);

            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("User Registered Successfully");
            }

            pst.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}