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
import Bean.pizzaBean;

public class pizzaDao 
{
    public pizzaBean[] query(pizzaBean p,int[] count) throws SQLException
    {
       // JDBC driver name and database URL
       final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
       final String DB_URL="jdbc:mysql://localhost:3306/TEST";
       //  Database credentials
       final String USER = "root";
       final String PASS = "mysql";
	  // String user="hi";
       Connection  conn = null;
       Statement stmt = null;
	   int flag = 0;
	   //int count=0;
	   int pid=p.getPid();
       String pname = p.getPname();
       String category = p.getCategory();
	   String sub_category =p.getSub_category();
	   float price=p.getPrice();
	   String desc = p.getDescription();
	    pizzaBean[] pb = new pizzaBean[100];
	   
	   
       try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         // Execute SQL query
          stmt = conn.createStatement();
         String sql,where="";  
		 
         sql = "SELECT * FROM pizza";
		 if(pid!=-1)
		 {
			 where =  " where pid=" + pid;
			 flag=1;
		 }
		 if(pname!=null)
		 {
			 if(flag==1){
				 where = where + " and pname='" + pname + "'";
				 
			 }
			 else{
				 where =  " where pname='" + pname + "'";
				 flag=1;
			 }
		 }
		 
		 if(sub_category!=null)
		 {
			 if(flag==1){
				 where = where + " and sub_category like '" + sub_category + "%'";
				
			 }
			 else{
				 where =  " where sub_category like '" + sub_category + "%'";
				  flag =1;
			 }
		 }
		 if(category!=null)
		 {
			 if(flag==1){
				 where = where + " and category= '" + category + "'";
				 
			 }
			 else{
				 sql = "SELECT distinct(sub_category) from pizza";
				 where =  " where category='" + category + "'";
				 flag=1;
			 }
		 }
		 
		 sql = sql + where;
         ResultSet rs = stmt.executeQuery(sql);
		
         // Extract data from result set
		 if(category==null || category!=null & sub_category!=null){
			 while(rs.next())
			 {
				//Retrieve by column name
				String name,cat,subcat,description; 
				int id;
				float pri;
				id = rs.getInt("pid");
				name = rs.getString("pname");
				cat = rs.getString("category");                              
				subcat = rs.getString("sub_category");
				description = rs.getString("description");
				pri = rs.getFloat("price");
				pb[count[0]] = new pizzaBean(id,name,cat,subcat,pri,description);
				count[0]++;
				
				
			}
		}
		else{
			while(rs.next())
			 {
				//Retrieve by column name
				                             
				String subcat = rs.getString("sub_category");
				pb[count[0]] = new pizzaBean(-1,null,null,subcat,0,null);
				
				count[0]++;
				
				
			}
		}
		rs.close();
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
	   return pb;
    }
}


