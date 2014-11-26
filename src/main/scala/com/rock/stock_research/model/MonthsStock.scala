package com.rock.stock_research.model

import com.rock.stock_research.util.DateUtil

class MonthsStock(daysStockData: Seq[Map[String, Any]]) extends PeriodsStock(daysStockData) {
   override def group = {
	  val groupedMap = daysStockData.groupBy((stock:Map[String,Any])=>{
	    val date = stock("date").toString
	    date.substring(5,7)
      })
      val dates = groupedMap.keys.toSeq.sorted
      for(i <- 0 to dates.length){
        val date = dates(i)
        val stocks = groupedMap(date)
        doStatistic(stocks, false)
      }
	}
   
    
}