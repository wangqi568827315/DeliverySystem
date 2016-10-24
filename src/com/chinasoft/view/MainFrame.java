package com.chinasoft.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import com.chinasoft.controller.PublicAttributes;
import com.chinasoft.model.dao.INotice;
import com.chinasoft.model.dao.impl.INoticeImpl;
import com.chinasoft.model.dao.impl.IPackImpl;

public class MainFrame {
	
	
	static JFrame myFrame = null;
	
	
	static JPanel childPanel_mymsg = new JPanel();
	static JPanel childPanel_addExp = new JPanel();
	static JPanel childPanel_delByPhone = new JPanel();
	static JPanel childPanel_delByMsg = new JPanel();
	static JPanel childPanel_record = new JPanel();
	static JPanel childPanel_notice = new JPanel();
	
	static Map<Integer, JButton> btns_banner = null;
	static Map<Integer, JPanel> panels_child = null;
	static CardLayout cLayout = null;

	private static INotice inoticeDao = new INoticeImpl();
	
//	public static void main(String[] args){
//		initial();
//	}
//	
	public static void initial(String[] strs){
		myFrame = new JFrame("快递派件系统");
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
		tipLabel.setForeground(new Color(252,77,39));
		tipLabel.setFont(new Font("微软雅黑", 2 , 30));
		north_panel.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
		north_panel.add(tipLabel);
		jpt.add(north_panel, BorderLayout.NORTH);
		
		
		//左边的Jpanel
		JPanel west_panel = new JPanel();
		west_panel.setPreferredSize(new Dimension(200,600));
		west_panel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
		
		JButton ban_mymsg = new JButton("我的信息");
		JButton ban_addExp = new JButton("添加快件");
		JButton ban_delByPhone = new JButton("电话派件");
		JButton ban_delByMsg = new JButton("短信派件");
		JButton ban_record = new JButton("签收记录");
		JButton ban_notice = new JButton("查看公告");
		
		btns_banner = new HashMap<Integer, JButton>();
		
		btns_banner.put(1, ban_mymsg);
		btns_banner.put(2, ban_addExp);
		btns_banner.put(3, ban_delByPhone);
		btns_banner.put(4, ban_delByMsg);
		btns_banner.put(5, ban_record);
		btns_banner.put(6, ban_notice);
		
		for(int i = 1 ; i <= btns_banner.keySet().size(); i++){
			Style.main_banner_btn(btns_banner.get(i));
			west_panel.add(btns_banner.get(i));
		}
		
		jpt.add(west_panel, BorderLayout.WEST);
		
		
		//右边的Jpanel
		cLayout = new CardLayout();
		final JPanel center_panel = new JPanel(cLayout);
		center_panel.setPreferredSize(new Dimension(1100,650));
		center_panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		childPanel_mymsg = new JPanel();
		childPanel_addExp = new JPanel();
		childPanel_delByPhone = new JPanel();
		childPanel_delByMsg = new JPanel();
		childPanel_record = new JPanel();
		
		panels_child = new HashMap<Integer, JPanel>();
		panels_child.put(1, childPanel_mymsg);
		panels_child.put(2, childPanel_addExp);
		panels_child.put(3, childPanel_delByPhone);
		panels_child.put(4, childPanel_delByMsg);
		panels_child.put(5, childPanel_record);
		panels_child.put(6, childPanel_notice);
		
		InitMainChild.initchild(panels_child, strs);
		
		for(int j = 1 ; j <= panels_child.keySet().size(); j++){
			Style.main_panel_child(panels_child.get(j));
		}

		center_panel.add(panels_child.get(1),"P1");
		center_panel.add(panels_child.get(2),"P2");
		center_panel.add(panels_child.get(3),"P3");
		center_panel.add(panels_child.get(4),"P4");
		center_panel.add(panels_child.get(5),"P5");
		center_panel.add(panels_child.get(6),"P6");
		
		ban_mymsg.addActionListener(new ActionListener() {	
			@Override	public void actionPerformed(ActionEvent e) {	
				cLayout.show(center_panel, "P1");
				
				
				}	});
		
		ban_addExp.addActionListener(new ActionListener() {	
			@Override	
			public void actionPerformed(ActionEvent e) {	
				cLayout.show(center_panel, "P2");
				}	});
		
		ban_delByPhone.addActionListener(new ActionListener() {	
			@Override	
			public void actionPerformed(ActionEvent e) {	
				cLayout.show(center_panel, "P3");
				InitMainChild.model1.setRowCount(0);
				new IPackImpl().showPack(InitMainChild.model1);
					
				showTablepStyle();
				}	});
		
		ban_delByMsg.addActionListener(new ActionListener() {	
			@Override	
			public void actionPerformed(ActionEvent e) {	
				cLayout.show(center_panel, "P4");
				InitMainChild.model2.setRowCount(0);
				new IPackImpl().showPack(InitMainChild.model2);
				
				showTablemStyle();
				}	});
		
		ban_record.addActionListener(new ActionListener() {	
			@Override	
			public void actionPerformed(ActionEvent e) {	
				cLayout.show(center_panel, "P5");
				
				showTablehStyle();
				
				InitMainChild.model3.setRowCount(0);
				new IPackImpl().showHistory((InitMainChild.model3));
				}	});
		
		ban_notice.addActionListener(new ActionListener() {	
			@Override	
			public void actionPerformed(ActionEvent e) {	
				cLayout.show(center_panel, "P6");
				inoticeDao.showNoticeTitle();
				PublicAttributes.noticeNo = InitMainChild.notice_length;
				inoticeDao.showNotice(InitMainChild.notice_jtf1,InitMainChild.notice_jta1);
				}	});
		
		jpt.add(center_panel, BorderLayout.CENTER);
		
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
	
	public static void showTablepStyle(){
		//表格奇偶行显示不同背景颜色,已派件显示灰色字体
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {  
            public Component getTableCellRendererComponent(JTable table,  
                    Object value, boolean isSelected, boolean hasFocus,  
                    int row, int column) {  
                if (row % 2 == 0) {  
                    setBackground(Color.white); //设置奇数行底色  
                } else if (row % 2 == 1) {  
                    setBackground(new Color(206, 231, 255)); //设置偶数行底色  
                }  
                if (!InitMainChild.expTable_p.getValueAt(row, 7).equals("未派送")) {  
                    setForeground(Color.gray);
                } 
                return super.getTableCellRendererComponent(table, value,  
                        isSelected, hasFocus, row, column);  
            }  
        };  
        for (int i = 0; i < InitMainChild.expTable_p.getColumnCount(); i++) {  
        	InitMainChild.expTable_p.getColumn(InitMainChild.expTable_p.getColumnName(i)).setCellRenderer(tcr);  
        }  
        
	}
	
	public static void showTablemStyle(){
		//表格奇偶行显示不同背景颜色,已派件显示灰色字体
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {  
            public Component getTableCellRendererComponent(JTable table,  
                    Object value, boolean isSelected, boolean hasFocus,  
                    int row, int column) {  
                if (row % 2 == 0) {  
                    setBackground(Color.white); //设置奇数行底色  
                } else if (row % 2 == 1) {  
                    setBackground(new Color(206, 231, 255)); //设置偶数行底色  
                }  
                if (!InitMainChild.expTable_m.getValueAt(row, 7).equals("未派送")) {  
                    setForeground(Color.gray);
                } 
                return super.getTableCellRendererComponent(table, value,  
                        isSelected, hasFocus, row, column);  
            }  
        };  
        for (int i = 0; i < InitMainChild.expTable_m.getColumnCount(); i++) {  
        	InitMainChild.expTable_m.getColumn(InitMainChild.expTable_m.getColumnName(i)).setCellRenderer(tcr);  
        }  
        
	}
	public static void showTablehStyle(){
		//表格奇偶行显示不同背景颜色
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {  
            public Component getTableCellRendererComponent(JTable table,  
                    Object value, boolean isSelected, boolean hasFocus,  
                    int row, int column) {  
                if (row % 2 == 0) {  
                    setBackground(Color.white); //设置奇数行底色  
                } else if (row % 2 == 1) {  
                    setBackground(new Color(206, 231, 255)); //设置偶数行底色  
                }  
                return super.getTableCellRendererComponent(table, value,  
                        isSelected, hasFocus, row, column);  
            }  
        };  
        for (int i = 0; i < InitMainChild.expTable_sel.getColumnCount(); i++) {  
        	InitMainChild.expTable_sel.getColumn(InitMainChild.expTable_sel.getColumnName(i)).setCellRenderer(tcr);  
        }  
        
	}
	
	
	
}













