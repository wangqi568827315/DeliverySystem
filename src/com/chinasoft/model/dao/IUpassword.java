package com.chinasoft.model.dao;

public interface IUpassword {

	public abstract boolean regist(String user,String password,String name, String sex, String phone, String idcard, String address, int role);
	
	public abstract boolean checkPwd(String user, String pwd);
	
	public abstract boolean changePwd(String user, String pwd, String newpwd);
	
	public abstract boolean checkReUser(String user);
	
	public abstract void deleteUser(int no);
}
