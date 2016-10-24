package com.chinasoft.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chinasoft.model.dao.ISalary;
import com.chinasoft.model.dao.impl.ISalaryImpl;

public class CreateSalary {

	public static DefaultComboBoxModel<String> cbmodel = null;
	
	private static ISalary iSalaryDao = new ISalaryImpl();
	
	private static JComboBox<String> jcb = null;
	
	private static JTextField tf2 = null;
	private static JTextField tf3 = null;
	
	private static JFrame myFrame = null;
	
	public static void init() {

		
		myFrame = new JFrame("添加一条工资");
		myFrame.setBounds(0, 0, 400, 175);
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);
		myFrame.setLayout(new GridLayout(3,1,0,20));
		Container container = myFrame.getContentPane();
		
		JPanel jp1 = new JPanel(new GridLayout(1,3,12,0));
		JPanel jp2 = new JPanel(new GridLayout(1,3,12,0));
		JPanel jp3 = new JPanel();
		
		jp1.setPreferredSize(new Dimension(375,50));
		jp2.setPreferredSize(new Dimension(375,50));
		jp3.setPreferredSize(new Dimension(375,50));
		
		JLabel jl1 = new JLabel("员工姓名:");
		jl1.setPreferredSize(new Dimension(125,30));
		JLabel jl2 = new JLabel("基础工资(元):");
		jl2.setPreferredSize(new Dimension(125,30));
		JLabel jl3 = new JLabel("每件奖金(元):");
		jl3.setPreferredSize(new Dimension(125,30));
		
		jl1.setFont(new Font("微软雅黑",0,14));
		jl2.setFont(new Font("微软雅黑",0,14));
		jl3.setFont(new Font("微软雅黑",0,14));
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		
		cbmodel = new DefaultComboBoxModel<String>();
		iSalaryDao.showComboItem();
		jcb = new JComboBox<String>(cbmodel);
		jcb.setPreferredSize(new Dimension(125,30));
		
		tf2 = new JTextField("2000");
		tf2.setPreferredSize(new Dimension(125,30));
		tf3 = new JTextField("1.5");
		tf2.setPreferredSize(new Dimension(125,30));
		
		jcb.setFont(new Font("微软雅黑",0,14));
		tf2.setFont(new Font("微软雅黑",0,14));
		tf3.setFont(new Font("微软雅黑",0,14));
		
		jp2.add(jcb);
		jp2.add(tf2);
		jp2.add(tf3);
		
		JButton btn_add = new JButton("添加");
		btn_add.setContentAreaFilled(false);
		btn_add.setPreferredSize(new Dimension(80,30));
		btn_add.setFont(new Font("微软雅黑", 0 , 16));
		
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addSalary(e);
			}
		});
		
		jp3.add(btn_add);
		
		container.add(jp1);
		container.add(jp2);
		container.add(jp3);
		myFrame.setVisible(true);
	}

	
	private static void addSalary(ActionEvent e){
		
		int no = Integer.parseInt(String.valueOf(jcb.getSelectedItem()).split(" - ")[0]);
		int basic =  Integer.parseInt(tf2.getText());
		double bonus = Double.parseDouble(tf3.getText());
		
		if(JOptionPane.showConfirmDialog(null, "确定添加?") == 0){
			if(iSalaryDao.addSalary(no, basic, bonus) == true){
				JOptionPane.showMessageDialog(null, "添加成功!");
				iSalaryDao.showSalary();
				myFrame.setVisible(false);
			}
		}
	
	}
}














