package com.chinasoft.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.chinasoft.model.dao.IUpassword;
import com.chinasoft.model.dao.impl.IUpasswordImpl;
import com.chinasoft.util.RegistPattern;

public class Regist {

	static JFrame myFrame = null;
	static JLabel label_name = null;
	static JLabel label_sex = null;
	static JLabel label_phone = null;
	static JLabel label_idcard = null;
	static JLabel label_address = null;
	static JCheckBox clause = null;
	static JLabel check_label = null;
	static JButton btn_confirm = null;
	static JButton btn_reset = null;
	static JLabel label_username = null;
	static JLabel label_password = null;
	static JLabel label_repassword = null;
	
	static JTextField tf_username = null;
	static JPasswordField tf_password = null;
	static JPasswordField tf_repassword = null;
	static JTextField tf_name = null;
	static JTextField tf_phone = null;
	static JTextField tf_idcard = null;
	static JTextArea jta_address = null;
	static JRadioButton sexbtn1 = null;
	static JRadioButton sexbtn2 = null;
	static JLabel clauseLabel  = null;
	
	private static IUpassword iupDao = new IUpasswordImpl();
	
	public static void initial(){
		myFrame = new JFrame("注册");
		myFrame.setBounds(0, 0, 350, 700);
		myFrame.setLocationRelativeTo(null);
		myFrame.setLayout(new FlowLayout());
		myFrame.setResizable(false);
		
		Container container = myFrame.getContentPane();
		
		//引导
		JLabel tipLabel = new JLabel("用户注册");
		tipLabel.setPreferredSize(new Dimension(300, 60));
		tipLabel.setFont(new Font("微软雅黑", 0 , 20));
		
		//账号密码
		label_username = new JLabel("账号:");
		Style.regist_label_style(label_username);
		tf_username = new JTextField();
		Style.regist_textfield_style(tf_username);
		JPanel jp_user = new JPanel();
		jp_user.add(label_username);
		jp_user.add(tf_username);
		
		label_password = new JLabel("密码:");
		Style.regist_label_style(label_password);
		tf_password = new JPasswordField();
		Style.regist_textfield_style(tf_password);
		JPanel jp_pwd = new JPanel();
		jp_pwd.add(label_password);
		jp_pwd.add(tf_password);
		
		label_repassword = new JLabel("确认密码:");
		Style.regist_label_style(label_repassword);
		tf_repassword = new JPasswordField();
		Style.regist_textfield_style(tf_repassword);
		JPanel jp_repwd = new JPanel();
		jp_repwd.add(label_repassword);
		jp_repwd.add(tf_repassword);
		
		//姓名
		label_name = new JLabel("姓名:");
		Style.regist_label_style(label_name);
		tf_name = new JTextField();
		Style.regist_textfield_style(tf_name);
		JPanel jp_rows1 = new JPanel();
		jp_rows1.add(label_name);
		jp_rows1.add(tf_name);
		
		//性别
		label_sex = new JLabel("性别:");
		Style.regist_label_style(label_sex);
		sexbtn1 = new JRadioButton("男");
		sexbtn2 = new JRadioButton("女");
		sexbtn1.setFont(new Font("微软雅黑", 0 , 16));
		sexbtn2.setFont(new Font("微软雅黑", 0 , 16));
		ButtonGroup bgsex = new ButtonGroup();
		sexbtn1.setSelected(true);
		bgsex.add(sexbtn1);
		bgsex.add(sexbtn2);
		
		JPanel jp_sex = new JPanel();
		jp_sex.setPreferredSize(new Dimension(170,30));
		
		jp_sex.add(sexbtn1);
		jp_sex.add(sexbtn2);
		
		JPanel jp_rows2 = new JPanel();
		jp_rows2.add(label_sex);
		jp_rows2.add(jp_sex);
		
		//电话
		label_phone = new JLabel("电话:");
		Style.regist_label_style(label_phone);
		tf_phone = new JTextField();
		Style.regist_textfield_style(tf_phone);
		JPanel jp_rows3 = new JPanel();
		jp_rows3.add(label_phone);
		jp_rows3.add(tf_phone);
		
		//身份证
		label_idcard = new JLabel("身份证:");
		Style.regist_label_style(label_idcard);
		tf_idcard = new JTextField();
		Style.regist_textfield_style(tf_idcard);
		JPanel jp_rows4 = new JPanel();
		jp_rows4.add(label_idcard);
		jp_rows4.add(tf_idcard);
		
		//住址
		label_address = new JLabel("住址:");
		Style.regist_label_style(label_address);
		jta_address = new JTextArea();
		jta_address.setPreferredSize(new Dimension(170,80));
		jta_address.setBorder(BorderFactory.createLineBorder(Color.gray));
		jta_address.setFont(new Font("微软雅黑", 0 , 16));
		jta_address.setLineWrap(true);
		
		JPanel jp_rows5 = new JPanel();
		jp_rows5.add(label_address);
		jp_rows5.add(jta_address);
		
		//条款
		clause = new JCheckBox(); 
		clauseLabel = new JLabel("我同意所有条款");
		clauseLabel.setFont(new Font("微软雅黑", 0 , 14));
		
		JPanel jp_rows6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp_rows6.setPreferredSize(new Dimension(270,30));
		
		jp_rows6.add(clause);
		jp_rows6.add(clauseLabel);
		
		//check信息
		check_label = new JLabel("请逐条填写准确信息!");
		check_label.setForeground(Color.gray);
		check_label.setFont(new Font("微软雅黑", 0 , 14));
		check_label.setPreferredSize(new Dimension(myFrame.getWidth(),30));
		check_label.setHorizontalAlignment(JLabel.CENTER);
		check_label.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));
		
		//确认和重置
		btn_confirm = new JButton("提交");
		Style.regist_btn_style(btn_confirm);
		btn_confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {confirm(e);}
		});
		btn_reset = new JButton("重置");
		Style.regist_btn_style(btn_reset);
		btn_reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {reset(e);}
		});
		JPanel jp_rows7 = new JPanel(new GridLayout(1,2,50,50));
		jp_rows7.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
		jp_rows7.add(btn_confirm);
		jp_rows7.add(btn_reset);
		
		
		
		container.add(tipLabel);
		container.add(jp_user);
		container.add(jp_pwd);
		container.add(jp_repwd);
		container.add(jp_rows1);
		container.add(jp_rows2);
		container.add(jp_rows3);
		container.add(jp_rows4);
		container.add(jp_rows5);
		container.add(jp_rows6);
		container.add(check_label);
		container.add(jp_rows7);
		
		myFrame.setVisible(true);
	}
	
	private static void confirm(ActionEvent e){
		int role = 0;
		String user = tf_username.getText();
		String password = String.valueOf(tf_repassword.getPassword());
		String name = tf_name.getText();
		String sex = null;
		
		if(user.substring(0,2).equals("sa")) role = 1;
		else role = 0;
		
		
		if(sexbtn1.isSelected()){
			sex = "男";
		}if(sexbtn2.isSelected()){
			sex = "女";
		}
		String phone = tf_phone.getText();
		String idcard = tf_idcard.getText();
		String address = jta_address.getText();
		
		if(iupDao.checkReUser(user) == false){
			JOptionPane.showMessageDialog(null, "账号已存在!!");
		}else{
			if(!password.equals(String.valueOf(tf_password.getPassword()))){
				JOptionPane.showMessageDialog(null, "两次输入的密码不一样!");
				tf_repassword.setText("");
				tf_password.setText("");
			}else{
				if(RegistPattern.checkPhone(phone) == false){
					JOptionPane.showMessageDialog(null, "电话号码格式不正确!");
					tf_phone.setText("");
				}else{
					if(new IUpasswordImpl().regist(user,password,name, sex, phone, idcard, address,role) == true){
						JOptionPane.showMessageDialog(null, "注册成功!请登录!");
						myFrame.setVisible(false);
					}
				}
				
			}
			
		}
	}
	
	private static void reset(ActionEvent e){
		clause.setSelected(false);
		tf_username.setText(null);
		tf_password.setText(null);
		tf_repassword.setText(null);
		tf_name.setText(null);
		tf_phone.setText(null);
		tf_idcard.setText(null);
		jta_address.setText(null);
		sexbtn1.setSelected(true);
		sexbtn2.setSelected(false);
		check_label.setText("请逐条填写准确信息!");
	}
}














