package com.rock.stock_research.service

import com.rock.stock_research.dao.StockDao
import scala.collection.JavaConversions._
import com.rock.stock_research.entity.Stock
import com.alibaba.fastjson.JSON
import scala.util.parsing.json.JSON
import com.rock.stock_research.util.NumUtil
import com.rock.stock_research.util.DateUtil

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
import com.rock.stock_research.statistic.ComparedStatisticResult
import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.statistic.TimePeriod
import com.rock.stock_research.statistic.TimePeriod
import com.rock.stock_research.cache.StockCache

class StockService extends IStockService {
  System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
  override def autocomplete(query:String):Seq[Map[String, String]] = {
	  StockCache.get(query).map{
		  stock => Map("symbol"->stock._1, "name"->stock._2)
	  }.toSeq.take(10)
  }
  override def getStockStat(field: String, symbols: Seq[String], startDate: String = "1900-01-01", endDate: String = "9999-01-01", period: TimePeriod = DayPeriod): Seq[Seq[ComparedStatisticResult]] = {
    val eqFields = symbols.map {
      symbol => ("st_code", symbol)
    };
    val stocks = DbDao.get("stock", Table.stockFields, eqFields).filter {
      stock => stock("date").toString().compareTo(startDate) >= 0 && stock("date").toString().compareTo(endDate) <= 0
    }

    val groupedStocks = stocks.groupBy { stock => stock("st_code") }.values.toSeq

    val statisticResults = groupedStocks.map {
      stocks => groupedByPeriod(stocks, period).map { stocks => StatisticResult(stocks, field) }
    }
    val statResults = statisticResults.map {
      statResults =>
        var tmp = statResults.+:(new StatisticResult)
        var res = ArrayBuffer.empty[ComparedStatisticResult]
        while (!tmp.tail.isEmpty) {
          res += ComparedStatisticResult(tmp.head, tmp.tail.head)
          tmp = tmp.tail
        }
        res

    }
    makeSamePeriod(statResults) 
   
  }

  private def makeSamePeriod(statResults: Seq[Seq[ComparedStatisticResult]]) = {
    var dates = statResults.flatten.map {
      stat => (stat.start, stat.end)
    }.toSet.toSeq.sortWith {
      (d1, d2) => d1._1.compareTo(d2._1) <= 0
    }

    statResults.map {
      statResult =>
        var fullStmts = dates.map {
          date =>
            var stmt = new ComparedStatisticResult
            stmt.start = date._1
            stmt.end = date._2
            stmt
        }
        for(fullStmt <- fullStmts){
          val res = statResult.filter{stmt => (stmt.start, stmt.end ) == (fullStmt.start ,fullStmt.end )}
          if(!res.isEmpty){
              fullStmt.overrideWith(res.head)
          }
        }
        fullStmts
    }
     
  }

  private def groupedByPeriod(stocks: Seq[Map[String, Object]], period: TimePeriod) = {
    val res = period match {
      case DayPeriod => stocks.groupBy { stock => stock("date").toString }.values.toSeq
      case WeekPeriod => stocks.groupBy { stock => stock("date").toString.substring(0, 4) + "_w_" + DateUtil.getWeekOfYear(stock("date").toString) }.values.toSeq
      case MonthPeriod => stocks.groupBy { stock => stock("date").toString.substring(0, 7) }.values.toSeq
      case YearPeriod => stocks.groupBy { stock => stock("date").toString.substring(0, 4) }.values.toSeq
    }
    res.sortBy(stocks => stocks.head("date").toString)
  }
}
 










