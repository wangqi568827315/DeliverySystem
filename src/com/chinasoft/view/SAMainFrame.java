package com.chinasoft.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.chinasoft.controller.PublicAttributes;
import com.chinasoft.model.dao.INotice;
import com.chinasoft.model.dao.ISalary;
import com.chinasoft.model.dao.impl.INoticeImpl;
import com.chinasoft.model.dao.impl.IPackImpl;
import com.chinasoft.model.dao.impl.ISalaryImpl;

public class SAMainFrame {
	
	private static ISalary iSalaryDao = new ISalaryImpl();
	private static INotice inoticeDao = new INoticeImpl();
	
	
	public static void init() {
		JFrame myFrame = new JFrame("快递派件系统");
		myFrame.setBounds(0, 0, 1366, 768);
		myFrame.setLocationRelativeTo(null);
		myFrame.setResizable(false);
		Container container = myFrame.getContentPane();
		
		//总Panel
		JPanel jpt = new JPanel();
		jpt.setBorder(BorderFactory.createEmptyBorder(34, 33, 34, 33));
		jpt.setLayout(new BorderLayout());
		
		//上面的JPanel
		JPanel north_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		north_panel.setPreferredSize(new Dimension(jpt.getWidth() , 50));
		
		JLabel tipLabel = new JLabel("    安迅通     ");
		tipLabel.setFont(new Font("微软雅黑", 2 , 30));
		tipLabel.setForeground(new Color(252,77,39));
		north_panel.add(tipLabel);
		jpt.add(north_panel, BorderLayout.NORTH);
		
		
		//左边的Jpanel
		JPanel west_panel = new JPanel();
		west_panel.setPreferredSize(new Dimension(200,600));
		west_panel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
		
		JButton ban_selEmp = new JButton("员工信息");
		JButton ban_selSal = new JButton("工资查询");
		JButton ban_notice = new JButton("查看公告");
		
		Style.main_banner_btn(ban_selEmp);
		Style.main_banner_btn(ban_selSal);
		Style.main_banner_btn(ban_notice);
		
		west_panel.add(ban_selEmp);
		west_panel.add(ban_selSal);
		west_panel.add(ban_notice);
		
		jpt.add(west_panel, BorderLayout.WEST);
		
		
		//右边的Jpanel
		final CardLayout cLayout = new CardLayout();
		final JPanel center_panel = new JPanel(cLayout);
		center_panel.setPreferredSize(new Dimension(1100,650));
		center_panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		JPanel childPanel_selEmp = new JPanel();
		JPanel childPanel_selSal = new JPanel();
		JPanel childPanel_notice = new JPanel();
		
		Style.main_panel_child(childPanel_selEmp);
		Style.main_panel_child(childPanel_selSal);
		Style.main_panel_child(childPanel_notice);
		
		center_panel.add(childPanel_selEmp, "P1");
		center_panel.add(childPanel_selSal, "P2");
		center_panel.add(childPanel_notice, "P3");
	
				//子Panel装入Hashmap
		Map<Integer, JPanel> child_panels = new HashMap<Integer, JPanel>();
		child_panels.put(0, childPanel_selEmp);
		child_panels.put(1, childPanel_selSal);
		child_panels.put(2, childPanel_notice);
		SAInitMainChild.initchild(child_panels);
		
		jpt.add(center_panel, BorderLayout.CENTER);
		
		
		//切换子Panel
		ban_selEmp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cLayout.show(center_panel, "P1");
			}
		});
		ban_selSal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cLayout.show(center_panel, "P2");
				iSalaryDao.showSalary();
			}
		});
		ban_notice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cLayout.show(center_panel, "P3");
				inoticeDao.showNoticeTitle();
				PublicAttributes.noticeNo = InitMainChild.notice_length;
				inoticeDao.showNotice(SAInitMainChild.notice_jtf1, SAInitMainChild.notice_jta1);
			}
		});
		
		//键盘事件
				myFrame.setFocusable(true);
				myFrame.addKeyListener(new KeyAdapter(){
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						int keycode = e.getKeyCode();
						switch (keycode) {
						case KeyEvent.VK_CONTROL:
							JOptionPane.showMessageDialog(null, "制作人:  王琦");
							break;
						case KeyEvent.VK_ALT:
							JOptionPane.showMessageDialog(null, "QQ:  568827315");
							break;
						}
					}			
				});
		
		container.add(jpt);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}
	
}
