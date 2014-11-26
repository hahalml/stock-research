package com.rock.stock_research.test

import com.rock.stock_research.dao.db.ConnectionManager
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.MapListHandler
import scala.collection.JavaConversions._
import scala.collection.mutable.ArrayBuffer
import scalaj.http.Http
import org.jsoup.Jsoup
import scalaj.http.HttpOptions
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import java.net._

object GetIndustry extends App {

  fetch

  def fetch {
    val conn = ConnectionManager.getConnection
    val runner = new QueryRunner()
    val stocks = runner.query(conn, "select distinct st_code from stock", new MapListHandler())
    conn.close()
    println("stock size:" + stocks.length)

    val t1 = System.currentTimeMillis();
    var futures = stocks.map { stCode =>
      future(update(stCode("st_code").asInstanceOf[String]))
    }

    Await.result(Future.sequence(futures), 100 minutes)
    val t2 = System.currentTimeMillis();
    println("wast time: " + (t2 - t1) / 1000)
    println("done")

  }

  def update(stCode: String) {
    try {
      println("update "+stCode)
      val conn = ConnectionManager.getConnection
      val runner = new QueryRunner()
      val stocks  = runner.query(conn, "select * from stock_industry where stCode = ?", stCode, new MapListHandler())
      if(!stocks.isEmpty())
        return
      val code = if (stCode.startsWith("sh")) "0" + stCode.substring(2) else "1" + stCode.substring(2)
      val url = s"http://quotes.money.163.com/$code.html#2c01"
      val industList = parseIndustry(url)

      

      for (industry <- industList) {
        val args: Array[Object] = Array(industry, stCode)
        runner.update(conn, "insert into stock_industry(industryName, stCode) values(?,?) ", industry, stCode)
      }
      conn.close()
    } catch {
      case t: Exception => t.printStackTrace()
    }
  }

  def parseIndustry(url: String) = {
    val arr = ArrayBuffer.empty[String]
    val res = Http(url).option(HttpOptions.readTimeout(130 * 1000)).option(HttpOptions.connTimeout(60 * 1000)).asString
    val doc = Jsoup.parse(res)
    val relate_stock = doc.select("div.relate_stock").get(1)
    val liList = relate_stock.select("li")
    for (li <- liList) {
      val title = li.select("a").attr("title")
      arr += title
    }
    arr
  }
}
