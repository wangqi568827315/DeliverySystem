package com.chinasoft.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.chinasoft.controller.PublicAttributes;
import com.chinasoft.model.DBFactory.DBFactory;
import com.chinasoft.model.dao.INotice;
import com.chinasoft.view.InitMainChild;
import com.chinasoft.view.SAInitMainChild;

public class INoticeImpl implements INotice {

	private static Connection connection = DBFactory.getInstance();
	
	@Override
	public boolean createNotice(String title, String article) {
		String sql1 = "insert into Notice select ?,?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setString(1, title);
			ps1.setString(2, article);
			
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
	public void showNotice(JTextField jtf, JTextArea jta) {
		String sql1 = "select n_title,n_article from Notice where n_no = ? ";
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, PublicAttributes.noticeNo);
			
			ResultSet res1 = ps1.executeQuery();
			
			while(res1.next()){
				jtf.setText(res1.getString(1));
				jta.setText(res1.getString(2));
			}
			res1.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public void showNoticeTitle() {
		// TODO Auto-generated method stub
		String sql1 = "select n_title,n_article from Notice";
		String[] strs = null;
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			
			ResultSet res1 = ps1.executeQuery();
			int j = 0;
			while(res1.next()){
				j++;
			}
			InitMainChild.notice_length = j;
			strs = new String[j];
			
			SAInitMainChild.jbtns = new JButton[j];
			
			ResultSet res2 = ps1.executeQuery();
			int i = 0;
			while(res2.next()){
				strs[i] = res2.getString(1);
				SAInitMainChild.jbtns[j-i-1] = new JButton(res2.getString(1));
				i++;
			}
			
			res1.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
















