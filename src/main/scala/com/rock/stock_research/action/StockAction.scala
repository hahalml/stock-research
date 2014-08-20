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
class StockAction extends ScalatraServlet with FlashMapSupport with ScalateSupport {
  //private def displayPage(title:String, content:Seq[Node]) = Template.page(title, content, url(_))
  private val stockService: IStockService = new StockService()

  get("/stocks-infos-by-day") {
   // val stocksInfos = stockService.getStocksInfo("", "", ByDay)
    Map("code" -> "0", "data" -> StockDao.getStocks(null, null))
  }
  get("/stocks-infos-by-week") {
	  val stocksInfos = stockService.getStocksInfo("", "", ByWeek)
  }
  get("/stocks-infos-by-month") {
	  val stocksInfos = stockService.getStocksInfo(null,null, ByMonth)
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
  
  get("/get-stock-statistics-info"){
   // val daysAgo = params("days").toInt
    val t1 = System.currentTimeMillis()
     val js = DB.fetchWithSql[Stock]("select id from stock").map((st:Stock)=>Map("code"->st.stCode, "price"->st.currPrice))
     val t2 = System.currentTimeMillis()
     println("t2-t1:"+(t2-t1))
     JacksMapper.writeValueAsString(js)
     // JacksMapper.writeValueAsString(DB.query[Stock].whereEqual("stCode","sz300100").limit(5).fetch.map((st:Stock)=>Map("code"->st.stCode, "price"->st.currPrice)))
  }
  
  
  
   get("/test"){
     JacksMapper.writeValueAsString(stockService.getStockStatistics(11))
  }
  
  
}