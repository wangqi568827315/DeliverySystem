package com.chinasoft.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.chinasoft.model.DBFactory.DBFactory;
import com.chinasoft.model.dao.IUpassword;
import com.chinasoft.model.entity.EMPLOYEE;
import com.chinasoft.util.DateTime;

public class IUpasswordImpl implements IUpassword {

	static Connection connection = DBFactory.getInstance();
	
	
	@Override
	public boolean checkPwd(String user, String pwd) {
		String correctPwd = null;
		try {
			PreparedStatement pStatement = connection.prepareStatement("select u_pwd from Upassword where u_user = ?");
			pStatement.setString(1, user);
			ResultSet res1 = pStatement.executeQuery();
			
			while(res1.next()){
				correctPwd = res1.getString(1);
			}
			if(pwd.equals(correctPwd)){
				return true;
			}
			
			pStatement.close();
			res1.close();
			
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public boolean regist(String user,String password,String name, String sex, String phone, String idcard, String address, int role) {
		String sql1 = "insert into dbo.Employee select ?,?,?,?,?,?,?,0,?";
		String sql2 = "insert into dbo.Upassword select ?,?,?";
		try {
			PreparedStatement pStatement1 = connection.prepareStatement(sql1);
			PreparedStatement pStatement2 = connection.prepareStatement(sql2);
			pStatement1.setString(1, name);
			pStatement1.setString(2, sex);
			pStatement1.setString(3, phone);
			pStatement1.setString(4, idcard);
			pStatement1.setString(5, address);
			pStatement1.setString(6, DateTime.sqlDateTime() );
			pStatement1.setString(7, "在职");
			pStatement1.setInt(8, role);
			pStatement1.executeUpdate();
			
			PreparedStatement temp = connection.prepareStatement("select TOP 1 [e_no]  from [dbo].[Employee]  where [e_name] = ?  order by [e_regdate] desc");
			temp.setString(1, name);
			
			ResultSet res1 = temp.executeQuery();
			while(res1.next()){
				pStatement2.setInt(1, res1.getInt(1));
			}
			pStatement2.setString(2, user);
			pStatement2.setString(3, password);
			
			
			pStatement2.executeUpdate();
			
			pStatement1.close();
			pStatement2.close();
			res1.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean changePwd(String user, String pwd, String newpwd) {
		
		String sql1 = "select u_pwd from Upassword where u_user = ?";
		String sql2 = "update Upassword set u_pwd = ? where u_user = ?";
		String temp = "";
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			ps1.setString(1, user);
			ResultSet res1 = ps1.executeQuery();
			while(res1.next()){
				temp = res1.getString(1);
			}
			if(!pwd.equals(temp)){
				return false;
			}else{
				ps2.setString(1, newpwd);
				ps2.setString(2, user);
				ps2.executeUpdate();
			}
			
			
			res1.close();
			ps1.close();
			ps2.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}


	@Override
	public boolean checkReUser(String user) {
		
		String sql1 = "select u_user from Upassword";
		
		try {
			Statement s1 = connection.createStatement();
			
			ResultSet res1 = s1.executeQuery(sql1);
			while(res1.next()){
				if(res1.getString(1).equals(user))  return false;
			}
			
			res1.close();
			s1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}


	@Override
	public void deleteUser(int no) {
		String sql1 = "delete Upassword where e_no = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, no);
			
			ps1.executeUpdate();
			
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}



}














