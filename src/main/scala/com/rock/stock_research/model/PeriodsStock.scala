package com.rock.stock_research.model

abstract class PeriodsStock(daysStockData: Seq[Map[String, Any]]) {
	def group
	
	protected def doStatistic(stocks:Seq[Map[String,Any]]) = {
	  Map()
	}
}