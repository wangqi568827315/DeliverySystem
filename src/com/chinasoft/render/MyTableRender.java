package com.chinasoft.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class MyTableRender implements TableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO Auto-generated method stub
		Component render = new DefaultTableCellRenderer().getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		Color forColor=null;;
		Color backColor=Color.white;;
		
		if (table.getValueAt(row,7).toString().trim().equals("离职")) {  
			forColor = Color.gray;
        }else {
        	forColor = Color.black;
		}
		if(isSelected){
			forColor = Color.red;
		}
		 if (row % 2 == 0) {  
    		 backColor=Color.white; //设置奇数行底色 
         } else{  
        	 backColor=new Color(206, 231, 255); //设置偶数行底色  
         }  
		render.setForeground(forColor);
		render.setBackground(backColor);
		return render;
	}

}
