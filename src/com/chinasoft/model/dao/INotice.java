package com.chinasoft.model.dao;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public interface INotice {

	public abstract boolean createNotice(String title, String article);
	
	public abstract void showNotice(JTextField jtf, JTextArea jta);
	
	public abstract void showNoticeTitle();
	
}
