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
import com.rock.stock_research.statistic.PeriodStatistic
import com.rock.stock_research.statistic.DayPeriod
import com.rock.stock_research.statistic.TimePeriod
import com.rock.stock_research.statistic.TimePeriod
import com.rock.stock_research.dao.DbDao
import com.rock.stock_research.statistic.StatisticResult
import com.rock.stock_research.statistic.WeekPeriod
import com.rock.stock_research.statistic.MonthPeriod
import com.rock.stock_research.statistic.YearPeriod
import com.rock.stock_research.dao.Table
import com.rock.stock_research.types.AliasType._



class StockService extends IStockService {
  System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

 

 override def getStock(table: String, fields: Set[String], eqField: (String, Any) = null, startDate:String = "1900-01-01", endDate:String = "9999-01-01") = {
    val stocks = DbDao.get(table, fields, eqField).filter{
      stock => stock("date").toString().compareTo(startDate) >= 0 && stock("date").toString().compareTo(endDate) <=0
    }
     
    val groupedStocks = stocks.groupBy{stock => stock("st_code")}.values.toSeq
    val period = DayPeriod
    val s = groupedStocks.map{
      stocks => groupedByPeriod(stocks, period).map{stocks => StatisticResult(stocks, "")}
    }
    stocks
  }

   
  private def groupedByPeriod(stocks:Seq[Map[String, Object]], period:TimePeriod) = {
    val res = period match {
	  case DayPeriod => stocks.groupBy{stock => stock("date").toString}.values.toSeq
	  case WeekPeriod => stocks.groupBy{stock => stock("date").toString.substring(0,4) +"_w_"+  DateUtil.getDateOfWeek(stock("date").toString)}.values.toSeq
	  case MonthPeriod => stocks.groupBy{stock => stock("date").toString.substring(0,7)}.values.toSeq
	  case YearPeriod => stocks.groupBy{stock => stock("date").toString.substring(0,4)}.values.toSeq
	}
    res.sortBy(stocks => stocks.head("date").toString)
  }
  
  override  def getStockStat(field:String, symbols:Seq[String], startDate:String = "1900-01-01", endDate:String = "9999-01-01"):MatrixData = {
    val eqFields = symbols.map{
      symbol => ("st_code",symbol)
    };
	val stocks = DbDao.get("stock", Table.stockFields , eqFields).filter{
      stock =>  stock("date").toString().compareTo(startDate) >= 0 && stock("date").toString().compareTo(endDate) <=0
    }

    val groupedStocks = stocks.groupBy{stock => stock("st_code")}.values.toSeq
    val period = DayPeriod
    val s = groupedStocks.map{
      stocks => groupedByPeriod(stocks, period).map{stocks => StatisticResult(stocks, "")}
    }
      null
  }

}
 










