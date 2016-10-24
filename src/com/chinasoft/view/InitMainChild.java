package com.chinasoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RepaintManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.commons.httpclient.HttpException;

import com.chinasoft.controller.PublicAttributes;
import com.chinasoft.controller.SendMsg;
import com.chinasoft.model.dao.INotice;
import com.chinasoft.model.dao.IPack;
import com.chinasoft.model.dao.impl.IEmployeeImpl;
import com.chinasoft.model.dao.impl.INoticeImpl;
import com.chinasoft.model.dao.impl.IPackImpl;

public class InitMainChild {

	private static INotice inoticeDao = new INoticeImpl();
	public static int notice_length = 0;
	
	private static JButton[] jbtns = null;
	
	public static DefaultTableModel model1;
	public static JTable expTable_p;
	public static DefaultTableModel model2;
	public static DefaultTableModel model3;
	public static JTable expTable_m;
	
	public static JTable expTable_sel;
	private static JScrollPane jsp_p;
	private static JScrollPane jsp_p1;

	static JLabel label_no = null;
	static JLabel label_name = null;
	static JLabel label_sex = null;
	static JLabel label_phone = null;
	static JLabel label_idcard = null;
	static JLabel label_address = null;
	static JLabel label_regdate = null;

	public static JLabel lb_no = null;
	public static JTextField tf_name = null;
	public static JTextField tf_sex = null;
	public static JTextField tf_phone = null;
	public static JTextField tf_idcard = null;
	public static JTextArea ta_address = null;
	public static JLabel lb_regdate = null;

	private static JTextField tabletf = null;

	public static String s1 = null;
	public static String s2 = null;
	public static String s3 = null;
	public static String s4 = null;

	static JTextField tf_delp_name = null;
	static JTextField tf_delm_phone = null;

	private static int focusedRowIndex = 0;

	private static JPopupMenu m_popupMenu = null;
	
	private static IPack packdao = new IPackImpl();
	
	public static JTextField notice_jtf1 = null;
	public static JTextArea notice_jta1 = null;

	public static void initchild(Map<Integer, JPanel> panels_child,
			String[] strs) {

		for (Map.Entry<Integer, JPanel> panels : panels_child.entrySet()) {

			switch (panels.getKey()) {
			case 1:
				childPanel_mymsg(panels.getValue(), strs);
				break;
			case 2:
				childPanel_addExp(panels.getValue());
				break;
			case 3:
				childPanel_delByPhone(panels.getValue());
				break;
			case 4:
				childPanel_delByMsg(panels.getValue());
				break;
			case 5:
				childPanel_record(panels.getValue());
				break;
			case 6:
				childPanel_notice(panels.getValue());
				break;
			}

		}

	}

	private static void childPanel_notice(JPanel content) {
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		
		jp1.setPreferredSize(new Dimension(820,580));
		jp2.setPreferredSize(new Dimension(180,580));
		
		JPanel jp1_1 = new JPanel();
		JPanel jp1_2 = new JPanel();
		jp1_1.setPreferredSize(new Dimension(820,480));
		jp1_2.setPreferredSize(new Dimension(820,50));
		
		JPanel jp1_1_1 = new JPanel();
		jp1_1_1.setPreferredSize(new Dimension(820,480));
		notice_jtf1 = new JTextField();
		notice_jtf1.setHorizontalAlignment(JTextField.CENTER);
		notice_jtf1.setEditable(false);
		notice_jtf1.setPreferredSize(new Dimension(780,40));
		notice_jtf1.setFont(new Font("微软雅黑",1,26));
		notice_jtf1.setBorder(BorderFactory.createEmptyBorder());
		
		notice_jta1 = new JTextArea();
		notice_jta1.setLineWrap(true);
		notice_jta1.setEditable(false);
		notice_jta1.setPreferredSize(new Dimension(780,400));
		notice_jta1.setBorder(BorderFactory.createLineBorder(Color.gray));
		notice_jta1.setFont(new Font("微软雅黑",0,16));
		jp1_1_1.add(notice_jtf1);
		jp1_1_1.add(notice_jta1);
		jp1_1.add(jp1_1_1);
		
		JButton jbtn1 = new JButton("←");
		Style.login_btn_style(jbtn1);
		JButton jbtn2 = new JButton("→");
		Style.login_btn_style(jbtn2);
		jp1_2.add(jbtn1);
		jp1_2.add(jbtn2);
		
		
//		inoticeDao.showNoticeTitle();
		jbtns = new JButton[20];
		for (int i = 0; i < jbtns.length; i++) {
			jbtns[i] = new JButton();
			jbtns[i].setHorizontalAlignment(JButton.LEFT);
			jbtns[i].setContentAreaFilled(false);
			jbtns[i].setBorder(BorderFactory.createLineBorder(Color.white));
//			jbtns[i].setText(inoticeDao.showNoticeTitle()[i]);
			jbtns[i].setPreferredSize(new Dimension(160,20));
			jbtns[i].setFont(new Font("微软雅黑",0,16));
			jp2.add(jbtns[i]);
		}
		

		for(int w = 0 ; w < jbtns.length ; w++){
			jbtns[w].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i = 0 ; i < jbtns.length ; i++){
						if(e.getSource() == jbtns[i]){
							PublicAttributes.noticeNo = i+1;
							inoticeDao.showNotice(notice_jtf1, notice_jta1);
						}
					}
				}
			});
		}
		
		
		
		jp2.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		
		jbtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PublicAttributes.noticeNo -= 1;
				if(PublicAttributes.noticeNo == 0){
					PublicAttributes.noticeNo = 1;
				}

				inoticeDao.showNotice(notice_jtf1, notice_jta1);
			}
		});
		jbtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(PublicAttributes.noticeNo >= notice_length){
					PublicAttributes.noticeNo = notice_length;
				}else {
					PublicAttributes.noticeNo += 1;
				}
				inoticeDao.showNotice(notice_jtf1, notice_jta1);
			}
		});
		
		jp1.add(jp1_1);
		jp1.add(jp1_2);
		content.add(jp1);
		content.add(jp2);
		
	}

	public static void childPanel_mymsg(JPanel content, String[] strs) {

		JLabel label_no = new JLabel("编号:");
		JLabel label_name = new JLabel("姓名:");
		JLabel label_sex = new JLabel("性别:");
		JLabel label_phone = new JLabel("电话号码:");
		JLabel label_idcard = new JLabel("身份证号:");
		JLabel label_address = new JLabel("住址:");
		JLabel label_regdate = new JLabel("注册时间:");

		final JLabel lb_no = new JLabel(strs[0]);
		final JTextField tf_name = new JTextField(strs[1]);
		final JTextField tf_sex = new JTextField(strs[2]);
		final JTextField tf_phone = new JTextField(strs[3]);
		final JTextField tf_idcard = new JTextField(strs[4]);
		final JTextArea ta_address = new JTextArea(strs[5]);
		JLabel lb_regdate = new JLabel(strs[6].split(" ")[0]);

		Style.main_child_ta(ta_address);

		final JButton btn_edit = new JButton("编辑");
		final JButton btn_save = new JButton("保存");
		btn_save.setVisible(false);
		btn_edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf_name.setEditable(true);
				tf_sex.setEditable(true);
				tf_phone.setEditable(true);
				tf_idcard.setEditable(true);
				ta_address.setEditable(true);
				btn_edit.setVisible(false);
				btn_save.setVisible(true);
			}
		});

		btn_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel warnning = new JLabel("确认保存修改?");
				warnning.setFont(new Font("微软雅黑", 0, 14));
				int i = JOptionPane.showConfirmDialog(new JPanel(), warnning);

				if (i == 0) {
					if (new IEmployeeImpl().updateEmp(
							Integer.parseInt(lb_no.getText()),
							tf_name.getText(), tf_sex.getText(),
							tf_phone.getText(), tf_idcard.getText(),
							ta_address.getText()) == true) {
						JOptionPane.showMessageDialog(null, "修改信息成功!");
						tf_name.setEditable(false);
						tf_sex.setEditable(false);
						tf_phone.setEditable(false);
						tf_idcard.setEditable(false);
						ta_address.setEditable(false);
						btn_save.setVisible(false);
						btn_edit.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "信息有误,请重新输入!");
					}

				}
			}
		});

		Style.login_btn_style(btn_edit);
		Style.login_btn_style(btn_save);
		Style.main_child_label(label_no);
		Style.main_child_label(label_name);
		Style.main_child_label(label_sex);
		Style.main_child_label(label_phone);
		Style.main_child_label(label_idcard);
		Style.main_child_label(label_address);
		Style.main_child_label(label_regdate);

		Style.main_child_label(lb_no);
		Style.main_child_tf(tf_name);
		Style.main_child_tf(tf_sex);
		Style.main_child_tf(tf_phone);
		Style.main_child_tf(tf_idcard);
		Style.main_child_label(lb_regdate);
		lb_regdate.setPreferredSize(new Dimension(220, 36));

		tf_name.setEditable(false);
		tf_sex.setEditable(false);
		tf_phone.setEditable(false);
		tf_idcard.setEditable(false);
		ta_address.setEditable(false);

		JPanel initchild_mymsg_panels0 = new JPanel(new FlowLayout(
				FlowLayout.LEFT));
		JPanel initchild_mymsg_panels1 = new JPanel(new FlowLayout(
				FlowLayout.LEFT));
		JPanel initchild_mymsg_panels2 = new JPanel(new FlowLayout(
				FlowLayout.LEFT));
		JPanel initchild_mymsg_panels3 = new JPanel(new FlowLayout(
				FlowLayout.LEFT));
		JPanel initchild_mymsg_panels4 = new JPanel(new FlowLayout(
				FlowLayout.LEFT));
		JPanel initchild_mymsg_panels5 = new JPanel(new FlowLayout(
				FlowLayout.LEFT));
		JPanel initchild_mymsg_panels6 = new JPanel(new FlowLayout(
				FlowLayout.LEFT));
		JPanel initchild_mymsg_panels7 = new JPanel(new FlowLayout(
				FlowLayout.LEFT));
		initchild_mymsg_panels0.setPreferredSize(new Dimension(1000, 60));
		initchild_mymsg_panels1.setPreferredSize(new Dimension(1000, 60));
		initchild_mymsg_panels2.setPreferredSize(new Dimension(1000, 60));
		initchild_mymsg_panels3.setPreferredSize(new Dimension(1000, 60));
		initchild_mymsg_panels4.setPreferredSize(new Dimension(1000, 60));
		initchild_mymsg_panels5.setPreferredSize(new Dimension(1000, 60));
		initchild_mymsg_panels6.setPreferredSize(new Dimension(1000, 60));
		initchild_mymsg_panels7.setPreferredSize(new Dimension(800, 60));
		initchild_mymsg_panels0.add(label_no);
		initchild_mymsg_panels0.add(lb_no);
		initchild_mymsg_panels1.add(label_name);
		initchild_mymsg_panels1.add(tf_name);
		initchild_mymsg_panels2.add(label_sex);
		initchild_mymsg_panels2.add(tf_sex);
		initchild_mymsg_panels3.add(label_phone);
		initchild_mymsg_panels3.add(tf_phone);
		initchild_mymsg_panels4.add(label_idcard);
		initchild_mymsg_panels4.add(tf_idcard);
		initchild_mymsg_panels5.add(label_address);
		initchild_mymsg_panels5.add(ta_address);
		initchild_mymsg_panels6.add(label_regdate);
		initchild_mymsg_panels6.add(lb_regdate);
		initchild_mymsg_panels7.add(btn_edit);
		initchild_mymsg_panels7.add(btn_save);

		content.add(initchild_mymsg_panels0);
		content.add(initchild_mymsg_panels1);
		content.add(initchild_mymsg_panels2);
		content.add(initchild_mymsg_panels3);
		content.add(initchild_mymsg_panels4);
		content.add(initchild_mymsg_panels5);
		content.add(initchild_mymsg_panels6);
		content.add(initchild_mymsg_panels7);

	}

	public static void childPanel_addExp(JPanel content) {

		JPanel jPanel2 = new JPanel();
		jPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		content.add(jPanel2);

		JLabel label_order_no = new JLabel("单号:");
		Style.main_child_label(label_order_no);
		jPanel2.add(label_order_no);

		final JTextField tf_order_no = new JTextField();
		Style.main_child_tf(tf_order_no);
		jPanel2.add(tf_order_no);

		JPanel jPanel3 = new JPanel();
		jPanel3.setLayout(new GridLayout(0, 2));
		content.add(jPanel3);

		JLabel label_post = new JLabel("寄件人:");
		Style.main_child_label(label_post);
		jPanel3.add(label_post);

		JLabel label_rec = new JLabel("收件人:");
		Style.main_child_label(label_rec);
		jPanel3.add(label_rec);

		JPanel jPanel4 = new JPanel();
		jPanel4.setLayout(new GridLayout(1, 2));
		content.add(jPanel4);

		JPanel jPanel4_left = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel4.add(jPanel4_left);

		JLabel label_post_name = new JLabel("姓名:");
		Style.main_child_label(label_post_name);
		jPanel4_left.add(label_post_name);

		final JTextField tf_post_name = new JTextField();
		Style.main_child_tf(tf_post_name);
		jPanel4_left.add(tf_post_name);

		JPanel jPanel4_right = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel4.add(jPanel4_right);

		JLabel label_rec_name = new JLabel("姓名:");
		Style.main_child_label(label_rec_name);
		jPanel4_right.add(label_rec_name);

		final JTextField tf_rec_name = new JTextField();
		Style.main_child_tf(tf_rec_name);
		jPanel4_right.add(tf_rec_name);

		JPanel jPanel5 = new JPanel();
		jPanel5.setLayout(new GridLayout(1, 2));
		content.add(jPanel5);

		JPanel jPanel5_left = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel5.add(jPanel5_left);

		JLabel label_post_phone = new JLabel("电话:");
		Style.main_child_label(label_post_phone);
		jPanel5_left.add(label_post_phone);

		final JTextField tf_post_phone = new JTextField();
		Style.main_child_tf(tf_post_phone);
		jPanel5_left.add(tf_post_phone);

		JPanel jPanel5_right = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel5.add(jPanel5_right);

		JLabel label_rec_phone = new JLabel("电话:");
		Style.main_child_label(label_rec_phone);
		jPanel5_right.add(label_rec_phone);

		final JTextField tf_rec_phone = new JTextField();
		Style.main_child_tf(tf_rec_phone);
		jPanel5_right.add(tf_rec_phone);

		JPanel jPanel6 = new JPanel();
		jPanel6.setLayout(new GridLayout(1, 2));
		jPanel6.setPreferredSize(new Dimension(1000, 70));
		content.add(jPanel6);

		JPanel jPanel6_left = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel6.add(jPanel6_left);

		JLabel label_post_address = new JLabel("住址:");
		Style.main_child_label(label_post_address);
		jPanel6_left.add(label_post_address);
		jPanel6_left.setPreferredSize(new Dimension(500, 70));

		final JTextArea ta__post_name = new JTextArea();
		Style.main_child_ta(ta__post_name);
		jPanel6_left.add(ta__post_name);

		JPanel jPanel6_right = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel6_right.setPreferredSize(new Dimension(500, 70));
		jPanel6.add(jPanel6_right);

		JLabel label_rec_address = new JLabel("住址:");
		Style.main_child_label(label_rec_address);

		jPanel6_right.add(label_rec_address);

		final JTextArea ta_rec_name = new JTextArea();
		Style.main_child_ta(ta_rec_name);
		jPanel6_right.add(ta_rec_name);

		JPanel jPanel7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		content.add(jPanel7);
		JLabel label_commit = new JLabel("备注:");

		Style.main_child_label(label_commit);
		jPanel7.add(label_commit);
		jPanel7.setPreferredSize(new Dimension(1000, 70));

		final JTextArea ta_commit = new JTextArea();
		Style.main_child_ta(ta_commit);
		jPanel7.add(ta_commit);

		JPanel jPanel8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel8.setPreferredSize(new Dimension(800, 100));
		jPanel8.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		content.add(jPanel8);

		JButton btn_addExp = new JButton("添加");
		btn_addExp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel pwdwarn = new JPanel();
				JLabel warnning = new JLabel("确认添加吗?");
				warnning.setFont(new Font("微软雅黑", 0, 14));
				int i = JOptionPane.showConfirmDialog(pwdwarn, warnning);
				if (i == 0) {
					if (packdao.addExp(tf_order_no.getText(),
							tf_rec_name.getText(), tf_rec_phone.getText(),
							ta_rec_name.getText(), tf_post_name.getText(),
							tf_post_phone.getText(), ta__post_name.getText(),
							ta_commit.getText()) == true) {
						JOptionPane.showMessageDialog(null, "添加成功!");
						tf_order_no.setText("");
						tf_rec_name.setText("");
						tf_rec_phone.setText("");
						ta_rec_name.setText("");
						tf_post_name.setText("");
						tf_post_phone.setText("");
						ta__post_name.setText("");
						ta_commit.setText("");
					}
				}
			}
		});
		Style.login_btn_style(btn_addExp);
		jPanel8.add(btn_addExp);

		Style.main_child_childpanel(jPanel2);
		Style.main_child_childpanel(jPanel3);
		Style.main_child_childpanel(jPanel4);
		Style.main_child_childpanel(jPanel5);

	}

	public static void childPanel_delByPhone(JPanel content) {

		String colNames[] = { "单号", "收件人", "收件人电话", "收件人地址", "寄件人",
				"寄件人电话", "寄件人地址", "派件日期", "备注" };

		// 将表格变为只读
		model1 = new DefaultTableModel(colNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				// return super.isCellEditable(row, column);
				JTextField tabletf = new JTextField(model1.getValueAt(row,
						column).toString());
				tabletf.setEditable(false);
				expTable_p.getColumnModel().getColumn(column)
						.setCellEditor(new DefaultCellEditor(tabletf));

				return true;
			}

		};
		expTable_p = new JTable(model1);
		expTable_p.setFont(new Font("微软雅黑", 0, 14));

		expTable_p.setRowHeight(20);
		expTable_p.getTableHeader().setFont(new Font("微软雅黑",0,14));
		
		TableColumnModel tcm = expTable_p.getColumnModel();
		tcm.getColumn(1).setPreferredWidth(35);
		tcm.getColumn(4).setPreferredWidth(35);
		tcm.getColumn(8).setPreferredWidth(180);
		
		jsp_p = new JScrollPane(expTable_p);
		jsp_p.setPreferredSize(new Dimension(1000, 400));
		jsp_p.setViewportView(expTable_p);
		content.add(jsp_p);

		JPanel bottom = new JPanel();
		content.add(bottom, BorderLayout.SOUTH);
		JLabel label_delp_name = new JLabel("收件人姓名");
		Style.main_child_label(label_delp_name);
		tf_delp_name = new JTextField();
		Style.main_child_tf(tf_delp_name);
		tf_delp_name.setEditable(false);

		tf_delp_name.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));
		JLabel label_delp_phone = new JLabel("收件人电话");
		Style.main_child_label(label_delp_phone);
		tf_delm_phone = new JTextField();
		Style.main_child_tf(tf_delm_phone);
		tf_delm_phone.setEditable(false);

		// 点击选中列事件
		expTable_p.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
					// // 获得点击单元格数据

					focusedRowIndex = expTable_p.rowAtPoint(e.getPoint());
					createPopupMenu(expTable_p);
					if (focusedRowIndex == -1) {
						return;
					}
					// 将表格所选项设为当前右键点击的行
					expTable_p.setRowSelectionInterval(focusedRowIndex,
							focusedRowIndex);
					// 弹出菜单
					m_popupMenu.show(expTable_p, e.getX(), e.getY());

				} else {
					if (expTable_p.getValueAt(expTable_p.getSelectedRow(), 0) != null) {
						s1 = String.valueOf(expTable_p.getValueAt(
								expTable_p.getSelectedRow(), 1));
						s2 = String.valueOf(expTable_p.getValueAt(
								expTable_p.getSelectedRow(), 2));
					}
					tf_delp_name.setText(s1);
					tf_delm_phone.setText(s2);
				}
				MainFrame.showTablepStyle();

			}
		});

		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp1.setPreferredSize(new Dimension(1000, 50));
		JButton btn_upd = new JButton("修改");
		JButton btn_del = new JButton("删除");
		jp1.add(btn_upd);
		jp1.add(btn_del);

		// 修改
		btn_upd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = expTable_p.getSelectedRow();
				PublicAttributes.tableSelObject = expTable_p.getValueAt(index,
						0);

				Object[] selectedMsg = null;
				selectedMsg = new Object[] { expTable_p.getValueAt(index, 0),
						expTable_p.getValueAt(index, 1),
						expTable_p.getValueAt(index, 2),
						expTable_p.getValueAt(index, 3),
						expTable_p.getValueAt(index, 4),
						expTable_p.getValueAt(index, 5),
						expTable_p.getValueAt(index, 6),
						expTable_p.getValueAt(index, 8) };
				UpdatePack.init(selectedMsg);

			}
		});
		// 删除
		btn_del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String delNo = String.valueOf(expTable_p.getValueAt(
						expTable_p.getSelectedRow(), 0));

				if (JOptionPane.showConfirmDialog(null, "确认删除?单号:" + delNo) == 0) {
					if (packdao.deletePack(delNo) == true) {
						JOptionPane.showMessageDialog(null, "删除成功!");
						model1.setRowCount(0);
						packdao.showPack(model1);
						MainFrame.showTablepStyle();
					} else {
						JOptionPane.showMessageDialog(null, "系统异常,请重试!");
					}
				}
			}
		});

		tf_delm_phone.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		JButton btn_call = new JButton("我已拨打");
		btn_call.setContentAreaFilled(false);
		btn_call.setPreferredSize(new Dimension(165, 30));
		btn_call.setFont(new Font("微软雅黑", 0, 16));
		// 拨打派件
		btn_call.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(null, "确定不是手滑?") == 0) {
					if (packdao.updateState(1,String.valueOf(expTable_p.getValueAt(expTable_p.getSelectedRow(), 0))) == true) {
						JOptionPane.showMessageDialog(null, "操作成功!");
						model1.setRowCount(0);
						packdao.showPack(model1);
						MainFrame.showTablepStyle();
					}
				}
			}
		});

		JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp2.setPreferredSize(new Dimension(1000, 50));
		jp2.add(label_delp_name);
		jp2.add(tf_delp_name);
		jp2.add(label_delp_phone);
		jp2.add(tf_delm_phone);
		jp2.add(btn_call);

		JPanel jp3 = new JPanel(new GridLayout(2, 1, 0, 0));
		jp3.add(jp1);
		jp3.add(jp2);

		Style.login_btn_style(btn_upd);
		Style.login_btn_style(btn_del);

		bottom.add(jp3);

	}

	public static void childPanel_delByMsg(JPanel content) {

		String colNames[] = { "单号", "收件人", "收件人电话", "收件人地址", "寄件人",
				"寄件人电话", "寄件人地址", "派件日期", "备注" };

		// 将表格变为只读
		model2 = new DefaultTableModel(colNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				// return super.isCellEditable(row, column);
				JTextField tf = new JTextField(model2.getValueAt(row, column).toString());
				tf.setEditable(false);
				expTable_m.getColumnModel().getColumn(column)
						.setCellEditor(new DefaultCellEditor(tf));

				return true;
			}

		};
		
		expTable_m = new JTable(model2);
		expTable_m.setFont(new Font("微软雅黑", 0, 14));

		expTable_m.setRowHeight(20);
		expTable_m.getTableHeader().setFont(new Font("微软雅黑",0,14));

		TableColumnModel tcm = expTable_m.getColumnModel();
		tcm.getColumn(1).setPreferredWidth(35);
		tcm.getColumn(4).setPreferredWidth(35);
		tcm.getColumn(8).setPreferredWidth(180);
		
		
		jsp_p1 = new JScrollPane(expTable_m);
		jsp_p1.setPreferredSize(new Dimension(1000, 400));
		jsp_p1.setViewportView(expTable_m);
		content.add(jsp_p1);

		JPanel bottom = new JPanel();
		content.add(bottom, BorderLayout.SOUTH);
		JLabel label_delp_name = new JLabel("收件人姓名");
		Style.main_child_label(label_delp_name);
		final JTextField tf_delp_name = new JTextField();
		Style.main_child_tf(tf_delp_name);
		tf_delp_name.setEditable(false);

		tf_delp_name.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));
		JLabel label_delp_phone = new JLabel("收件人电话");
		Style.main_child_label(label_delp_phone);
		final JTextField tf_delm_phone = new JTextField();
		Style.main_child_tf(tf_delm_phone);
		tf_delm_phone.setEditable(false);

		// 点击选中列事件
		expTable_m.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
					// // 获得点击单元格数据

					focusedRowIndex = expTable_m.rowAtPoint(e.getPoint());
					createPopupMenu(expTable_m);
					if (focusedRowIndex == -1) {
						return;
					}
					// 将表格所选项设为当前右键点击的行
					expTable_m.setRowSelectionInterval(focusedRowIndex,
							focusedRowIndex);
					// 弹出菜单
					m_popupMenu.show(expTable_m, e.getX(), e.getY());

				} else{
				if (expTable_m.getValueAt(expTable_m.getSelectedRow(), 0) != null) {
					s3 = String.valueOf(expTable_m.getValueAt(
							expTable_m.getSelectedRow(), 1));
					s4 = String.valueOf(expTable_m.getValueAt(
							expTable_m.getSelectedRow(), 2));
				}
				tf_delp_name.setText(s3);
				tf_delm_phone.setText(s4);

				MainFrame.showTablemStyle();
			}}
		});

		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp1.setPreferredSize(new Dimension(1000, 50));
		JButton btn_upd = new JButton("修改");
		JButton btn_del = new JButton("删除");
		jp1.add(btn_upd);
		jp1.add(btn_del);

		// 修改
		btn_upd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = expTable_m.getSelectedRow();
				PublicAttributes.tableSelObject = expTable_m.getValueAt(index,
						0);

				Object[] selectedMsg = null;
				selectedMsg = new Object[] { expTable_m.getValueAt(index, 0),
						expTable_m.getValueAt(index, 1),
						expTable_m.getValueAt(index, 2),
						expTable_m.getValueAt(index, 3),
						expTable_m.getValueAt(index, 4),
						expTable_m.getValueAt(index, 5),
						expTable_m.getValueAt(index, 6),
						expTable_m.getValueAt(index, 8) };
				UpdatePack.init(selectedMsg);

			}
		});
		// 删除
		btn_del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String delNo = String.valueOf(expTable_m.getValueAt(
						expTable_m.getSelectedRow(), 0));

				if (JOptionPane.showConfirmDialog(null, "确认删除?单号:" + delNo) == 0) {
					if (packdao.deletePack(delNo) == true) {
						JOptionPane.showMessageDialog(null, "删除成功!");
						model2.setRowCount(0);
						packdao.showPack(model2);
						MainFrame.showTablemStyle();
					} else {
						JOptionPane.showMessageDialog(null, "系统异常,请重试!");
					}
				}
			}
		});

		tf_delm_phone.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		JButton btn_send = new JButton("发送");
		btn_send.setContentAreaFilled(false);
		btn_send.setPreferredSize(new Dimension(165, 30));
		btn_send.setFont(new Font("微软雅黑", 0, 16));

		JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp2.setPreferredSize(new Dimension(1000, 50));
		jp2.add(label_delp_name);
		jp2.add(tf_delp_name);
		jp2.add(label_delp_phone);
		jp2.add(tf_delm_phone);
		jp2.add(btn_send);

		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp3.setPreferredSize(new Dimension(1000, 50));
		final JTextArea preskip = new JTextArea("您的快递到了,请在18:00之前前往全派食街申通快递领取!");
		preskip.setLineWrap(true);
		preskip.setPreferredSize(new Dimension(500, 45));
		preskip.setFont(new Font("微软雅黑", 0, 14));
		preskip.setBorder(BorderFactory.createLineBorder(Color.gray));
		jp3.add(preskip);

		// 信息派件
		btn_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(null, "确定发送信息?") == 0) {
					try {
						if (SendMsg.init(s4, preskip.getText()).equals("1")) {
							JOptionPane.showMessageDialog(null, "发送成功!");
							packdao.updateState(2, String
									.valueOf(expTable_m.getValueAt(
											expTable_m.getSelectedRow(), 0)));
							model2.setRowCount(0);
							packdao.showPack(model2);
							MainFrame.showTablemStyle();
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (HttpException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel jpt = new JPanel(new GridLayout(3, 1, 0, 0));
		jpt.add(jp1);
		jpt.add(jp2);
		jpt.add(jp3);

		Style.login_btn_style(btn_upd);
		Style.login_btn_style(btn_del);

		bottom.add(jpt);
	}

	public static void childPanel_record(JPanel content) {

		JPanel jPanel1 = new JPanel(new FlowLayout());

		jPanel1.setPreferredSize(new Dimension(1000, 50));

		JLabel label_sel_date = new JLabel("日期：", JLabel.LEFT);
		label_sel_date.setFont(new Font("微软雅黑", 0, 14));
		final JTextField tf_sel_yyyy = new JTextField(10);
		JLabel label_sel_yyyy = new JLabel("年", JLabel.LEFT);
		label_sel_date.setFont(new Font("微软雅黑", 0, 14));
		final JTextField tf_sel_MM = new JTextField(10);
		JLabel label_sel_MM = new JLabel("月", JLabel.LEFT);
		label_sel_date.setFont(new Font("微软雅黑", 0, 14));
		final JTextField tf_sel_dd = new JTextField(10);
		JLabel label_sel_dd = new JLabel("日", JLabel.RIGHT);
		label_sel_date.setFont(new Font("微软雅黑", 0, 14));
		JButton btn_select = new JButton("查询");

		Style.login_btn_style(btn_select);

		Style.main_child_label(label_sel_date);
		Style.main_child_label(label_sel_yyyy);
		Style.main_child_label(label_sel_MM);
		Style.main_child_label(label_sel_dd);

		Style.main_child_select_tf(tf_sel_yyyy);
		Style.main_child_select_tf(tf_sel_MM);
		Style.main_child_select_tf(tf_sel_dd);

		jPanel1.add(label_sel_date);
		jPanel1.add(tf_sel_yyyy);
		jPanel1.add(label_sel_yyyy);
		jPanel1.add(tf_sel_MM);
		jPanel1.add(label_sel_MM);
		jPanel1.add(tf_sel_dd);
		jPanel1.add(label_sel_dd);
		jPanel1.add(btn_select);

		String colNames[] = { "货号", "单号", "收件人", "收件人电话", "收件人地址", "寄件人",
				"寄件人电话", "寄件人地址", "备注", "状态", "派件方式", "派件日期", "派件人" };

		// 将表格变为只读
		model3 = new DefaultTableModel(colNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				// return super.isCellEditable(row, column);
				JTextField tf = new JTextField(model3.getValueAt(row, column)
						.toString());
				tf.setEditable(false);
				expTable_sel.getColumnModel().getColumn(column)
						.setCellEditor(new DefaultCellEditor(tf));

				return true;
			}

		};
		expTable_sel = new JTable(model3);
		expTable_sel.setFont(new Font("微软雅黑", 0, 14));

		expTable_sel.setRowHeight(20);
		expTable_sel.getTableHeader().setFont(new Font("微软雅黑",0,14));
		
		TableColumnModel tcm = expTable_sel.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(25);
		tcm.getColumn(2).setPreferredWidth(35);
		tcm.getColumn(5).setPreferredWidth(35);
		tcm.getColumn(9).setPreferredWidth(40);
		tcm.getColumn(10).setPreferredWidth(42);
		tcm.getColumn(11).setPreferredWidth(80);
		tcm.getColumn(12).setPreferredWidth(50);
		

		MainFrame.showTablehStyle();
		
		JScrollPane jsp = new JScrollPane(expTable_sel);
		jsp.setPreferredSize(new Dimension(1000, 500));
		jsp.setViewportView(expTable_sel);

		content.add(jPanel1);
		content.add(jsp);

		packdao.showHistory(model3);
		
		//查询
		btn_select.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str1 = tf_sel_yyyy.getText();
				String str2 = tf_sel_MM.getText();
				String str3 = tf_sel_dd.getText();
				if(str1.equals("")) str1 = "0";
				if(str2.equals("")) str2 = "0";
				if(str3.equals("")) str3 = "0";
				
				packdao.selectHistory(str1,str2,str3);
			}
		});
		
	}

	private static void createPopupMenu(final JTable jt) {
		m_popupMenu = new JPopupMenu();
		m_popupMenu.setPreferredSize(new Dimension(70, 35));
		JMenuItem signMenItem = new JMenuItem();
		signMenItem.setText("签    收");
		signMenItem.setFont(new Font("微软雅黑", 0, 14));
		signMenItem.setPreferredSize(new Dimension(70, 35));
		signMenItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (JOptionPane.showConfirmDialog(null, "确认签收?") == 0) {
					if (jt.getValueAt(focusedRowIndex, 7).equals("未派送")) {

						JOptionPane.showMessageDialog(null, "逗我呢?都没派送就签收?");
					} else {
						if (packdao.signPack(String.valueOf(jt
								.getValueAt(focusedRowIndex, 0))) == true) {
							JOptionPane.showMessageDialog(null, "签收成功!");
							InitMainChild.model1.setRowCount(0);
							packdao.showPack(model1);
							InitMainChild.model2.setRowCount(0);
							packdao.showPack(model2);
							MainFrame.showTablepStyle();
							MainFrame.showTablemStyle();
						}
					}
				}

			}
		});
		m_popupMenu.add(signMenItem);
	}

}
