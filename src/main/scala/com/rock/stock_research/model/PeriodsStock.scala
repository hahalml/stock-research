package com.rock.stock_research.model

import com.rock.stock_research.util.NumUtil

abstract class PeriodsStock(daysStockData: Seq[Map[String, Any]]) {
	def group
	
	protected def doStatistic(stocks:Seq[Map[String,Any]], isDay:Boolean) = {
	  val stNum = stocks.length
	  isDay match {
	    case true => val stock = stocks(0)
	      val incPercent = NumUtil.incPercent(stock("prev_close_price"), stock("curr_price"))
	      Map("curr" -> stock("curr_price"), "min" -> "-", "max" -> "-", "incPercent" -> incPercent, "dateRange" -> stock("date"), "currDealNum" -> stock("deal_stock_num"), "incDealNum" -> "", "currDealPrice" -> stock("deal_price"),"incDealPrice"->"")
	    case _  => 
	      val curr = stocks(stNum - 1)("curr_price")
	      val min = stocks.minBy((stock:Map[String,Any])=>stock("curr_price").toString.toDouble)
	      val max = stocks.maxBy((stock:Map[String,Any])=>stock("curr_price").toString.toDouble)
	      val dateRange = stocks(0)("date")+"-"+stocks(stNum - 1)("date")
	      val currDealNum = stocks.foldLeft(0)((s1:Int,st:Map[String,Any])=>s1 + st("deal_stock_num").toString.toInt)
	      val currDealPrice = stocks.foldLeft(0.0)((s1:Double,st:Map[String,Any])=>s1 + st("deal_stock_num").toString.toDouble)
  	      Map("curr" -> curr, "min" -> "-", "max" -> "-", "incPercent" -> "", "dateRange" -> dateRange, "currDealNum" -> currDealNum, "incDealNum" -> "", "currDealPrice" -> currDealPrice,"incDealPrice"->"")

	  }
	}
}