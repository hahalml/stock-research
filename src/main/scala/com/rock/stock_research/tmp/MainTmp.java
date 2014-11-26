package com.rock.stock_research.tmp;

import java.sql.Connection;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;

import com.rock.stock_research.dao.db.ConnectionManager;

public class MainTmp {

	public static void main(String[] args) throws Exception {
		 
		//insertUser();
//		insertComment();
		insertPhraise();
		System.out.println("done");
	}
	private static void insertPhraise()throws Exception{
		Connection conn = ConnectionManager.getConnection();
		QueryRunner runner = new QueryRunner();
		for(int i=0; i<100;i++){
			runner.update(conn, "insert into appstartup.praise(userId) values(?)", 1);
		}
		conn.close();
	}
	private static void insertComment()throws Exception{

	}
	private static void insertUser()throws Exception{
		Connection conn = ConnectionManager.getConnection();
		QueryRunner runner = new QueryRunner();
		for(int i=0; i<100;i++){
			runner.update(conn, "insert into appstartup.user(passWord, email) values(?,?)", "123","jordan"+i+"@163.com");
		}
		conn.close();
	}
}
