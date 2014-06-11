/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimension.webservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Deepak Jha @Dimension Labs !!!
 */
public class GStoreDBConnect {
    public String userName;
    public String detail;
    ResultSet rs;
    Statement st;
    Connection conn;
    /* main method was to test the connection if that works or not!
        public static void main(String arg[]){
        GStoreDBConnect gc = new GStoreDBConnect();
        gc.makeConnect("com.mysql.jdbc.Driver",3306,"grocerystore_db", "root", "Creator@123");
    }*/
    public GStoreDBConnect(String driverName, int port, String dbName, String dbUsrName, String dbPwd){
        //Loding the driver.        
        // After the load of driver we are connecting with the database.
        try{
            Class.forName(driverName);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/"+dbName, dbUsrName, dbPwd);
        }
        // Catch for registering the driver name !
        catch(ClassNotFoundException driverException){
            System.out.println("Exception arrived: " +driverException);
        }
        // Catch  Jha @Dimension Labs !!!!for connecting with the database server !
        catch(SQLException connException){
            System.out.println("Exception arrived: " +connException);
        }        
    }
    // This is to check the user name and password comes from the front end application.
    public Boolean checkCredentials(String usrName, String loginPassword){
        String DBpassword=null;
        try{         
            st = conn.createStatement();
            rs = st.executeQuery("select * from user where UserName=\'"+usrName+"\'");
                while (rs.next()){                    
                    DBpassword = rs.getString("Password");
                    String lastName = rs.getString("EmailID");                                               
                    // print the results
                    System.out.format("%s Login Password, %s Last Name\n", loginPassword, lastName);
                }
            st.close();
        }catch(SQLException connException){
            System.out.println("Exception arrived: " +connException);
        }        
        if (DBpassword.equals(loginPassword) && DBpassword !=null){
            return true;
        }else{
            return false;
        }
    }
    
}
