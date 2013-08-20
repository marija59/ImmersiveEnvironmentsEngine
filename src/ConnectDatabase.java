/* ConnectDatabase.java 
 */  
//package com.javaworkspace.connecthsqldb;  
  
import java.sql.*;
  
/** 
 * @author www.javaworkspace.com 
 *  
 */  
public class ConnectDatabase {  
	public  Connection con = null;
    public  Statement st = null;
    public  ResultSet rs = null;

    public  String url = "jdbc:mysql://localhost:3306/test";
    public  String user = "root";
    public  String password = "Sre90koto$";
    
    public ConnectDatabase(){
    	try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();             
        } catch (SQLException ex) {
        	System.out.println("Connection not established!"+ex);
        } 	
    }
    
    
    public static void main(String[] args) {
        
    }
    
    public void InsertRow(String sql)
    {
    	//int val = st.executeUpdate(sql);
    	//System.out.println("1 row affected");
    }
    public void SelectData(String sql)
    {
    	try {  
             ResultSet resultSet = st.executeQuery(sql);  
            while (resultSet.next()) {  
                //System.out.println("EMPLOYEE NAME:" + resultSet.getString("EMPNAME"));  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    
}  