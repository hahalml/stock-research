package com.rock.stock_research.service

import com.rock.stock_research.dao._
import com.rock.stock_research.entity.Stock
import com.rock.stock_research.util.NumUtil
import scala.collection.mutable.ArrayBuffer

class StockService extends IStockService {
  // private val stockDao = new StockDao()
  override def getStocksPriceInfo = {
    var startDate = null
    var endDate = null
    val stockList = List.empty[Stock]

    val res = List.empty
    for (stock <- stockList) {
      val deltaPrice = stock.currPrice - stock.prevClosePrice
    }

    ""
  }

  override def getStocksInfo(startDate: String, endDate: String) = {
    var stocksInfoList = ArrayBuffer.empty[ArrayBuffer[Map[String,String]]]
    val stocks = StockDao.getStocks(startDate, endDate)
    val stockGroup = stocks.groupBy((stock: Stock) => stock.stCode)
    for (stocksMap <- stockGroup) {
      val stockDatas = stocksMap._2.sortBy((stock: Stock) => stock.date)
      val stockInfos = createStockInfos(stockDatas)
      stocksInfoList += stockInfos
    }
    stocksInfoList.sortWith((stockInfos1:ArrayBuffer[Map[String,String]], stockInfos2:ArrayBuffer[Map[String,String]])=>true)
    stocksInfoList
  }

  override def getCurrentStockInfo(stCode:String):Map[String,String]={
    Map.empty[String,String]
  }
  
  
  private def createStockInfos(stocks: ArrayBuffer[Stock]) = {

    val stockNum = stocks.length
    var stockInfos = ArrayBuffer.empty[Map[String, String]]
    val range = (0 to stockNum - 1)
    for (i <- range) {
      val stock = stocks(i)
      val incPercent = NumUtil.percent(stock.prevClosePrice, stock.currPrice)
      var delNumIncPercent = ""
      if (i != 0) {
        delNumIncPercent = NumUtil.percent(stocks(i - 1).dealStockNum, stock.dealStockNum)
      }
      val date = stock.date
      stockInfos += Map("date" -> stock.date, "price" -> stock.currPrice.toString, "priceInc" -> incPercent,
          "dealNum" -> stock.dealStockNum.toString, "dealNumInc" -> delNumIncPercent, "minPrice"->stock.currPrice.toString, "maxPrice"->stock.currPrice.toString
          ,"minDealNum"->stock.dealStockNum.toString, "maxDealNum"->stock.dealStockNum.toString)
    }

    stockInfos
  }

}