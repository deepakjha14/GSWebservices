/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimension.webservices.dbQuery;

import com.dimension.webservices.security.GSTokenGeneration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
/**
 *
 * @author Deepak Jha @Dimension Labs !!!
 */
public class GStoreDBConnect {
    public String userName;
    public String detail;    
    public static String token;
    public CallableStatement cs;
    ResultSet rs;
    Statement st;
    public static Connection conn;
    /* main method was to test the connection if that works or not!
        public static void main(String arg[]){
        GStoreDBConnect gc = new GStoreDBConnect();
        gc.makeConnect("com.mysql.jdbc.Driver",3306,"grocerystore_db", "root", "Creator@123");
    }*/
    public GStoreDBConnect(){
        // This constructor has been added because of the token requirement of the project !
        // token="empty";        
    }
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
    public String checkCredentials(String usrName, String loginPassword, String timeStamp,String deviceName){
        String DBpassword=null;        
        String emailID = null;
        rs=null;
        try{         
            st = conn.createStatement();
            rs = st.executeQuery("select * from user where UserName=\'"+usrName+"\'");                                 
            // when there is something in the result set we will loop to find the answer.
            if(rs.next()){
                    DBpassword = rs.getString("Password");
                    emailID = rs.getString("EmailID");                                                           
                // print the results
                if(DBpassword.equals(loginPassword) ){
                    System.out.println("Password Match !!!"); 
                    GSTokenGeneration dbToken = new GSTokenGeneration();
                    token= dbToken.generateToken(usrName,rs.getString("DOB"),timeStamp,deviceName);
                } else {                
                    System.out.format("%s Login Password, %s Email ID\n", loginPassword, emailID);
                    token="empty";
                    rs=null;
                }
            }
            st.close();
        }catch(SQLException connException){
            System.out.println("Exception arrived: " +connException.getMessage());
        }        
        return token;
    } // This should be removed after completion !
    /*public String generateToken(String usrName, rs, String timeStamp, String deviceName){
    GSTokenGeneration dbToken = new GSTokenGeneration(usrName,rs.getString("DOB"),timeStamp,deviceName);
     return dbToken.toString();   
    }*/
}