package com.chinasoft.model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.chinasoft.model.DBFactory.DBFactory;
import com.chinasoft.model.dao.ISalary;
import com.chinasoft.util.DateTime;
import com.chinasoft.view.CreateSalary;
import com.chinasoft.view.InitMainChild;
import com.chinasoft.view.SAInitMainChild;

public class ISalaryImpl implements ISalary {

	static Connection connection = DBFactory.getInstance();
	
	@Override
	public void showComboItem() {
		// TODO Auto-generated method stub
		
		
		String sql1 = "select e_no, e_name from Employee where e_role = 0";
		try {
			Statement s1 = connection.createStatement();
			ResultSet res1 = s1.executeQuery(sql1);
			
			while(res1.next()){
				CreateSalary.cbmodel.addElement(res1.getString(1)+" - "+res1.getString(2));
			}
			
			res1.close();
			s1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean addSalary(int no , double basic, double bonus) {
		String sql1 = "insert into Salary select ?,?,?,?,?,?";
		String sql2 = "select e_count from Employee where e_no = ?";
		int temp = 0;
		try {
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			ps2.setInt(1, no);
			ResultSet res1 = ps2.executeQuery();
			while(res1.next()){
				temp = res1.getInt(1);
			}
			
			res1.close();
			ps2.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, no);
			ps1.setDouble(2, basic);
			ps1.setInt(3, temp);
			ps1.setDouble(4, bonus);
			ps1.setDouble(5, basic+((double)temp)*bonus);
			ps1.setString(6, DateTime.sqlDateTime());
			
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
	public void showSalary() {
		SAInitMainChild.model1.setRowCount(0);
		String sql1 = "select b.s_no,a.e_no,a.e_name,b.s_basic,b.s_count,b.s_bonus,b.s_sum,b.s_date "
				+ "from Employee as a, Salary as b where a.e_no = b.e_no order by s_date desc";
		Object[] objs = null;
		String str1 = null ,str2 = null ,str3 = null ,str4 = null ,str5 = null ,str6 = null, str7 = null ;
		try {
			Statement s1 = connection.createStatement();
			
			ResultSet res1 = s1.executeQuery(sql1);
			while(res1.next()){
				str1 = res1.getString(1);
				str2 = res1.getString(2)+" - "+res1.getString(3);
				str3 = res1.getString(4);
				str4 = res1.getString(5);
				str5 = res1.getString(6);
				str6 = res1.getString(7);
				str7 = res1.getString(8).split(" ")[0];
				objs = new Object[]{str1, str2, str3, str4, str5, str6, str7};
				SAInitMainChild.model1.addRow(objs);
			}
			
			res1.close();
			s1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean deleteSalary(int sno) {
		String sql1 = "delete Salary where s_no=?";
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, sno);
			
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
	public void checkSalary(String str1, String str2, String str3, String str4) {
		int zeronum = 0;
		
		ResultSet res1 = null;
		
		SAInitMainChild.model1.setRowCount(0);
		
		String sql = null;
		String rowstr1 = null, rowstr2 = null, rowstr3 = null, rowstr4 = null, rowstr5 = null, rowstr6 = null, rowstr7 = null;
		Object[] row1 = null;
		
		Map<Integer,String> maps = new HashMap<Integer,String>();
		maps.put(0, str1);
		maps.put(1, str2);
		maps.put(2, str3);
		maps.put(3, str4);
		
		for(int i = 0 ; i < maps.keySet().size() ; i++){
			if(maps.get(i).equals("0")){
				zeronum++;
			}
		}
		switch (zeronum) {
		case 0: sql = "{call [selSalary_4](?,?,?,?)}";
			break;
		case 1: sql = "{call [selSalary_3](?,?,?,?)}";
			break;
		case 2: sql = "{call [selSalary_2](?,?,?,?)}";
			break;
		case 3: sql = "{call [selSalary_1](?,?,?,?)}";
			break;
		case 4: sql = "{call [selSalary_0](?,?,?,?)}";
			break;
		}
		
		try {
			CallableStatement cs1 = connection.prepareCall(sql);
			cs1.setString(1, str1);
			cs1.setInt(2, Integer.parseInt(str2));
			cs1.setInt(3, Integer.parseInt(str3));
			cs1.setInt(4, Integer.parseInt(str4));
			
			res1 = cs1.executeQuery();
			while(res1.next()){
				rowstr1 = res1.getString(1);
				rowstr2 = res1.getString(2)+" - "+res1.getString(3);
				rowstr3 = res1.getString(4);
				rowstr4 = res1.getString(5);
				rowstr5 = res1.getString(6);
				rowstr6 = res1.getString(7);
				rowstr7 = res1.getString(8).split(" ")[0];
				
				row1 = new Object[]{rowstr1,rowstr2,rowstr3,rowstr4,rowstr5,rowstr6,rowstr7};
				SAInitMainChild.model1.addRow(row1);
			}
			
			cs1.close();
			res1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}








