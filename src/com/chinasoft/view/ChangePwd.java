package com.chinasoft.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.chinasoft.model.dao.impl.IUpasswordImpl;

public class ChangePwd {

	
	public static void changePwd(){
		final JFrame myFrame = new JFrame("修改密码");
		myFrame.setBounds(0, 0, 450, 300);
		myFrame.setLocationRelativeTo(null);
		myFrame.setResizable(false);
		Container container = myFrame.getContentPane();
		
		JPanel jpt = new JPanel();
		jpt.setBorder(BorderFactory.createEmptyBorder(25,20,25,20));
		
		JPanel jp1 = new JPanel();
		jp1.setPreferredSize(new Dimension(450,40));
		JPanel jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(450,40));
		JPanel jp3 = new JPanel();
		jp3.setPreferredSize(new Dimension(450,40));
		JPanel jp4 = new JPanel();
		jp4.setPreferredSize(new Dimension(450,40));
		JPanel jp5 = new JPanel();
		jp4.setPreferredSize(new Dimension(450,40));
		
		JLabel jlab1 = new JLabel("账号:");
		JLabel jlab2 = new JLabel("原密码:");
		JLabel jlab3 = new JLabel("新密码:");
		JLabel jlab4 = new JLabel("确认密码:");
		JButton confirm = new JButton("确认");
		
		Style.regist_label_style(jlab1);
		Style.regist_label_style(jlab2);
		Style.regist_label_style(jlab3);
		Style.regist_label_style(jlab4);
		Style.login_btn_style(confirm);
		
		final JTextField tf1 = new JTextField();
		final JPasswordField tf2 = new JPasswordField();
		final JPasswordField tf3 = new JPasswordField();
		final JPasswordField tf4 = new JPasswordField();
		
		Style.regist_textfield_style(tf1);
		Style.regist_textfield_style(tf2);
		Style.regist_textfield_style(tf3);
		Style.regist_textfield_style(tf4);
		
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			if(!String.valueOf(tf3.getPassword()).equals(String.valueOf(tf4.getPassword()))){
				JOptionPane.showMessageDialog(null, "两次输入的密码不一样!!");
			}else if(new IUpasswordImpl().changePwd(tf1.getText(), String.valueOf(tf2.getPassword()), String.valueOf(tf4.getPassword())) == true){
					JOptionPane.showMessageDialog(null, "密码修改成功!");
					myFrame.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "原账号/密码不对,请仔细核对!");
					tf1.setText("");	tf2.setText("");	tf3.setText("");	tf4.setText("");
				}
				
			
			}
		});
		
		
		
		jp1.add(jlab1);
		jp1.add(tf1);
		jp2.add(jlab2);
		jp2.add(tf2);
		jp3.add(jlab3);
		jp3.add(tf3);
		jp4.add(jlab4);
		jp4.add(tf4);
		jp5.add(confirm);
		
		jpt.add(jp1);
		jpt.add(jp2);
		jpt.add(jp3);
		jpt.add(jp4);
		jpt.add(jp5);
		
		container.add(jpt);
		
		myFrame.setVisible(true);
	}
	
}










