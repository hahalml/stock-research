package a;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.alibaba.fastjson.JSON;
import com.rock.stock_research.dao.db.ConnectionManager;
import com.rock.stock_research.entity.Stock;

public class A {

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	String id;
	public static void main(String[] args) throws Exception {

	    Connection conn =	ConnectionManager.getConnection();
		QueryRunner r = new QueryRunner();

		List stocks = r.query(conn, "select * from stock where id > ? and date>? limit 10", new BeanListHandler<St>(St.class) , new Object[]{300,"2000-09-09"});

	//	r.query(conn, "select * from stock where id > ?",new beanh);
		System.out.println(stocks.size());
		St st = new St();
		st.setId(1);
		st.setSt_code("abc");
		System.out.println(JSON.toJSONString(st));
		Stock stock = new Stock();
		stock.setId(12345);
		System.out.println(JSON.toJSONString(stock)); 
		 
		r.update(conn, "", new Object[]{});
		r.update(conn, "", 3);
	}
	
	
	public static class St{
		int id;
		String st_code;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getSt_code() {
			return st_code;
		}
		public void setSt_code(String st_code) {
			this.st_code = st_code;
		}
		
	}
}
