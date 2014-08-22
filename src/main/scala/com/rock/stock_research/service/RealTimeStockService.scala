package com.rock.stock_research.service

import com.rock.stock_research.entity.Stock
import com.rock.stock_research.entity.Stock
import scala.collection.mutable.ArrayBuffer

trait RealTimeStockService {
  def getStockInfo(stCodeList: Array[String]): ArrayBuffer[Stock]
}