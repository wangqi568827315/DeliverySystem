package com.chinasoft.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.chinasoft.model.DBFactory.DBFactory;
import com.chinasoft.model.dao.IEmployee;
import com.chinasoft.view.SAInitMainChild;

public class IEmployeeImpl implements IEmployee {

	static Connection connection = DBFactory.getInstance();
	
	
	@Override
	public boolean updateEmp(int no, String name, String sex , String phone, String idcard, String address) {
		String sql1 = "update Employee set e_name=?,e_sex=?,e_phone=?,e_idcard=?,e_address=?  where e_no=?";
		String sql2 = "select u_user from Upassword where e_no = ?";
		String temp = null;
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setString(1, name);
			ps1.setString(2, sex);
			ps1.setString(3, phone);
			ps1.setString(4, idcard);
			ps1.setString(5, address);
			ps1.setInt(6, no);
			ps1.execute();
			
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			ps2.setInt(1, no);
			ResultSet res2 = ps2.executeQuery();
			while(res2.next()){
				temp = res2.getString(1);
			}
			showEmp(temp);
			
			ps1.close();
			ps2.close();
			res2.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

	@Override
	public String[] showEmp(String user) {
		String sql1 = "select a.* from Employee as a,Upassword as b where a.e_no = b.e_no and b.u_user = ? ";
		String str1 = null,str2 = null,str3 = null,str4 = null,str5 = null,str6 = null,str7 = null;
		String[] strs = null;
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setString(1, user);
			
			ResultSet res1 = ps1.executeQuery();
			while(res1.next()){
				str1 = String.valueOf(res1.getInt(1));
				str2 = res1.getString(2);
				str3 = res1.getString(3);
				str4 = res1.getString(4);
				str5 = res1.getString(5);
				str6 = res1.getString(6);
				str7 = res1.getString(7);
			}
			strs = new String[]{str1,str2,str3,str4,str5,str6,str7};
			
			res1.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strs;
	}

	@Override
	public  void updateCount(int count, int no) {
		String sql1 = "update Employee set e_count = ? where e_no = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, count);
			ps1.setInt(2, no);
			
			ps1.executeUpdate();
			
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void SAshowEmp() {
		SAInitMainChild.model2.setRowCount(0);
		
		String sql1 = "select e_no,e_name,e_sex,e_phone,e_idcard,e_address,e_regdate,e_state  ,"
				+ "case  when e_count is NULL then 0 else e_count end from Employee where e_state = '在职' "
				+ "and e_role = 0 order by e_no";
		String sql2 = "select e_no,e_name,e_sex,e_phone,e_idcard,e_address,e_regdate,e_state  ,"
				+ "case  when e_count is NULL then 0 else e_count end from Employee where e_state = '离职' "
				+ "and e_role = 0 order by e_no";
		String str1 = null, str2 = null, str3 = null, str4 = null, str5 = null, str6 = null, str7 = null, str8 = null, str9 = null;
		Object[] row1 = null;
	
		try {
			Statement s1 = connection.createStatement();
			
			ResultSet res1 = s1.executeQuery(sql1);
			while(res1.next()){
				str1 = res1.getString(1);
				str2 = res1.getString(2);
				str3 = res1.getString(3);
				str4 = res1.getString(4);
				str5 = res1.getString(5);
				str6 = res1.getString(6);
				str7 = res1.getString(7).split(" ")[0];
				str8 = res1.getString(8);
				str9 = res1.getString(9);
				row1 = new Object[]{str1, str2, str3, str4, str5, str6, str7,str8, str9 };
				SAInitMainChild.model2.addRow(row1);
			}
			
			
			s1.close();
			res1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String st1 = null, st2 = null, st3 = null, st4 = null, st5 = null, st6 = null, st7 = null, st8 = null, st9 = null;
			Object[] row2 = null;
			Statement s2 = connection.createStatement();
			
			ResultSet res2 = s2.executeQuery(sql2);
			while(res2.next()){
				st1 = res2.getString(1);
				st2 = res2.getString(2);
				st3 = res2.getString(3);
				st4 = res2.getString(4);
				st5 = res2.getString(5);
				st6 = res2.getString(6);
				st7 = res2.getString(7).split(" ")[0];
				st8 = res2.getString(8);
				st9 = res2.getString(9);
				row2 = new Object[]{st1, st2, st3, st4, st5, st6, st7,st8, st9 };
				SAInitMainChild.model2.addRow(row2);

			}
			
			s2.close();
			res2.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public boolean fireEmp(int no) {
		
		String sql1 = "update Employee set e_state = '离职' where e_no = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, no);
			
			ps1.executeUpdate();
			
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public int checkRole(String username) {
		int role = 0;
		String sql1 = "select a.e_role from Employee as a,Upassword as b  where a.e_no = b.e_no and b.u_user = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setString(1, username);
			
			ResultSet res1 = ps1.executeQuery();
			while(res1.next()){
				role = res1.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return role;
	}

	@Override
	public void selByNo(int no) {
		
		SAInitMainChild.model2.setRowCount(0);
		
		String sql1 = "select e_no,e_name,e_sex,e_phone,e_idcard,e_address,e_regdate,e_state  ,"
				+ "case  when e_count is NULL then 0 else e_count end from Employee where e_role = 0 and e_no = ?"
				+ " order by e_no";
		
		String str1 = null, str2 = null, str3 = null, str4 = null, str5 = null, str6 = null, str7 = null, str8 = null, str9 = null;
		Object[] row1 = null;
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, no);
			ResultSet res1 = ps1.executeQuery();
			while(res1.next()){
				str1 = res1.getString(1);
				str2 = res1.getString(2);
				str3 = res1.getString(3);
				str4 = res1.getString(4);
				str5 = res1.getString(5);
				str6 = res1.getString(6);
				str7 = res1.getString(7).split(" ")[0];
				str8 = res1.getString(8);
				str9 = res1.getString(9);
				row1 = new Object[]{str1, str2, str3, str4, str5, str6, str7,str8, str9 };
				SAInitMainChild.model2.addRow(row1);
			}
			
			
			res1.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}








