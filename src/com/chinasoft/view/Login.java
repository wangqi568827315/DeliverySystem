package com.chinasoft.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

import com.chinasoft.controller.PublicAttributes;
import com.chinasoft.model.dao.IEmployee;
import com.chinasoft.model.dao.INotice;
import com.chinasoft.model.dao.impl.IEmployeeImpl;
import com.chinasoft.model.dao.impl.INoticeImpl;
import com.chinasoft.model.dao.impl.IUpasswordImpl;
import com.chinasoft.util.DateTime;

public class Login {
	

	static JFrame myFrame = null;
	static JTextField usertf = null;
	static JPasswordField pwdtf = null;
	private static IEmployee iEmpDao = new IEmployeeImpl();
	
	private static INotice inoticeDao = new INoticeImpl();

	
	public static void main(String[] args) {

		initial();
	}

	public static void initial(){
		myFrame = new JFrame("登录");
		myFrame.setBounds(300, 300, 450, 300);
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);
		Container container = myFrame.getContentPane();
		container.setLayout(new BorderLayout());
		
		//账号
		JLabel label_userName = new JLabel("账号:");
		label_userName.setFont(new Font("微软雅黑", 0 , 16));
		usertf = new JTextField(10);
		usertf.setFont(new Font("微软雅黑", 0 , 16));
		JPanel jp1 = new JPanel();
		jp1.setPreferredSize(new Dimension(myFrame.getWidth() , 40));
		jp1.add(label_userName);
		jp1.add(usertf);
		
		//密码
		JLabel label_passWord = new JLabel("密码:");
		label_passWord.setFont(new Font("微软雅黑", 0 , 16));
		pwdtf = new JPasswordField(10);
		pwdtf.setFont(new Font("微软雅黑", 0 , 16));
		JPanel jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(myFrame.getWidth() , 40));
		jp2.add(label_passWord);
		jp2.add(pwdtf);
		
		//修改密码
		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton findpwd = new JButton("修改密码");
		findpwd.setBorderPainted(false);
		findpwd.setContentAreaFilled(false);
		findpwd.setFont(new Font("微软雅黑", 0 , 14));
		jp3.setPreferredSize(new Dimension(250,30));
		jp3.add(findpwd);
		findpwd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePwd.changePwd();
			}
		});
		
		//登录注册
		JButton btn_login = new JButton("登录");
		Style.login_btn_style(btn_login);
		btn_login.addActionListener(new ActionListener() { 
			@Override 
			public void actionPerformed(ActionEvent e) {login_check(e);}
			});
		
		JButton btn_regist = new JButton("注册");
		Style.login_btn_style(btn_regist);
		btn_regist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {regist(e);	}
		});
		
		JPanel jp4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
		jp4.setPreferredSize(new Dimension(myFrame.getWidth() , 40));
		jp4.add(btn_login);
		jp4.add(btn_regist);
		
		
		//总panel
		JPanel jpt = new JPanel();
		jpt.setBorder(BorderFactory.createEmptyBorder(50,50,75,50));
		jpt.add(jp1);
		jpt.add(jp2);
		jpt.add(jp3);
		jpt.add(jp4);
		
		container.add(jpt);
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}
	
	private static void login_check(ActionEvent e){
		String user = usertf.getText();
		String pwd = String.valueOf(pwdtf.getPassword());
		
		if(new IUpasswordImpl().checkPwd(user,pwd) == false){
			JPanel pwdwarn = new JPanel();
		    JLabel warnning = new JLabel("账号/密码不正确!请重新输入!");
			warnning.setFont(new Font("微软雅黑", 0 , 14));
			new JOptionPane();
			JOptionPane.showMessageDialog(pwdwarn, warnning,"提示",JOptionPane.WARNING_MESSAGE);
		}else{
			PublicAttributes.user = user;
			if(iEmpDao.checkRole(PublicAttributes.user) == 0){
				MainFrame.initial(iEmpDao.showEmp(user));
				
			}else{
				SAMainFrame.init();
			}
			myFrame.setVisible(false);
			
		}

		
	}
	
	private static void regist(ActionEvent e){
		Regist.initial();
	}
}










