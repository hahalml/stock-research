package com.rock.stock_research.cache

import org.apache.commons.dbutils.QueryRunner
import com.rock.stock_research.dao.db.ConnectionManager
import org.apache.commons.dbutils.handlers.MapListHandler
import scala.collection.JavaConversions._
object StockCache {
  private var stocks: Set[(String, String)] = null
  initStocks
  
  
  private def initStocks = {

    val runner = new QueryRunner()
    val conn = ConnectionManager.getConnection
    val stocks = runner.query(conn, "select st_code, name  from stock ", new MapListHandler())
    conn.close()
    println("size1:" + stocks.size)
    this.stocks = stocks.map {
      stock => (stock("st_code").toString, stock("name").toString)
    }.toSet

  }
  def get(codeOrName: String) = {
    println("size:" + stocks.size)
    stocks.filter { stock => stock._1.toLowerCase().contains(codeOrName.toLowerCase()) || stock._2.toLowerCase.contains(codeOrName.toLowerCase) }
  }
}

object TestStockCache extends App {
  StockCache.get("a")
}









