package com.rock.stock_research.action

import org.scalatra._
import scala.xml.{ Text, Node }
import scalate.ScalateSupport
import com.rock.stock_research.service.StockService
import com.rock.stock_research.service.IStockService
import spray.json._
import DefaultJsonProtocol._
import scala.collection.mutable.ArrayBuffer

class StockAction extends ScalatraServlet with FlashMapSupport with ScalateSupport {
  //private def displayPage(title:String, content:Seq[Node]) = Template.page(title, content, url(_))
  private val stockService: IStockService = new StockService()

  get("/stocks-infos-by-day") {
    val stocksInfos = stockService.getStocksInfo("", "")
    Map("code" -> "0", "data" -> stocksInfos)
  }
  get("/stocks-infos-by-week") {

  }
  get("/stocks-infos-by-month") {

  }
  get("/stocks-infos-by-year") {
	  "stocks-infos-by-year"
  }

}