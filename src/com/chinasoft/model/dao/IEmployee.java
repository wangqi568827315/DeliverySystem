package com.chinasoft.model.dao;

import javax.swing.JTable;

public interface IEmployee {

	public abstract String[] showEmp(String user);
	
	public abstract boolean updateEmp(int no, String name, String sex , String phone, String idcard, String address);
	
	public abstract void updateCount(int count, int no);
	
	public abstract void SAshowEmp();
	
	public abstract boolean fireEmp(int no);

	public abstract int checkRole(String username);
	
	public abstract void selByNo(int no);
	
}
