package com.chinasoft.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chinasoft.model.dao.impl.IPackImpl;

public class UpdatePack {

	public static void main(String[] args) {
	
	}
	
		public static void init(Object[] objs){
		final JFrame myFrame = new JFrame("修改快件信息");
		myFrame.setBounds(0, 0, 1000, 150);
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);

		Container container = myFrame.getContentPane();
		JPanel jpt = new JPanel();
		jpt.setPreferredSize(new Dimension(1000, 150));

		JPanel jp1 = new JPanel(new GridLayout(1, 8, 5, 0));
		jp1.setPreferredSize(new Dimension(960, 30));
		JLabel jlab1 = new JLabel("单号:");
		JLabel jlab2 = new JLabel("收件人姓名:");
		JLabel jlab3 = new JLabel("收件人电话:");
		JLabel jlab4 = new JLabel("收件人地址:");
		JLabel jlab5 = new JLabel("寄件人姓名:");
		JLabel jlab6 = new JLabel("寄件人电话:");
		JLabel jlab7 = new JLabel("寄件人地址:");
		JLabel jlab8 = new JLabel("备注:");
		Style.updatePack_lb(jlab1);
		Style.updatePack_lb(jlab2);
		Style.updatePack_lb(jlab3);
		Style.updatePack_lb(jlab4);
		Style.updatePack_lb(jlab5);
		Style.updatePack_lb(jlab6);
		Style.updatePack_lb(jlab7);
		Style.updatePack_lb(jlab8);
		jp1.add(jlab1);
		jp1.add(jlab2);
		jp1.add(jlab3);
		jp1.add(jlab4);
		jp1.add(jlab5);
		jp1.add(jlab6);
		jp1.add(jlab7);
		jp1.add(jlab8);

		JPanel jp2 = new JPanel(new GridLayout(1, 8, 0, 0));
		jp2.setPreferredSize(new Dimension(960, 30));
		final JTextField tf1 = new JTextField(String.valueOf(objs[0]));
		final JTextField tf2 = new JTextField(String.valueOf(objs[1]));
		final JTextField tf3 = new JTextField(String.valueOf(objs[2]));
		final JTextField tf4 = new JTextField(String.valueOf(objs[3]));
		final JTextField tf5 = new JTextField(String.valueOf(objs[4]));
		final JTextField tf6 = new JTextField(String.valueOf(objs[5]));
		final JTextField tf7 = new JTextField(String.valueOf(objs[6]));
		final JTextField tf8 = new JTextField(String.valueOf(objs[7]));
		tf1.setEditable(false);
		Style.updatePack_tf(tf1);
		Style.updatePack_tf(tf2);
		Style.updatePack_tf(tf3);
		Style.updatePack_tf(tf4);
		Style.updatePack_tf(tf5);
		Style.updatePack_tf(tf6);
		Style.updatePack_tf(tf7);
		Style.updatePack_tf(tf8);
		jp2.add(tf1);
		jp2.add(tf2);
		jp2.add(tf3);
		jp2.add(tf4);
		jp2.add(tf5);
		jp2.add(tf6);
		jp2.add(tf7);
		jp2.add(tf8);

		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp3.setPreferredSize(new Dimension(960, 50));
		JButton btn_confirm = new JButton("提交");
		btn_confirm.setContentAreaFilled(false);
		btn_confirm.setPreferredSize(new Dimension(120, 30));
		btn_confirm.setFont(new Font("微软雅黑", 0, 16));
		jp3.add(btn_confirm);
		
		btn_confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] strs = new String[]{tf1.getText(),tf2.getText(),tf3.getText(),tf4.getText(),
						tf5.getText(),tf6.getText(),tf7.getText(),tf8.getText(),};
				
				int i = JOptionPane.showConfirmDialog(null, "确认提交修改?");
				if(i == 0){
					if(new IPackImpl().updatePack(strs) == true){
						JOptionPane.showMessageDialog(null, "信息修改成功!");
						InitMainChild.model1.setRowCount(0);
						new IPackImpl().showPack(InitMainChild.model1);
						
						InitMainChild.model2.setRowCount(0);
						new IPackImpl().showPack(InitMainChild.model2);
						
						MainFrame.showTablepStyle();
						MainFrame.showTablemStyle();
						myFrame.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(null, "信息修改失败!请仔细核对后提交!");
					}
				}
			}
		});
		
		

		jpt.add(jp1);
		jpt.add(jp2);
		jpt.add(jp3);
		container.add(jpt);
		myFrame.setVisible(true);
	}

}
