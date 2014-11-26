package com.rock.stock_research.statistic

import com.rock.stock_research.entity.Stock

abstract class PeriodStatistic(stocks: Seq[Stock]) {
  protected def groupByPeriod: Seq[Seq[Stock]]
  def statistictResult = {
    val groupedStocks = groupByPeriod
    val re =  groupedStocks.map {
      stocks =>
        val result = new PeriodStatisticResult
        result.startDate = stocks.head.date 
        result.endDate = stocks(stocks.length - 1).date 
        val realPriceStocks = stocks.filter{stock=>stock.curr_price != 0.0}
        realPriceStocks match {
		  case value if value.isEmpty =>   
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