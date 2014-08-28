package com.rock.stock_research.action

import org.scalatra._
import scala.xml.{ Text, Node }
import scalate.ScalateSupport
import com.rock.stock_research.service.StockService
import com.rock.stock_research.service.IStockService
import scala.collection.mutable.ArrayBuffer
import org.json4s.JsonDSL._
import com.lambdaworks.jacks.JacksMapper
import com.rock.stock_research.constant._
import com.rock.stock_research.dao.StockDao
import com.rock.stock_research.dao.DB
import com.rock.stock_research.entity.Stock
import com.rock.stock_research.util.DateUtil
class StockAction extends ScalatraServlet with FlashMapSupport with ScalateSupport {
  //private def displayPage(title:String, content:Seq[Node]) = Template.page(title, content, url(_))
  private val stockService: IStockService = new StockService()

  get("/stocks-infos-by-day") {
    try {
      val stocksInfos = stockService.getStocksInfosByDay(DateUtil.daysAgo(14), null)
      JacksMapper.writeValueAsString(stocksInfos)
    } catch {
      case t: Throwable => t.printStackTrace(); t
    }

  }
  get("/stocks-infos-by-week") {
    val stocksInfos = stockService.getStocksInfo("", "", ByWeek)
  }
  get("/stocks-infos-by-month") {
    val stocksInfos = stockService.getStocksInfo(null, null, ByMonth)
    JacksMapper.writeValueAsString(stocksInfos)
  }
  get("/stocks-infos-by-year") {
    // var json = Map("code" -> "0", "data" -> List(Map("name" -> "rock"), Map("name" -> "rainbow")), "time"->"12:13")
    var json = stockService.getStocksInfo(null, null, ByYear)
    JacksMapper.writeValueAsString(json)

  }
  //https://github.com/wg/jacks
  get("get-current-stock/:stCode") {
    val stCode = params("stCode")
    stockService.getCurrentStockInfo(stCode)
  }

  get("/get-stock-statistics-info") {
    //    val daysAgo = params("days") match {
    //	  case null => 365
    //	  case d => d.toInt
    //	}
    //    val limit = params("stockNum") match {
    //	  case null => 100
    //	  case d => d.toInt
    //	}
    try {
      val body = stockService.getStockStatistics(11, 111)
      val head = Array("股票代码", "股票名称", "当前价", "累计涨幅", "最高", "最低", "平均", "总成交量", "成交金额", "开始日期", "结束日期", "涨幅(昨日)")
      JacksMapper.writeValueAsString(Map("head" -> head, "body" -> body))
    } catch {
      case t: Throwable => t.printStackTrace(); t
    }
  }

  get("/test") {
    //JacksMapper.writeValueAsString(stockService.getStockStatistics(11))
  }

}