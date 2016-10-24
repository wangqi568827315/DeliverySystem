package com.chinasoft.model.DBFactory;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBFactory {

	private static final String DRIVERCLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String CONURL = "jdbc:sqlserver://localhost:1433;databaseName=Delivery";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";
	
	private static Connection connection;
	
	static{
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//私有化构造方法
	protected DBFactory(){
		
	}
	
	//判断连接是否存在
	public static Connection getInstance(){
		
		if(connection == null){
			try {
				connection = DriverManager.getConnection(CONURL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
	//全表查询方法(泛型)
	public static <T> List<T> getListData(Class<T> type,String Sql)
	{
		List<T> result = new ArrayList<T>();
		PreparedStatement pstatement = null;
		connection = getInstance();
		
		try {
			pstatement = connection.prepareStatement(Sql);
			
			ResultSet list = pstatement.executeQuery();
			
			LoadObject(list,result,type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	//全表查询方法(泛型)  获取数据源
	private static <T> void LoadObject(ResultSet list,List<T> result,Class<T> type)
	{
		try {
			// 获取此 ResultSet 对象的列的编号、类型和属性
			ResultSetMetaData rsmd = list.getMetaData();
			//构造方法
			Constructor<T> constructor=type.getConstructor(null);
			//类方法
			Method[] methods = type.getMethods();
			
			while(list.next())
			{
				//获取构造方法
				T obj = constructor.newInstance(null);

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					//获取列名
					String colName = rsmd.getColumnName(i);
					
					Method execMethod =null;
					
					for (Method method : methods) {
						//如果方法名里面有"get"  结束本次循环进行下次循环
						if(method.getName().contains("get"))
						{
							continue;
						}
						//方法名全小写
						String methodname = method.getName().toLowerCase();
						//去掉下划线和美元符号,剩下纯小写英文字母
						String temp = colName.replace("_", "").replace("$","").toLowerCase();
						//如果方法为  "set"+temp ,  结束循环
						if(methodname.contains(temp))
						{
							execMethod = method;
							break;
						}
						
					}
					//由obj调用execMethod方法(setXXX),传值  list.getObject(i)
					execMethod.invoke(obj, list.getObject(i));
				}
				//结果集添加一行
				result.add(obj);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	//全表更新方法
	public static <T> T getObject(Class<T> type,String Sql)
	{
		List<T> result = new ArrayList<T>();
		PreparedStatement pstatement = null;
		connection = getInstance();
		
		try {
			pstatement = connection.prepareStatement(Sql);
			
			ResultSet list = pstatement.executeQuery();
			LoadObject(list,result,type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result.size()>0)
		{
			return result.get(0);
		}else {
			return null;
		}
		
	}
	
	public static boolean executeUpdate(String Sql)
	{
		PreparedStatement pstatement = null;
		connection = getInstance();
		int result =0;
		try {
			pstatement = connection.prepareStatement(Sql);
			result=pstatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result>0;
	}
	
}








