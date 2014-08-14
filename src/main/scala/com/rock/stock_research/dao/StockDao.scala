package com.rock.stock_research.dao

import com.rock.stock_research.entity.Stock
import scala.collection.mutable.ArrayBuffer

object StockDao{
  def getStock(stCode:String, startDate:String, endDate:String) = {
    
  }
  
  def getStocks(startDate:String, endDate:String) : ArrayBuffer[Stock] = {
    ArrayBuffer.empty
  }
}