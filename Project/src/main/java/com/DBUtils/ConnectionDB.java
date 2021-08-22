package com.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {
    public static Connection getConnection(){
        Connection cn = null;
        try {
//            characterEncoding=utf8mb4_unicode_ci
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://mysql5042.site4now.net/";
//            String url = "mysql5045.site4now.net/";
            String nameDTB = "db_a78d71_project";
            String userName = "a78d71_project";
            String pass = "261172Abc-";
            cn = DriverManager.getConnection(url + nameDTB, userName, pass);
        }catch (Exception e){
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, e);
        }
        return cn;
    }

    public static void main(String[] args) {
        System.out.println( ConnectionDB.getConnection());
    }
}
