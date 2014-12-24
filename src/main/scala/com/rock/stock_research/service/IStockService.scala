package com.rock.stock_research.service

import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.constant.Period
import com.rock.stock_research.constant.ByDay
import com.rock.stock_research.model.StockGroupStatistics
import com.rock.stock_research.model.StockStatistics
import com.rock.stock_research.types.AliasType._
import com.rock.stock_research.statistic.StatisticResult
import com.rock.stock_research.statistic.ComparedStatisticResult
import com.rock.stock_research.statistic.TimePeriod
import com.rock.stock_research.statistic.DayPeriod
trait IStockService {
 

  
  def getStockStat(field:String, symbols:Seq[String], startDate:String = "1900-01-01", endDate:String = "9999-01-01", period:TimePeriod = DayPeriod):Seq[Seq[ComparedStatisticResult]]
}