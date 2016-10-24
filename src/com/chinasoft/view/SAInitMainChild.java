package com.chinasoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.chinasoft.controller.PublicAttributes;
import com.chinasoft.model.dao.IEmployee;
import com.chinasoft.model.dao.INotice;
import com.chinasoft.model.dao.ISalary;
import com.chinasoft.model.dao.IUpassword;
import com.chinasoft.model.dao.impl.IEmployeeImpl;
import com.chinasoft.model.dao.impl.INoticeImpl;
import com.chinasoft.model.dao.impl.ISalaryImpl;
import com.chinasoft.model.dao.impl.IUpasswordImpl;
import com.chinasoft.render.MyTableRender;


public class SAInitMainChild {
	
	private static ISalary iSalaryDao = new ISalaryImpl();
	private static IEmployee iEmpDao = new IEmployeeImpl();
	private static IUpassword iupDao = new IUpasswordImpl();
	private static INotice inoticeDao = new INoticeImpl();
	
	
	public static JTextField notice_jtf1 = null;
	public static JTextArea notice_jta1 = null;
	
	public static JButton[] jbtns = null;
	
	public static DefaultTableModel model1 = null;
	public static DefaultTableModel model2 = null;

	public static JTable empTable = null;
	
	private static JPanel jp_noticetitle = null;
	
	public static void initchild(Map<Integer, JPanel> child_panels) {

		for (Map.Entry<Integer, JPanel> panels : child_panels.entrySet()) {

			switch (panels.getKey()) {
			case 0:
				init_childPanel_selEmp(panels.getValue());
				break;
			case 1:
				init_childPanel_selSal(panels.getValue());
				break;
			case 2:
				init_childPanel_notice(panels.getValue());
				break;
			}
		}
	}
	
	private static void init_childPanel_selEmp(JPanel content){
		content.setLayout(new BorderLayout());
		
		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(1000,60));
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		content.add(bottom,BorderLayout.SOUTH);
		
		
		String colNames[]={"编号","姓名","性别","电话号码","身份证号","地址","注册日期","状态","本月派件数"};
		
		
		empTable=new JTable();
		empTable .setFont(new Font("微软雅黑", 0 , 14));
		empTable.setRowHeight(20);
		
		// 将表格变为只读
		model2 = new DefaultTableModel(colNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				JTextField tabletf = new JTextField(model2.getValueAt(row,column).toString());
				tabletf.setEditable(false);
				empTable.getColumnModel().getColumn(column).setCellEditor(new DefaultCellEditor(tabletf));

				return true;
			}
		};
		//show
		iEmpDao.SAshowEmp();
		
		empTable.setModel(model2);
		empTable.setDefaultRenderer(Object.class, new MyTableRender());
		empTable.getTableHeader().setFont(new Font("微软雅黑",0,14));
		
		TableColumnModel tcm = empTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(30);
		tcm.getColumn(4).setPreferredWidth(120);
		
		JScrollPane jsp1 = new JScrollPane(empTable);
		content.add(jsp1,BorderLayout.CENTER);
		//showEmpTableStyle();
		
		JButton btn_fire = new JButton("员工注销");
		btn_fire.setContentAreaFilled(false);
		btn_fire.setPreferredSize(new Dimension(175,30));
		btn_fire.setFont(new Font("微软雅黑", 0 , 16));
		
		bottom.add(btn_fire);
		
		//注销
		btn_fire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int index = empTable.getSelectedRow();
				String empname = String.valueOf(empTable.getValueAt(index, 1));
				int no = Integer.parseInt(String.valueOf(empTable.getValueAt(index, 0)));
				
				if (JOptionPane.showConfirmDialog(null, "确认注销员工 "+empname+" ?") == 0) {
					if (iEmpDao.fireEmp(no) == true) {
						JOptionPane.showMessageDialog(null, "操作成功!");
						iupDao.deleteUser(no);
						iEmpDao.SAshowEmp();
					} else {
						JOptionPane.showMessageDialog(null, "系统异常,请重试!");
					}
				}
			}
		});
		
		
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension(1000,60));
		top.setLayout(new FlowLayout(FlowLayout.RIGHT));
		content.add(top,BorderLayout.NORTH);
		
		JLabel jlno = new JLabel("编号:");
		Style.regist_label_style(jlno);
		
		final JTextField tfno = new JTextField();
		tfno.setFont(new Font("微软雅黑", 0 , 16));
		tfno.setPreferredSize(new Dimension(160,30));
		
		JButton btn_selEmp = new JButton("查询");
		Style.login_btn_style(btn_selEmp);
		
		top.add(jlno);
		top.add(tfno);
		top.add(btn_selEmp);
		
		
		btn_selEmp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tfno.getText().equals("")){
					iEmpDao.selByNo(Integer.parseInt(tfno.getText()));
				}else{
					iEmpDao.SAshowEmp();
				}
			}
		});
		
	}
	
	
	private static void init_childPanel_selSal(JPanel content){

		
		content.setLayout(new BorderLayout());
		
		JPanel top =new JPanel();
		content.add(top,BorderLayout.NORTH);
		top.setLayout(new FlowLayout(FlowLayout.RIGHT));
		top.setPreferredSize(new Dimension(1000,60));
		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(1000,60));
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		content.add(bottom,BorderLayout.SOUTH);
		
		JLabel jl_name = new JLabel("姓名");
		Style.regist_label_style(jl_name);
		
		final JTextField tf_name = new JTextField();
		Style.regist_textfield_style(tf_name);
		
		JLabel jl_date = new JLabel("日期");
		Style.regist_label_style(jl_date);
		
		final JTextField tf_yyyy = new JTextField("年");
		tf_yyyy.setFont(new Font("微软雅黑", 0 , 16));
		tf_yyyy.setPreferredSize(new Dimension(70,30));
		final JTextField tf_MM = new JTextField("月");
		tf_MM.setFont(new Font("微软雅黑", 0 , 16));
		tf_MM.setPreferredSize(new Dimension(70,30));
		final JTextField tf_dd = new JTextField("日");
		tf_dd.setFont(new Font("微软雅黑", 0 , 16));
		tf_dd.setPreferredSize(new Dimension(70,30));
		
		
		JButton btn_check = new JButton("查询");
		Style.login_btn_style(btn_check);
		
		JButton btn_insert = new JButton("添加");
		Style.login_btn_style(btn_insert);
		
		
		JButton btn_delete = new JButton("删除");
		Style.login_btn_style(btn_delete);
		
		
		btn_insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateSalary.init();
			}
		});
		
		
		
		btn_check.setContentAreaFilled(false);
		btn_insert.setContentAreaFilled(false);
		
		top.add(jl_name);
		top.add(tf_name);
		top.add(jl_date);
		top.add(tf_yyyy);
		top.add(tf_MM);
		top.add(tf_dd);
		top.add(btn_check);
		bottom.add(btn_insert);
		bottom.add(btn_delete);
		
		
		String colNames[]={"编号","员工","基础工资","派件数","每件奖金","总工资","日期"};
		
		
		final JTable salTable_s=new JTable();
		salTable_s .setFont(new Font("微软雅黑", 0 , 14));
		salTable_s.setRowHeight(20);
		salTable_s.getTableHeader().setFont(new Font("微软雅黑",0,14));
		
		
		// 将表格变为只读
		model1 = new DefaultTableModel(colNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				JTextField tabletf = new JTextField(model1.getValueAt(row,column).toString());
				tabletf.setEditable(false);
				salTable_s.getColumnModel().getColumn(column).setCellEditor(new DefaultCellEditor(tabletf));

				return true;
			}
		};
		iSalaryDao.showSalary();
		salTable_s.setModel(model1);
		showTableStyle(salTable_s);
		
		
		JScrollPane jsp1 = new JScrollPane(salTable_s);
		content.add(jsp1);
		
		//删除
		btn_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int index = salTable_s.getSelectedRow();
				int sno = Integer.parseInt(String.valueOf(salTable_s.getValueAt(index, 0)));
				
				if (JOptionPane.showConfirmDialog(null, "确认删除?") == 0) {
					if (iSalaryDao.deleteSalary(sno) == true) {
						JOptionPane.showMessageDialog(null, "删除成功!");
						iSalaryDao.showSalary();
					} else {
						JOptionPane.showMessageDialog(null, "系统异常,请重试!");
					}
				}
			}
		});
		
		//查询
		btn_check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str1 = tf_name.getText();
				String str2 = tf_yyyy.getText().replace("年", "");
				String str3 = tf_MM.getText().replace("月", "");;
				String str4 = tf_dd.getText().replace("日", "");;
				
				if(str1.equals("")) str1 = "0";
				if(str2.equals("")) str2 = "0";
				if(str3.equals("")) str3 = "0";
				if(str4.equals("")) str4 = "0";
				
				iSalaryDao.checkSalary(str1,str2,str3,str4);
			}
		});
		
	}
	
	public static void init_childPanel_notice(JPanel content) {
		JPanel jp1 = new JPanel();
		jp_noticetitle = new JPanel();
		
		jp1.setPreferredSize(new Dimension(820,580));
		jp_noticetitle.setPreferredSize(new Dimension(180,580));
		
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
		
		JPanel jp1_3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp1_3.setPreferredSize(new Dimension(820,50));
		JButton jbtn_add = new JButton("发布新公告");
		jbtn_add.setContentAreaFilled(false);
		jbtn_add.setPreferredSize(new Dimension(170,30));
		jbtn_add.setFont(new Font("微软雅黑", 0 , 16));
		
		jp1_2.add(jbtn1);
		jp1_2.add(jbtn2);
		jp1_3.add(jbtn_add);
		
		
		inoticeDao.showNoticeTitle();
	
		
		initjbtns();
		
		
		
		jp_noticetitle.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		
		jbtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(PublicAttributes.noticeNo >= InitMainChild.notice_length){
					PublicAttributes.noticeNo = InitMainChild.notice_length;
				}else {
					PublicAttributes.noticeNo += 1;
				}

				inoticeDao.showNotice(notice_jtf1, notice_jta1);
			}
		});
		jbtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PublicAttributes.noticeNo -= 1;
				if(PublicAttributes.noticeNo == 0){
					PublicAttributes.noticeNo = 1;
				}
				inoticeDao.showNotice(notice_jtf1, notice_jta1);
				
			}
		});
		
		
		jbtn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateNotice.init();
			}
		});
		
		jp1.add(jp1_1);
		jp1.add(jp1_2);
		jp1.add(jp1_3);
		content.add(jp1);
		content.add(jp_noticetitle);
		
	}
	
	
	
	public static void showTableStyle(JTable table){
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
        for (int i = 0; i < table.getColumnCount(); i++) {  
        	table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);  
        }  
        
	}

	
	public static void initjbtns(){
		for (int i = 0; i < jbtns.length; i++) {
			jbtns[i].setHorizontalAlignment(JButton.LEFT);
			jbtns[i].setContentAreaFilled(false);
			jbtns[i].setBorder(BorderFactory.createLineBorder(Color.white));
			
			jbtns[i].setPreferredSize(new Dimension(160,20));
			jbtns[i].setFont(new Font("微软雅黑",0,16));
			jp_noticetitle.add(jbtns[i]);
		}
		
		for(int w = 0 ; w < jbtns.length ; w++){
			jbtns[w].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i = 0 ; i < jbtns.length ; i++){
						System.out.println(e.getSource());
						if(e.getSource().toString().split("text=")[1].split(",def")[0].equals(jbtns[i].getText())){
							PublicAttributes.noticeNo =InitMainChild.notice_length- i;
							inoticeDao.showNotice(notice_jtf1, notice_jta1);
						}
					}
				}
			});
		}
	}
}














