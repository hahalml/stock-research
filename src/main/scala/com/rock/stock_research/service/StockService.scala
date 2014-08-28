package com.rock.stock_research.service

import com.rock.stock_research.dao._
import com.rock.stock_research.entity.Stock
import com.rock.stock_research.util.NumUtil
import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.constant._
import java.util.Date
import scala.xml.Null
import com.rock.stock_research.util.DateUtil
import com.rock.stock_research.entity.Stock
import com.rock.stock_research.entity.Stock
import java.util.Calendar
import scala.collection.JavaConversions._
import java.lang.Double
import com.rock.stock_research.statistics.StockStatistics
class StockService extends IStockService {

  override def getStocksInfo(startDate: String, endDate: String, period: Period) = {
    List(Map("min" -> "", "max" -> "", "endPrice" -> "", "avg" -> "", "totalDealNum" -> "", "totalDealPrice" -> "",
      "startDate" -> "", "endDate" -> "", "incPricePercent" -> "", "incDealNumPercent" -> ""))
    null
  }

  override def getCurrentStockInfo(stCode: String): Map[String, String] = {
    Map.empty[String, String]
  }

  private def createStockInfos(stocks: Seq[Stock], period: Period) = {
    val groupedPeriods = period match {
      case ByDay => stocks.groupBy((stock: Stock) => stock.date)
      case ByWeek => stocks.groupBy((stock: Stock) => stock.date.substring(0, 4) + " " + DateUtil.getDateOfWeek(DateUtil.parse(stock.date)))
      case ByMonth => stocks.groupBy((stock: Stock) => stock.date.substring(0, 4))
      case ByYear => stocks.groupBy((stock: Stock) => stock.date.substring(0, 4))
    }

    for ((gName, periodStocks) <- groupedPeriods) {
      println("gname:" + gName)
    }

    val stockNum = stocks.length
    var stockInfos = ArrayBuffer.empty[Map[String, String]]
    val range = (0 to stockNum - 1)
    for (i <- range) {
      val stock = stocks(i)
      val incPercent = NumUtil.incPercent(stock.prevClosePrice, stock.currPrice)
      var delNumIncPercent = ""
      if (i != 0) {
        delNumIncPercent = NumUtil.incPercent(stocks(i - 1).dealStockNum, stock.dealStockNum)
      }
      val date = stock.date
      stockInfos += Map("sector" -> stock.stCode, "date" -> stock.date, "price" -> stock.currPrice.toString, "priceInc" -> incPercent,
        "dealNum" -> stock.dealStockNum.toString, "dealNumInc" -> delNumIncPercent, "minPrice" -> stock.currPrice.toString, "maxPrice" -> stock.currPrice.toString, "minDealNum" -> stock.dealStockNum.toString, "maxDealNum" -> stock.dealStockNum.toString)
    }

    stockInfos
  }

  def getStockStatistics(daysAgo: Int, stockNum: Int): ArrayBuffer[Map[String, Any]] = {
    val stocks = StockDao.getStocks(QueryOption(DateUtil.getDaysAgo(daysAgo), null, Int.MaxValue)).filterNot((stock: Map[String, Any]) => stock("curr_price").toString.toDouble == 0.0)
    val stList = stocks.groupBy((stock: Map[String, Any]) => stock("st_code").toString)
    val statDatas = ArrayBuffer.empty[Map[String, Any]]
    for ((stCode, stockDatas) <- stList) {
      var sts = stockDatas.groupBy((stock: Map[String, Any]) => stock("date").toString).values.map((stocks: ArrayBuffer[Map[String, Any]]) => stocks(0))
      val sortedStocks = sts.toList.sortBy((stock: Map[String, Any]) => stock("date").toString)
      val stockNum = sortedStocks.length

      val incPercent = NumUtil.incPercent(sortedStocks(0)("curr_price"), sortedStocks(stockNum - 1)("curr_price"))
      var min: Double = null
      var max: Double = null
      var avg = 0.0
      var totalDealNum = 0
      var totalDealPrice = 0.0
      val currPrice = sortedStocks(stockNum - 1)("curr_price")
      var totalPrice = 0.0

      var totalInc = 0
      var totalDec = 0
      var totalNotChanged = 0
      for (stock <- sortedStocks) {
        val currPrice = stock("curr_price").toString.toDouble
        val prevClose = stock("prev_closed").toString.toDouble

        currPrice - prevClose match {
          case value if (value == 0) => totalNotChanged = totalNotChanged + 1
          case value if (value > 0) => totalInc = totalInc + 1
          case value if (value < 0) => totalDec = totalDec + 1
        }

        totalDealNum = totalDealNum + stock("deal_stock_num").toString.toInt
        totalDealPrice = totalDealPrice + stock("deal_price").toString.toDouble
        totalPrice = totalPrice + currPrice
        if (min == null) {
          min = currPrice
        }
        if (max == null) {
          max = currPrice
        }
        if (currPrice != 0 && currPrice <= min) {
          min = currPrice
        }
        if (currPrice != 0 && currPrice >= max) {
          max = currPrice
        }
      }
      avg = totalPrice / stockNum
      statDatas += Map("stCode" -> getStockField(sortedStocks, 0, "st_code"), "name" -> getStockField(sortedStocks, 0, "name"), "min" -> min,
        "max" -> max, "avg" -> avg, "currPrice" -> currPrice, "incPercent" -> NumUtil.incPercent(sortedStocks(0)("curr_price"), sortedStocks(stockNum - 1)("curr_price")),
        "totalDealNum" -> totalDealNum, "totalDealPrice" -> totalDealPrice,
        "startDate" -> sortedStocks(0)("date"), "endDate" -> sortedStocks(stockNum - 1)("date"),
        "preIncPercent" -> NumUtil.incPercent(getStockField(sortedStocks, stockNum - 1, "prev_close_price"), getStockField(sortedStocks, stockNum - 1, "curr_price")),
        "totalInc" -> NumUtil.percent(totalInc / stockNum), "totalDec" -> NumUtil.percent(totalDec / stockNum), "totalNotChanged" -> NumUtil.percent(totalNotChanged / stockNum))
    }

    statDatas.sortWith((st1: Map[String, Any], st2: Map[String, Any]) => st1("incPercent").toString.replace("%", "").toDouble > st2("incPercent").toString.replace("%", "").toDouble).take(stockNum)

  }

  private def getStockField(stocks: List[Map[String, Any]], index: Int, field: String) = {
    stocks(index)(field)
  }

  private def doStatistics(stocks: Seq[Map[String, Any]]): StockStatistics = {
    if (stocks == null || stocks.isEmpty)
      return null
    val st = new StockStatistics
    var totalPrice = 0.0
    for (stock <- stocks) {
      st.totalDealNum = st.totalDealNum + stock("").toString.toInt
      st.totalDealPrice = st.totalDealPrice + stock("").toString.toDouble
      totalPrice = totalPrice + stock("").toString.toDouble
    }
    st.avg = totalPrice / stocks.length
    val minStock = stocks.minBy((stock: Map[String, Any]) => stock("").toString.toDouble)
    st.min = minStock("").toString.toDouble
    val maxStock = stocks.minBy((stock: Map[String, Any]) => stock("").toString.toDouble)
    st.max = maxStock("").toString.toDouble
    st.curr = stocks.last("").toString.toDouble
    st
  }

  private def doDailyStatistics(stocks: Seq[Map[String, Any]]) = {
    var stData = Seq.empty[Map[String, Any]]
    for (stock <- stocks) {
      val priceIncPercent = NumUtil.incPercent(stock(""), stock(""))
      val dealNumIncPercent = NumUtil.incPercent(stock(""), stock(""))
      val dealPriceIncPercent = NumUtil.incPercent(stock(""), stock(""))
      val price = stock("")
      val dealNum = stock("")
      stData = stData :+ Map("price" -> price, "" -> dealNum, "" -> priceIncPercent, "" -> dealNumIncPercent, "" -> dealPriceIncPercent)
    }
    stData
  }

}