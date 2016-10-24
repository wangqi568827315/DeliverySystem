package com.chinasoft.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistPattern {

	public static void main(String[] args){
		System.out.println(checkPhone("18771761260"));
		System.out.println(checkCode("420528199502285011"));
	}
	
	public static boolean checkPhone(String phone){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Matcher m = p.matcher(phone);  
		return m.matches();  
	}
	
	public static boolean checkCode(String code){
		Pattern p = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");  
		Matcher m = p.matcher(code);  
		return m.matches();  
	}

}
