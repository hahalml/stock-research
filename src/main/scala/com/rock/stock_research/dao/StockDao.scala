package com.rock.stock_research.dao

import com.rock.stock_research.entity.Stock
import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.entity.Stock
import org.apache.commons.dbutils.QueryRunner
import com.rock.stock_research.dao.db.ConnectionManager
import scala.collection.JavaConversions._
import com.rock.stock_research.dao._
import org.apache.commons.dbutils.handlers.BeanListHandler
import org.apache.commons.dbutils.handlers.BeanHandler
import com.alibaba.fastjson.JSON
import com.rock.stock_research.service.StockService
import com.rock.stock_research.service.IStockService
import com.lambdaworks.jacks.JacksMapper
import com.rock.stock_research.constant.ByWeek

    
object StockDao {
  private val fields = " name, open_price, curr_price, date, st_code, time, deal_stock_num, deal_price, prev_close_price "

  def get(id: Int) = {
    val runner = new QueryRunner()
    val conn = ConnectionManager.getConnection
    val stock = runner.query(conn, "select "+fields+" from stock where id = ?", new BeanHandler(classOf[Stock]), Array(id))
    conn.close()
    stock
  }

  def getSome(stCode: String = null, startStr: String = "1900-01-01", endStr: String = "9999-01-01", offset: Integer = 0, num: Integer = Integer.MAX_VALUE) = {
    val start = if(startStr == null || startStr.trim().isEmpty()) "1900-01-01" else startStr
    val end = if(endStr == null || endStr.trim().isEmpty()) "9999-01-01" else endStr
    val runner = new QueryRunner()
    val conn = ConnectionManager.getConnection
    var args: Array[Object] = Array(stCode, start, end, offset, num)
    val stocks = if (stCode == null) {
      args  = Array(start, end, offset, num)
      runner.query(conn, "select "+fields+" from stock where  date >= ? and date <= ? order by date limit ?, ?",
        args, new BeanListHandler[Stock](classOf[Stock]))
    } else {
      runner.query(conn, "select "+fields+" from stock where st_code = ? and date >= ?  and date <= ? order by date limit ?, ?",
        args, new BeanListHandler[Stock](classOf[Stock]))
    }
    conn.close()
    stocks
  }

  def main(args: Array[String]) {
//    val runner = new QueryRunner()
//    val conn = ConnectionManager.getConnection
//    var arr: Array[Object] = Array("2000-01-01", "2014-09-01")
//    val stocks = StockDao.getSome("sz300101") // runner.query(conn, "select * from stock where date >= ? and date <=?", arr, new BeanListHandler(classOf[Stock]))
//    conn.close()
//    print(stocks.size())
//	  val stock = new Stock()
//	  stock.id = 123
//	  stock.st_code = "sz600321"
//	   
//	  val js = JSON.toJSONString(stocks,true)
//	  println(js)
    
//     val stockService: IStockService = new StockService()
////
////   
////
//    val stocks = stockService.getStatisticsAtPeriod(Seq())
//    print(stocks.size())
//    println(JacksMapper.writeValueAsString(stocks, false))
  
    
//    val stocks = StockDao.getSome("sz300101")
//    println(stocks.size())
//    val seq = Seq(stocks,stocks)
//    val list = seq.reduceLeft(_ ++ _)
//    println(list.size())
//    val stCodes = Seq("sz300101")
//    val list =  stCodes.map((stCode: String) => StockDao.getSome(stCode)).reduceLeft(_ ++ _)
//    println(list.size())
//    val stockService: IStockService = new StockService()
//     val stCodes = null
//      val stocksInfos = stockService.getStatisticsByPeriods(stCodes, ByWeek)
//      println(JacksMapper.writeValueAsString(stocksInfos))
  }
}
 

    
//
//  val stocks = StockDao.getSome("sz300101")
//      print(stocks.size())















