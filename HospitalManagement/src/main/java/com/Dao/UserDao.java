package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.User;

public class UserDao {
  private Connection conn;
  
  public UserDao(Connection conn) {
	  super();
	  this.conn=conn;
  }
  
  public boolean userRegister(User u) {
	  boolean f = false;
	  String sql = "insert into user_dtls(full_name,email,password) values(?,?,?)";
	  try {
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, u.getFullName());
		ps.setString(2, u.getEmail());
		ps.setString(3, u.getPassword());
		
		int i = ps.executeUpdate();
		
		if(i==1) {
			f = true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  return f;
  }
  
  
  public User userLogin(String email, String password) {
	  User u = null;
	  
	  try {
		  String sql = "select * from user_dtls where email=? and password=?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, email);
		  ps.setString(2, password);
		  
		  ResultSet rs = ps.executeQuery();
		  
		  while(rs.next()) {
			  u = new User();
			  u.setId(rs.getInt(1));
			  u.setFullName(rs.getString(2));
			  u.setEmail(rs.getString(3));
			  u.setPassword(rs.getString(4));
		  }
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  
	  return u;
  }
  
  public boolean checkOldPassword(int userid, String oldpassword) {
	  boolean f = false;
	  try {
		  String sql = "select * from user_dtls where id=? and password=?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setInt(1, userid);
		  ps.setString(2, oldpassword);
		  
		  ResultSet rs = ps.executeQuery();
		  while(rs.next()) {
			  f = true;
		  }
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  
	  return f;
  }
  
  public boolean changePassword(int userid, String newpassword) {
	  boolean f = false;
	  try {
		  String sql = "update user_dtls set password=? where id=?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, newpassword);
		  ps.setInt(2, userid);
		  
		  int i = ps.executeUpdate();
		  if(i ==1) {
			  f=true;
		  }
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  
	  return f;
  }
}
