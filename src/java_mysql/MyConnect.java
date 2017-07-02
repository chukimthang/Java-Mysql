/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class MyConnect {

    public final String className = "com.mysql.jdbc.Driver";
    public final String url = "jdbc:mysql://localhost:3306/java_mysql";
    public final String user = "root";
    public final String pass = "";

    public String table = "tbl_student";
    public Connection connection;

    /**
     * Connect database
     */
    public void connect() {
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

    /**
     * Show all list data
     *
     * @param rs
     */
    public void showData(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println(rs.getString(1) + " - "
                        + rs.getString(2) + " - "
                        + rs.getFloat(3));
            }
        } catch (SQLException ex) {
            System.err.println("Error show data \n" + ex.toString());
        }
    }

    /**
     * Get list data
     *
     * @return
     */
    public ResultSet getData() {
        ResultSet rs = null;
        String sql = "SELECT * FROM " + table;
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("select error \n" + ex.toString());
        }
        return rs;
    }

    /**
     *
     * @param id
     * @return
     */
    public ResultSet getDataId(int id) {
        ResultSet rs = null;
        String sql = "SELECT * FROM " + table + " WHERE id = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            System.err.println("Select error \n" + ex.toString());
        }
        return rs;
    }

    /**
     * Insert record
     *
     * @param student
     */
    public void insert(Student student) {
        String sql = "INSERT INTO " + table + " VALUE(?, ?, ?)";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, student.getId());
            pst.setString(2, student.getName());
            pst.setFloat(3, student.getPoint());
            if (pst.executeUpdate() > 0) {
                System.out.println("Insert success!");
            } else {
                System.out.println("Insert error!");
            }
        } catch (SQLException ex) {
            System.err.println("Insert error \n" + ex.toString());
        }
    }

    /**
     * Update record
     *
     * @param student
     * @param id
     */
    public void update(int id, Student student) {
        String sql = "UPDATE " + table + " SET name = ?, point = ? WHERE id = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, student.getName());
            pst.setFloat(2, student.getPoint());
            pst.setInt(3, student.getId());
            if (pst.executeUpdate() > 0) {
                System.out.println("Update success!");
            } else {
                System.out.println("Update error!");
            }
        } catch (SQLException ex) {
            System.err.println("Update error \n" + ex.toString());
        }
    }

    /**
     * Delete record by id
     *
     * @param id
     */
    public void delete(int id) {
        String sql = "DELETE FROM " + table + " WHERE id = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            if (pst.executeUpdate() > 0) {
                System.out.println("Delete success!");
            } else {
                System.out.println("Record not found!");
            }
        } catch (SQLException ex) {
            System.err.println("Delete error \n" + ex.toString());
        }
    }
//
//    public static void main(String[] args) {
//        MyConnect myConnect = new MyConnect();
//        myConnect.connect();
//        myConnect.showData(myConnect.getDataId(1));
        //myConnect.delete(2);
//        myConnect.insert(new Student(2, "Le Duc Phuc", (float) 8.6)); 
//        myConnect.update(3, new Student(3, "Nguyen Manh Quang", (float) 6.5));
//        myConnect.showData(myConnect.getData());
//    }
}
