package com.rock.stock_research.test
import scalaj.http.Http
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.rock.stock_research.dao.db.ConnectionManager
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.MapListHandler
import scala.collection.JavaConversions._
object StockIndustry extends App {
//  val res = Http("http://quotes.money.163.com/0601390.html#2c01").param("q", "monkeys")
//  println(res.responseCode)
//  // println(res.asString)
//  val doc: Document = Jsoup.parse(res.asString)
//  val relate_stock = doc.select("div.relate_stock").get(1)
//  println(relate_stock.html())
//  val industry = relate_stock.select("li:eq(0) a").attr("title")
//  val zone = relate_stock.select("li:eq(1) a").attr("title")
//  println(industry)
//  println(zone)
  
  
  update;
  def update {
    val conn = ConnectionManager.getConnection
    val runner = new QueryRunner
    val list = runner.query(conn, "select id,st_code from stock group by st_code", new MapListHandler())
    var i = 0;
    for (st <- list) {
      try {
        i = i + 1
        println("fetch " + i)
        val st_code = st.get("st_code").toString.substring(2)
        val res = Http("http://quotes.money.163.com/" + st_code + ".html#2c01").param("q", "monkeys")
        val doc: Document = Jsoup.parse(res.asString)
        val relate_stock = doc.select("div.relate_stock").get(1)

        val industry = relate_stock.select("li:eq(0) a").attr("title")
        val zone = relate_stock.select("li:eq(1) a").attr("title")
        val args: Array[Object] = Array(zone, industry, st.get("st_code"))
        runner.update(conn, "insert into tmp_table(zone, industry, ticker) values(?,?,?)", args)
      } catch {
        case t: Exception => t.printStackTrace()
      }

    }
    conn.close()
    println("done")
  }

}