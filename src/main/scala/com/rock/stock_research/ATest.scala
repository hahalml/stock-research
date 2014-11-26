package com.rock.stock_research

import java.util.Locale
import java.lang.String
import java.lang.Double
import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.dao.db.ConnectionManager
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.BeanListHandler
import scala.collection.JavaConversions._
import org.apache.commons.dbutils.handlers.MapListHandler
import a.A
import com.rock.stock_research.entity.Stock

object ATest extends App{

 
  val objArr:Array[Object] = null
  val conn = ConnectionManager.getConnection
  val runner = new QueryRunner
  val stocks = runner.query(conn, "select * from stock where id > ? and date>? limit 10", new BeanListHandler[Stock](classOf[Stock]) ,Array(300),Array("2000-09-09"))
//  val stocks = runner.query(conn, "select * from stock where id > ? limit 10", new BeanListHandler[A](classOf[A]) ,Array(300))
//  val stocks = runner.query(conn, "select * from stock where id > ? limit 10", new MapListHandler() ,Array(300))
  println("size:"+stocks.size())
  conn.close()
 
//  stocks.foreach((stock:St2) => println(stock.id+":"+stock.st_code))
  
  
 
   
}

class St2(){
  var id:Int = -1
  var st_code:String = null
}









