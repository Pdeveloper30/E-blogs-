package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.User.UserDetails;

public class UserDAO {

	        //int a      
	 private Connection conn;
	 

	    public static Connection getConn(){  
	        Connection conn=null;  
	        try{  
	        	Class.forName("com.mysql.jdbc.Driver");
	             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eblogs","root","12345pnn"); //2 
	        }
	        catch(Exception e)
	        {System.out.println(e);}  
	        return conn;  
	    } 
	 
	 
	 
	 
	 
	 
	 
	 

	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	 
	 public boolean addUser(UserDetails u) //
	 {
		 boolean f=false;
		 
		 try
		 {
			  // Connection conn  =UserDAO.getConn();
			 
			 //String query="insert into user(name,email,password) values(?,?,?)";
			 
			 PreparedStatement ps=conn.prepareStatement("insert into user(name,email,password) values(?,?,?)");
			 ps.setString(1, u.getName());
			 ps.setString(2, u.getEmail());
			 ps.setString(3, u.getPassword());
			int i= ps.executeUpdate();
			 //0==1
			 if(i==1)
			 {
				 f=true;
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return f;
	 }
	 
	 public UserDetails loginUser(UserDetails u)
	 {
		 UserDetails user=null;
		 
		 try
		 {                                         //priya    and 1234      
			 String query="select * from user where email=? and password=?";
			 PreparedStatement ps=conn.prepareStatement(query);
			 ps.setString(1, u.getEmail());
			 ps.setString(2, u.getPassword());
			 
			 ResultSet rs= ps.executeQuery();
			 
			 if(rs.next())
			 {
				 user= new UserDetails();
				
				 
				 user.setId(rs.getInt("id"));				 
				 user.setName(rs.getString("name"));
				 user.setEmail(rs.getString("email"));
				 user.setPassword(rs.getString("password"));
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
		 
		 return user;
		 
	 }
	 
}
