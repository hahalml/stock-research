package com.rock.stock_research.service

import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.constant.Period
import com.rock.stock_research.constant.ByDay
import com.rock.stock_research.model.StockGroupStatistics
import com.rock.stock_research.model.StockStatistics
import com.rock.stock_research.types.AliasType._ 
trait IStockService {
 

  def getStock(table: String, fields: Set[String], eqField: (String, Any) = null, startDate:String = "1900-01-01", endDate:String = "9999-01-01"):Seq[Map[String,Object]]
  
  def getStockStat(field:String, symbols:Seq[String], startDate:String = "1900-01-01", endDate:String = "9999-01-01"):MatrixData
}