package com.chinasoft.model.dao;

public interface ISalary {
	
	public abstract void showComboItem();
	
	public abstract boolean addSalary(int no , double basic, double bonus);
	
	public abstract void showSalary();
	

	public abstract boolean deleteSalary(int sno);

	public abstract void checkSalary(String str1, String str2, String str3,String str4);
}
