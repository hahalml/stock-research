package com.rock.stock_research.cache

import org.apache.commons.dbutils.QueryRunner
import com.rock.stock_research.dao.db.ConnectionManager
import org.apache.commons.dbutils.handlers.MapListHandler
import scala.collection.JavaConversions._
object StockCache extends App {
  private var stocks = { //Set.empty[(String, String)] // symbol, name
    val runner = new QueryRunner()
    val conn = ConnectionManager.getConnection
    val stocks = runner.query(conn, "select st_code, name  from stock where id = ?", new MapListHandler())
    conn.close()
    stocks.map {
      stock => (stock("st_code").toString, stock("name").toString)
    }.toSet
  }
  
  def get(codeOrName: String) = {
    stocks.filter { stock => stock._1.toLowerCase().contains(codeOrName.toLowerCase()) || stock._2.toLowerCase.contains(codeOrName.toLowerCase) }
  }

  
}