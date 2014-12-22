package com.rock.stock_research.test

import com.rock.stock_research.dao.db.ConnectionManager
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.MapListHandler
import scala.collection.JavaConversions._

object RemoveDup {
  def main(args: Array[String]) {
	  println(getDiffFromAll.size)
	  val allIds = getAllIdOfStocks
	  val notDupIds = getDiffFromAll.map{stock => stock("id").toString.toInt}.toSet
	  val conn = ConnectionManager.getConnection
      val runner = new QueryRunner
      var i = 0;
	  for(id <- allIds){
	    if(!notDupIds.contains(id)){
	      runner.update(conn, "delete from stock where id = "+id)
	    }
	    i += 1
	    println(i)
	  }
	  conn.close
	  println("done")
  }
  
  def getAllIdOfStocks = {
     val conn = ConnectionManager.getConnection
    val runner = new QueryRunner
    val stocks = runner.query(conn, "select id  from stock", new MapListHandler)
    conn.close()
    stocks.map{stock => stock("id").toString.toInt}
  }
  def getDiffFromAll = {
    val conn = ConnectionManager.getConnection
    val runner = new QueryRunner
    val stocks = runner.query(conn, "select id, st_code, date, time from stock", new MapListHandler)
    conn.close()
    val stList = stocks.groupBy{
      stock => stock("st_code").toString + stock("date").toString + stock("time").toString
    }.values.toSeq.map{
      stocks => stocks(0)
    }
    stList
  }
  
   
}