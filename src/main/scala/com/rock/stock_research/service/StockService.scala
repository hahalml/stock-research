package com.rock.stock_research.service

import com.rock.stock_research.dao.StockDao
import scala.collection.JavaConversions._
import com.rock.stock_research.entity.Stock
import com.alibaba.fastjson.JSON
import scala.util.parsing.json.JSON
import com.rock.stock_research.model.StockStatistics
import com.rock.stock_research.model.StockStatistics
import com.rock.stock_research.util.NumUtil
import com.rock.stock_research.util.DateUtil

class StockService extends IStockService {
  System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

  def statisticOfPeriod(symbols: Seq[String], startDate: String = "1900-01-01", endDate: String = "9999-01-01", offset: Int = 0, num: Int = Int.MaxValue) = {
    var stocks = symbols match {
      case null => StockDao.getSome(null, startDate, endDate, offset, num).toSeq
      case _ =>
        symbols.map {
          symbol => StockDao.getSome(symbol, startDate, endDate, offset, num).toSeq
        }.flatten
    }
    stocks.groupBy((stock: Stock) => stock.getSt_code).values.map {
      stocks =>
        val lastIndex = stocks.length - 1
        val sortedStocks = stocks.sortBy { stock: Stock => stock.getDate }
        val statistic = new StockStatistics
        statistic.curr = sortedStocks(lastIndex).curr_price
        statistic.min = sortedStocks.head.curr_price
        statistic.max = sortedStocks(sortedStocks.length - 1).curr_price
        statistic.avg = sortedStocks.foldLeft(0.0)((s1: Double, s2: Stock) => s1 + s2.curr_price)/sortedStocks.length
        statistic.startDate  = sortedStocks(0).date
        statistic.endDate = sortedStocks(lastIndex).date
        statistic.incPercent = NumUtil.incPercent(sortedStocks(0).curr_price , sortedStocks(lastIndex).curr_price )
        statistic.lastIncPercent = NumUtil.incPercent(sortedStocks(lastIndex - 1).curr_price , sortedStocks(lastIndex).curr_price )
        statistic.name = sortedStocks(0).name 
        statistic.totalDealNum = sortedStocks.foldLeft(0)((curr:Int, stock:Stock)=>curr + stock.deal_stock_num ) 
        statistic.totalDealPrice = sortedStocks.foldLeft(0.0)((curr:Double, stock:Stock)=>curr + stock.deal_price  ) 
        statistic
    }.toSeq

     
  }

  def test{
    val stocks = Seq.empty[Stock]
    val a = 0
    stocks.groupBy((stock: Stock) => stock.getSt_code).values.map {
      stockDatas =>
        val groupedStockDatas = a match {
		   case 1 => stockDatas.groupBy{stock:Stock => stock.date.substring(0,4)}.values.toSeq // group by year
		   case 2 => stockDatas.groupBy{stock:Stock => stock.date.substring(0,7)}.values.toSeq // group by month
		   case 3 => stockDatas.groupBy{stock:Stock => stock.date.substring(0,10)}.values.toSeq //group by day
		   case _ => stockDatas.groupBy{stock:Stock => stock.date.substring(0,4)+"_"+DateUtil.getDateOfWeek(stock.date)}.values.toSeq //group by week
		}
		        
        
    }.toSeq
    
  }
  
  
}

object Abc extends App {
  case class Obj(v: Int)

  val seq = Seq(Obj(1), Obj(2), Obj(3))
  val total = seq.foldLeft(0)((a: Int, obj: Obj) => a + obj.v)
  println("total:"+total)
}










