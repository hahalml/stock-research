package com.rock.stock_research.statistic

import com.rock.stock_research.entity.Stock
import com.rock.stock_research.util.DateUtil

class PeriodStatistic(stocks: Seq[Stock], period:TimePeriod) {
  private def groupByPeriod: Seq[Seq[Stock]] = {
    period match {
      case DayPeriod => stocks.groupBy{stock => stock.date }.values.toSeq
      case WeekPeriod => stocks.groupBy{stock => stock.date.substring(0,4)+"_"+DateUtil.getDateOfWeek(stock.date) }.values.toSeq
      case MonthPeriod => stocks.groupBy{stock => stock.date.substring(0,7) }.values.toSeq
      case YearPeriod => stocks.groupBy{stock => stock.date.substring(0,4) }.values.toSeq
    }
     
  }
  def buildStatistictResult = {
    val groupedStocks = groupByPeriod
    groupedStocks.map {
      stocks =>
        val result = new PeriodStatisticResult
        result.symbol = stocks.head.st_code 
        result.startDate = stocks.head.date 
        result.endDate = stocks(stocks.length - 1).date 
        val realPriceStocks = stocks.filter{stock=>stock.curr_price != 0.0}
        realPriceStocks match {
		  case value if !value.isEmpty =>   
		    result.avg = "" + (realPriceStocks.foldLeft(0.0)((price: Double, stock: Stock) => price + stock.curr_price) / stocks.length)
	        result.min = realPriceStocks.minBy((stock: Stock) => stock.curr_price).curr_price.toString
	        result.max = realPriceStocks.maxBy((stock: Stock) => stock.curr_price).curr_price.toString
	        result.totalDealNum = realPriceStocks.map{stock=>stock.deal_stock_num }.sum.toString
	        result.totalDealPrice = realPriceStocks.map{stock=>stock.deal_price }.sum.toString
		}
        result
    }
  }
}