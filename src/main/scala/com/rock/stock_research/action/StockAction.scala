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
import com.rock.stock_research.entity.Stock
import com.rock.stock_research.util.DateUtil
import com.fasterxml.jackson.databind.ObjectMapper
import com.alibaba.fastjson.JSON
import com.rock.stock_research.statistic.DayPeriod
import com.rock.implict.ImplictTypes._
import scalaz.Split
import com.rock.stock_research.statistic.ComparedStatisticResult

class StockAction extends ScalatraServlet with FlashMapSupport with ScalateSupport {

  private val stockService: IStockService = new StockService()
//
//  get("/stocks-infos-days-ago") {
//    try {
//      val stocks = stockService.getStatisticsAtPeriod(null)
//
//      JacksMapper.writeValueAsString(stocks)
//    }catch {
//	  case t:Exception => t.printStackTrace()
//	}
//
//  }
//
//  get("/stocks-infos-summary-by-week") {
//    val stocksInfos = stockService.getStatisticsByPeriods(makeStockCodes(request.getParameter("stCodes")), ByWeek)
//    JacksMapper.writeValueAsString(stocksInfos.take(5))
//  }
//  get("/stocks-infos-by-month") {
//    val stCodes = if (request.getParameter("stCodes") == null) "" else request.getParameter("stCodes").trim()
//    val stocksInfos = stockService.getStatisticsByPeriods(stCodes.split(""), ByMonth, 15, 0, 10)
//    JacksMapper.writeValueAsString(stocksInfos)
//  }
//  get("/stocks-infos-by-year") {
//    val stCodes = if (request.getParameter("stCodes") == null) "" else request.getParameter("stCodes").trim()
//    val stocksInfos = stockService.getStatisticsByPeriods(stCodes.split(""), ByYear, 15, 0, 10)
//    JacksMapper.writeValueAsString(stocksInfos)
//  }
//
//  private def makeStockCodes(stCodes: String): Seq[String] = {
//    if (stCodes == null || stCodes.trim().isEmpty()) {
//      return null
//    }
//    stCodes.split(",").filter((stCode: String) => stCode != null && !stCode.trim().isEmpty())
//
//  }
  
  get("/stock-field-stat") {
    val symbols = new SplitabledString(request.getParameter("symbols")).toSet(",")
    val field = request.getParameter("field")
    val stocksInfos = stockService getStockStat(field = field, symbols = Seq.empty[String], period = DayPeriod)
    val grid = Map("head" -> createGridHead(stocksInfos), "body" -> stocksInfos)
    JacksMapper.writeValueAsString(grid)
     
  }
  
  def createGridHead(stocksInfos:Seq[Seq[ComparedStatisticResult]]) = {
    stocksInfos.head.map{stat => stat.start +" ~ "+stat.end }
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}