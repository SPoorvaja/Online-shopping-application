/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import Bean.loginBean;

public class loginDao 
{
    public int userAuthentication(loginBean l) throws SQLException
    {
       // JDBC driver name and database URL
       final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
       final String DB_URL="jdbc:mysql://localhost:3306/TEST";
       //  Database credentials
       final String USER = "root";
       final String PASS = "mysql";
	   String user="hi";
       Connection  conn = null;
       Statement stmt = null;
       String uname = l.getUserName();
       String upasswd = l.getPassword();
       try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         // Execute SQL query
          stmt = conn.createStatement();
         String sql;     
         sql = "SELECT * FROM utable";
         ResultSet rs = stmt.executeQuery(sql);
		 
         // Extract data from result set
         while(rs.next())
         {
            //Retrieve by column name
            
           user = rs.getString("username");
            String pass = rs.getString("password");                              
           if(uname.equals(user)&& upasswd.equals(pass))            
              return 1;         
			
			
        }
       }
       catch(SQLException se)
       {
         //Handle errors for JDBC
          se.printStackTrace();
       }
       catch(Exception e)
       {
         //Handle errors for Class.forName
         e.printStackTrace();
       }         
	   finally{
		   conn.close();
		   stmt.close();
	   }
       return 0;
    }
}


