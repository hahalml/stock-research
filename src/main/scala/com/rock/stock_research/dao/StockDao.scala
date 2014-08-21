package com.rock.stock_research.dao

import com.rock.stock_research.entity.Stock
import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.entity.Stock
import sorm._
import org.apache.commons.dbutils.QueryRunner
import com.rock.stock_research.dao.db.ConnectionManager
import org.apache.commons.dbutils.handlers.MapListHandler
import scala.collection.JavaConversions._
//https://github.com/sorm/sorm/blob/master/src/main/scala/sorm/Querier.scala
object StockDao{
  private val fields = " name, open_price, curr_price, date, st_code, time, deal_stock_num, deal_price, prev_close_price "
  def getStock(stCode:String, startDate:String, endDate:String) = {
    
  }
  
  def getStocks(startDate:String, endDate:String)  = {
    var res = (startDate, endDate) match  {
	  case (null, null) => DB.query[Stock].whereEqual("stCode", "sz300099").limit(1000).fetch
	  case (null, endDate) if endDate != null => DB.query[Stock].whereSmallerOrEqual("date",endDate).fetch
  	  case (startDate, null) if startDate != null => DB.query[Stock].whereSmallerOrEqual("date",endDate).fetch
	  case (startDate, endDate) => DB.query[Stock].whereLargerOrEqual("date",startDate).whereSmallerOrEqual("date",endDate).fetch
	}
    res.groupBy((stock:Stock)=>stock.date).values.toStream.map((sts:Stream[Stock])=>sts(0))
   
  }
  
  def getStocks(qo:QueryOption) = {
    val query = new QueryRunner()
    val conn = ConnectionManager.getConnection
    var sql = qo match {
	  case QueryOption(null, null, _) => "select "+fields+" from stock" 
	  case QueryOption(start, null, _) => "select "+fields+" from stock where date >='"+start+"' " 
	  case QueryOption(null, end, _) =>  "select "+fields+" from stock where date <='"+end+"'" 
	  case QueryOption(start, end, _) =>  "select "+fields+" from stock where date>='"+start+"' and date <='"+end+"'" 
	}
  //  sql = "select name, open_price, curr_price, date, st_code, time, deal_stock_num, deal_price, prev_close_price  FROM stock WHERE DATE >='2014-07-11'"
    println("sql:"+sql)
    val stocksMap = query.query(conn, sql, new MapListHandler)
    ConnectionManager.closeConnection(conn)
     
    val iterator = stocksMap.iterator();
    val stocks = ArrayBuffer.empty[Map[String,Any]]
    while (iterator.hasNext()) {
    	val map = iterator.next()
    	stocks += map.toMap
    }
    stocks
  }
  
  // SELECT * FROM table LIMIT 5,10; // 检索记录行 6-15
  //为了检索从某一个偏移量到记录集的结束所有的记录行，可以指定第二个参数为 -1：
  
  
  
  
  
  
  
  
}



//name longtext
//open_price double
//curr_price double
//datelong text
//st_code longtext
//time longtext
//deal_stock_num bigint(20)
//deal_price double
//prev_close_price double
//original_str longtext














