package com.rock.stock_research.service

import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.constant.Period
import com.rock.stock_research.constant.ByDay
import com.rock.stock_research.model.StockGroupStatistics
import com.rock.stock_research.model.StockStatistics
 
trait IStockService {
 
 
  def statisticOfPeriod(symbols:Seq[String], startDate:String = "1900-01-01", endDate:String = "9999-01-01", offset:Int = 0, num:Int = Int.MaxValue ):Seq[StockStatistics]
  
  
  
  
}