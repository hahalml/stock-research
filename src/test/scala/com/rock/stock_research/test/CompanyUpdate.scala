package com.rock.stock_research.test

import com.rock.stock_research.dao.db.ConnectionManager
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.MapListHandler
import scala.collection.JavaConversions._
import scalaj.http.Http
import org.jsoup.Jsoup
import scalaj.http.HttpOptions
import java.util.Map
import scala.collection.mutable.ArrayBuffer
import com.alibaba.fastjson.JSON
import java.lang.Double

object CompanyUpdate extends App {
  val d: Double = 1.1
  val c: Double = 1.1

  val conn = ConnectionManager.getConnection
  val runner = new QueryRunner
  val aa: Array[Object] = Array(null, null, null, "sh600000")

  println("len:" + aa.length)

  //runner.update(conn, "update company set marketCap = ?, pe = ?, totalShare = ? where symbol = ?", d,d,d,"sh600000")  
  conn.close

  updateAll

  
  
  
  //公司财务信息
  //http://quotes.money.163.com/0603019.html#2a02 http://quotes.money.163.com/f10/zycwzb_600036.html#01c02
  def updateAll {
    val conn = ConnectionManager.getConnection
    val runner = new QueryRunner
    val stocks = getStocks
    conn.close
    val symbols = stocks.map((stock: Map[String, Object]) => stock("symbol").asInstanceOf[String])
    parseStock(symbols)
  }

  def parseStock(symbols: Seq[String]) {

    var symbolBuffer = symbols.toBuffer
    var i = 0
    while (!symbolBuffer.isEmpty) {
      try {
        println(i)
        i += 1

        val sbs = symbolBuffer.take(1)
        symbolBuffer.trimStart(Math.min(1, sbs.size))
        val params = sbs.map((symbol: String) => if (symbol.startsWith("sh")) symbol.substring(2) + ".sh" else symbol.substring(2) + ".sz")
        val url = "http://bdcjhq.hexun.com/quote?s2=" + params.mkString(",")
        val html = Http(url).option(HttpOptions.readTimeout(111111)).option(HttpOptions.connTimeout(111111)).asString
        val doc = Jsoup.parse(html)
        var script = doc.select("script:eq(0)").html()
        script = script.replace("try{parent.bdcallback(", "").replace(")}catch(e){}", "")
        println(script)
        val obj = JSON.parseObject(script)
        for (code <- sbs) {
          val c = if (code.startsWith("sh")) code.substring(2) + ".sh" else code.substring(2) + ".sz"
          println("c:" + c)
          val info = obj.getJSONObject(c)
          val totalShare = info.getDouble("lt")
          val pe = info.getDouble("sy")
          val marketCap = info.getDouble("sz")
          updateStock(code, totalShare, pe, marketCap)
        }
      } catch {
        case t: Exception => t.printStackTrace()
      }

    }

  }

  private def getStocks = {
    val conn = ConnectionManager.getConnection
    val runner = new QueryRunner
    var stocks = runner.query(conn, "select * from company where pe is null", new MapListHandler)
    conn.close
    stocks
  }

  def updateStock(symbol: String, totalShare: Double, pe: Double, marketCap: Double) {
    val conn = ConnectionManager.getConnection
    val runner = new QueryRunner
    val args: Array[Object] = Array(marketCap, pe, totalShare, symbol)
    runner.update(conn, "update company set marketCap = ?, pe = ?, totalShare = ? where symbol = ?", marketCap, pe, totalShare, symbol)
    conn.close
  }
}