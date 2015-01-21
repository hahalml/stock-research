package com.rock.stock_research.sample

import com.rock.stock_research.dao.StockDao
import com.rock.stock_research.dao.DbDao
import com.rock.stock_research.util.NumUtil
import java.lang.Double

object StockUsageCase extends App {
  //StockDao.getSome(stCode, startStr, endStr, offset, num)
	import UsageCase._
  
	val stocks = getPriceIncDataBetween("2000-09-09", "8888-09-09");
	println(stocks)
	println("stocks size:"+stocks.size)
	 
}

object UsageCase{
  def getSymbols = {
    DbDao.get("stock", Set("st_code")).map {
      stock => stock("st_code").toString
    }.toSet
  }
   
  def getPriceIncDataBetween(start:String, end:String, min:Double = -20, max:Double = 20) = {
    val symbols = getSymbols
    symbols.map{
      symbol =>
      	val stocks = DbDao.get("stock", Set("date", "curr_price"), ("st_code"->symbol)).sortBy{
          stock => stock("date").toString
        }
      	(symbol, NumUtil.incPercent(stocks.head("curr_price"), stocks.last("curr_price")))
    }.toSeq.filter{
      stock => 
        val v1 = stock._2.replace("%","")
        val n  = if(v1 == "-") -10000 else Double.parseDouble(v1)
        n > min && n < max
    }.sortWith{
      (s1, s2) => 
        val v1 = s1._2.replace("%", "")
        val v2 = s2._2.replace("%", "")
        val n1 = if(v1 == "-") -10000 else Double.parseDouble(v1)
        val n2 = if(v2 == "-") -10000 else Double.parseDouble(v2)
        n1 > n2
    }
  }
}








