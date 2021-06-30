 package com.njupt.Utils;



import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.njupt.test.MqttSubscribe;
import org.apache.commons.dbcp.BasicDataSource;



public class JDBCUtil {
	

	//数据源
	private static BasicDataSource dataSource = null;

	private static String path;



	static {
		Properties pros = new Properties();

		try {

			path = MqttSubscribe.path2;

			//pros.load(JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			InputStream InputStream = new BufferedInputStream(new FileInputStream(new File(path)));
			pros = new Properties();
			pros.load(InputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}	

// 		String driver_class_name = pros.getProperty("mysqlDriver");
//		String url = pros.getProperty("mysqlURL");
//		String username = pros.getProperty("mysqlUser");
//		String password = pros.getProperty("mysqlPwd");
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(pros.getProperty("mysqlDriver"));
		dataSource.setUrl(pros.getProperty("mysqlURL"));
		dataSource.setUsername(pros.getProperty("mysqlUser"));
		dataSource.setPassword(pros.getProperty("mysqlPwd"));

	}

	public static DataSource getDataSource() {

		return dataSource;
	}

	//释放资源
	public static void close(ResultSet rs,Statement ps,Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement ps,Connection conn) {
		
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
