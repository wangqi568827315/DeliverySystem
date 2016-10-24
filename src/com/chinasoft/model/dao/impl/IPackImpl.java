package com.chinasoft.model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.management.modelmbean.ModelMBean;
import javax.swing.table.DefaultTableModel;

import com.chinasoft.controller.PublicAttributes;
import com.chinasoft.model.DBFactory.DBFactory;
import com.chinasoft.model.dao.IEmployee;
import com.chinasoft.model.dao.IPack;
import com.chinasoft.util.DateTime;
import com.chinasoft.view.InitMainChild;

public class IPackImpl implements IPack {

	static Connection connection = DBFactory.getInstance();
	
	private static IEmployee iEmpDao = new IEmployeeImpl();
	

	
	@Override
	public boolean addExp(String orderno,String recname, String recphone, String recaddress,
			String posname, String posphone, String posaddress, String commit) {
		String sql1 = "insert Pack select ?,?,?,?,?,?,?,?,?,?,?,?";
		String sql2 = "select a.e_no from Employee as a , Upassword as b where a.e_no = b.e_no and b.u_user = ?";
		int temp = 0;
		try {
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			ps2.setString(1, PublicAttributes.user);
			
			ResultSet res1 = ps2.executeQuery();
			while(res1.next()){
				temp = res1.getInt(1);
			}
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, temp);
			ps1.setString(2, null);
			ps1.setString(3, orderno);
			ps1.setString(4, recname);
			ps1.setString(5, recphone);
			ps1.setString(6, recaddress);
			ps1.setString(7, posname);
			ps1.setString(8, posphone);
			ps1.setString(9, posaddress);
			ps1.setString(10, commit);
			ps1.setString(11, "未派件");
			ps1.setString(12, DateTime.sqlDateTime());
			ps1.executeUpdate();
			
			ps1.close();
			ps2.close();
			res1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
		return true;
	}

	@Override
	public void showPack(DefaultTableModel model ) {
		String sql1 = "select p_orderNo,p_recName,p_recPhone,p_recAddress,p_PosName,p_posPhone,p_Address,"
				+ "p_date,p_commit  from Pack  where p_state = '未派件'    order by   p_date desc";
		String sql2 = "select p_orderNo,p_recName,p_recPhone,p_recAddress,p_PosName,p_posPhone,p_Address,"
				+ "p_date,p_commit  from Pack  where p_state = '已派未收'    order by   p_date desc";
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
				str7 = res1.getString(7);
				str8 = "未派送";
				str9 = res1.getString(9);
				
				row1 = new Object[]{str1, str2, str3, str4, str5, str6, str7,str8, str9 };
				model.addRow(row1);
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
				st7 = res2.getString(7);
				st8 = res2.getString(8).split(" ")[0];
				st9 = res2.getString(9);
				row2 = new Object[]{st1, st2, st3, st4, st5, st6, st7,st8, st9 };
				model.addRow(row2);

			}
			
			s2.close();
			res2.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
	@Override
	public boolean updatePack(String[] strs) {
		
		String sql1 = "{call [updatePack](?,?,?,?,?,?,?,?)}";
		
		try {
			CallableStatement cs1 = connection.prepareCall(sql1);
			cs1.setString(1, strs[0]);
			cs1.setString(2, strs[1]);
			cs1.setString(3, strs[2]);
			cs1.setString(4, strs[3]);
			cs1.setString(5, strs[4]);
			cs1.setString(6, strs[5]);
			cs1.setString(7, strs[6]);
			cs1.setString(8, strs[7]);
			
			cs1.executeUpdate();
			
			
			cs1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deletePack(String delno) {
		
		String sql1 = "delete from Pack where p_orderNo = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setString(1, delno);
			
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
	public boolean updateState(int way, String no) {
		
		String sql1 = "update Pack set p_state = '已派未收',d_no=?,p_date = ? where p_orderNo = ?";
		String sql2 = "select a.e_no from Employee as a, Upassword as b where a.e_no = b.e_no and b.u_user = ?";
		String s1l3 = "select e_count from Employee where e_no = ?";
		
	
		
		try {
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			ps2.setString(1, PublicAttributes.user);
			
			ResultSet res = ps2.executeQuery();
			while(res.next()){
				PublicAttributes.userno = res.getInt(1);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			int temp = 0;
			PreparedStatement ps3 = connection.prepareStatement(s1l3);
			ps3.setInt(1, PublicAttributes.userno);
			ResultSet res1 = ps3.executeQuery();
			while(res1.next()){
				temp = res1.getInt(1);
			}
			
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, way);
			ps1.setString(2, DateTime.sqlDateTime());
			ps1.setString(3, no);
			
			ps1.executeUpdate();
			
			temp += 1;
			
			iEmpDao.updateCount(temp, PublicAttributes.userno);
			
			ps1.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

	@Override
	public boolean signPack(String no) {
		
		String sql1 = "update  Pack set p_state = '已签收',p_date = ? where p_orderNo = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setString(1, DateTime.sqlDateTime());
			ps1.setString(2, no);
			
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
	public void showHistory(DefaultTableModel model) {
		model.setRowCount(0);
		String sql1 = "{call [selectHistory]}";
		String str1 = null, str2 = null, str3 = null, str4 = null, str5 = null, 
				str6 = null, str7 = null, str8 = null, str9 = null,str10 = null, str11 = null, str12 = null, str13 = null;
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
				str7 = res1.getString(7);
				str8 = res1.getString(8);
				str9 = res1.getString(9);
				str10 = res1.getString(10);
				str11 = res1.getString(11);
				str12 = res1.getString(12).split(" ")[0];
				str13 = res1.getString(13);
				
				row1 = new Object[]{str1,str2,str3,str4,str5,str6,str7,str8,str9,str10,str11,str12,str13};
				model.addRow(row1);
			}
			
			s1.close();
			res1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public boolean selectHistory(String yyyy, String MM, String dd) {
		int zeronum = 0;
		
		ResultSet res1 = null;
		
		InitMainChild.model3.setRowCount(0);
		String sql = null;
		String str1 = null, str2 = null, str3 = null, str4 = null, str5 = null, 
				str6 = null, str7 = null, str8 = null, str9 = null,str10 = null, str11 = null, str12 = null, str13 = null;
		Object[] row1 = null;
		
		Map<Integer,String> maps = new HashMap<Integer,String>();
		maps.put(0, yyyy);
		maps.put(1, MM);
		maps.put(2, dd);
		
		for(int i = 0 ; i < maps.keySet().size() ; i++){
			if(maps.get(i).equals("0")){
				zeronum++;
			}
		}
		switch (zeronum) {
		case 0: sql = "{call [selHistoryByDate](?,?,?)}";
			break;
		case 1: sql = "{call [selHistoryByDate_3](?,?,?)}";
			break;
		case 2: sql = "{call [selHistoryByDate_2](?,?,?)}";
			break;
		case 3: {
			showHistory(InitMainChild.model3);
			return false;
		}
		}
		
		
		try {
			CallableStatement cs1 = connection.prepareCall(sql);
			cs1.setInt(1, Integer.parseInt(yyyy));
			cs1.setInt(2, Integer.parseInt(MM));
			cs1.setInt(3, Integer.parseInt(dd));
			
			res1 = cs1.executeQuery();
			while(res1.next()){
				str1 = res1.getString(1);
				str2 = res1.getString(2);
				str3 = res1.getString(3);
				str4 = res1.getString(4);
				str5 = res1.getString(5);
				str6 = res1.getString(6);
				str7 = res1.getString(7);
				str8 = res1.getString(8);
				str9 = res1.getString(9);
				str10 = res1.getString(10);
				str11 = res1.getString(11);
				str12 = res1.getString(12);
				str13 = res1.getString(13);
				
				row1 = new Object[]{str1,str2,str3,str4,str5,str6,str7,str8,str9,str10,str11,str12,str13};
				InitMainChild.model3.addRow(row1);
			}
			cs1.close();
			res1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
}








