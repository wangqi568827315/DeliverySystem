package com.chinasoft.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Style {
	
	public static void login_btn_style(JButton jbtn){
		jbtn.setContentAreaFilled(false);
		jbtn.setPreferredSize(new Dimension(80,30));
		jbtn.setFont(new Font("微软雅黑", 0 , 16));
	}
	
	public static void regist_label_style(JLabel jlb){
		jlb.setFont(new Font("微软雅黑", 0 , 16));
		jlb.setPreferredSize(new Dimension(80,30));
		jlb.setHorizontalAlignment(JLabel.LEFT);
	}
	
	public static void regist_textfield_style(JTextField jtf){
		jtf.setFont(new Font("微软雅黑", 0 , 16));
		jtf.setPreferredSize(new Dimension(170,30));
	}
	
	public static void regist_btn_style(JButton jbtn){
		jbtn.setContentAreaFilled(false);
		jbtn.setPreferredSize(new Dimension(80,30));
		jbtn.setFont(new Font("微软雅黑", 0 , 16));
	}
	
	public static void main_banner_btn(JButton jbtn){
		jbtn.setContentAreaFilled(false);
		jbtn.setPreferredSize(new Dimension(150,40));
		jbtn.setFont(new Font("微软雅黑", 0 , 16));
	}
	
	public static void main_panel_child(JPanel jp){
		jp.setBorder(BorderFactory.createEmptyBorder(20,25,80,25));
		jp.setPreferredSize(new Dimension(1000,600));
	}
	
	public static void main_child_label(JLabel jlab){
		jlab.setFont(new Font("微软雅黑", 0 , 18));
		jlab.setPreferredSize(new Dimension(100,36));
		jlab.setHorizontalAlignment(JLabel.LEFT);
	}
	
	public static void main_child_tf(JTextField jtf){
		jtf.setFont(new Font("微软雅黑", 0 , 18));
		jtf.setPreferredSize(new Dimension(220,36));
	}
	
	public static void main_child_ta(JTextArea jta){
		jta.setPreferredSize(new Dimension(220,55));
		jta.setFont(new Font("微软雅黑",0,18));
		jta.setBorder(BorderFactory.createLineBorder(Color.gray));
		jta.setLineWrap(true);
	}
	
	public static void main_child_childpanel(JPanel jp){
		jp.setPreferredSize(new Dimension(1000,50));
	}
	
	public static void main_child_select_tf(JTextField jtf){
		jtf.setFont(new Font("微软雅黑", 0 , 16));
		jtf.setPreferredSize(new Dimension(150,28));
		
	}
	
	public static void updatePack_lb(JLabel lb){
		lb.setPreferredSize(new Dimension(120,30));
		lb.setFont(new Font("微软雅黑", 0 , 16));
	}
	public static void updatePack_tf(JTextField tf){
		tf.setPreferredSize(new Dimension(120,30));
		tf.setFont(new Font("微软雅黑", 0 , 16));
	}
}













