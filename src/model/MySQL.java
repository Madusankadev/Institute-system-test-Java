/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author Madusanka
 */
public class MySQL {

    private static Connection con;

    public static void createConnection() throws Exception {
        if (con == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/InstituteSystem", "root", "SuV@gmv0");
        }
    }

    public static ResultSet execute(String querry) throws Exception {
        createConnection();
        return con.createStatement().executeQuery(querry);
    }

    public static Integer executeIUD(String querry) throws Exception {
        createConnection();
        return con.createStatement().executeUpdate(querry);

    }

}
