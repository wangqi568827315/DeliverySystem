package com.chinasoft.view;

import java.awt.Color;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.chinasoft.controller.PublicAttributes;
import com.chinasoft.model.dao.INotice;
import com.chinasoft.model.dao.impl.INoticeImpl;

public class CreateNotice {

	private static INotice inoticeDao = new INoticeImpl();
	

	public static void init() {
		final JFrame myFrame = new JFrame("发布公告");
		myFrame.setBounds(0,0, 600,400);
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);
		myFrame.setLayout(new FlowLayout());
		
		Container container = myFrame.getContentPane();
		
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
	
		jp1.setPreferredSize(new Dimension(600,50));
		jp2.setPreferredSize(new Dimension(600,250));
		jp3.setPreferredSize(new Dimension(600,50));
		

		JLabel jl1 = new JLabel("标题:");
		JLabel jl2 = new JLabel("正文:");
		Style.regist_label_style(jl1);
		Style.regist_label_style(jl2);
		jl1.setPreferredSize(new Dimension(50,50));
		jl2.setPreferredSize(new Dimension(50,50));
		
		
		final JTextField jtf1 = new JTextField();
		jtf1.setPreferredSize(new Dimension(500,40));
		jtf1.setFont(new Font("微软雅黑",1,18));
		jtf1.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		final JTextArea jta1 = new JTextArea();
		jta1.setLineWrap(true);
		jta1.setPreferredSize(new Dimension(500,240));
		jta1.setBorder(BorderFactory.createLineBorder(Color.gray));
		jta1.setFont(new Font("微软雅黑",0,16));
		JButton jbtn1 = new JButton("发布");
		Style.login_btn_style(jbtn1);
		
		jp1.add(jl1);
		jp1.add(jtf1);
		jp2.add(jl2);
		jp2.add(jta1);
		jp3.add(jbtn1);
		
		container.add(jp1);
		container.add(jp2);
		container.add(jp3);
		
		jbtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确认添加?") == 0){
					if(inoticeDao.createNotice(jtf1.getText(), jta1.getText()) == true){
						JOptionPane.showMessageDialog(null, "添加成功!");
						myFrame.setVisible(false);
						inoticeDao.showNoticeTitle();
//						SAInitMainChild.initjbtns();
						PublicAttributes.noticeNo = InitMainChild.notice_length;
						inoticeDao.showNotice(SAInitMainChild.notice_jtf1, SAInitMainChild.notice_jta1);
						
					}else{
						JOptionPane.showMessageDialog(null, "系统异常,请重试!");
					}
				}
				
			}
		});
		
		myFrame.setVisible(true);
	}
	
	
}














