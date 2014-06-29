/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimension.webservice.security;

import com.dimension.webservices.dbQuery.GStoreDBConnect;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Deepak
 */
public class GSTokenGeneration {
    
    public String generateToken(String usrName, String dob, String timeStamp, String deviceName) throws SQLException{
        // This is to be used in db operations.
        //GStoreDBConnect.conn;
        String token = null;
        CallableStatement cStmt = GStoreDBConnect.conn.prepareCall("{call generateToken(?, ?, ?, ?)}");       
        cStmt.setString(1, usrName);
        cStmt.setString(2, dob);
        cStmt.setString(3, deviceName);                
        //cStmt.setString(4, deviceName);
        cStmt.registerOutParameter(4, Types.VARCHAR);
        cStmt.registerOutParameter("tokenReturned", Types.VARCHAR);
        cStmt.execute();
        token = cStmt.getString("tokenReturned");
        return token;
    }    
}
