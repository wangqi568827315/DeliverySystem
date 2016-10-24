package com.chinasoft.model.dao;

import javax.swing.table.DefaultTableModel;

public interface IPack {
	
	public abstract boolean addExp(String orderno,String recname, String recphone, String recaddress,
			String posname, String posphone, String posaddress, String commit);
	
	public abstract void showPack(DefaultTableModel model);
	
	public abstract boolean updatePack(String[] strs);
	
	public abstract boolean deletePack(String delno);
	
	public abstract boolean updateState(int way, String no);
	
	public abstract boolean signPack(String no);
	
	public abstract void showHistory(DefaultTableModel model);

	public abstract boolean selectHistory(String yyyy, String MM, String dd);
}
