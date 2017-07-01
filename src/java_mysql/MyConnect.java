/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class MyConnect {

    private final String className = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/java_mysql";
    private final String user = "root";
    private final String pass = "";

    private Connection connection;
    
    /**
     * Connect database
     */
    private void connect() {
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connect success");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
        } catch (SQLException ex) {
            System.out.println("Error connection");
        }
    }
    
    public static void main(String[] args) {
        MyConnect myConnect = new MyConnect();
        myConnect.connect();
    }
}
