package com.rock.stock_research.test

import com.rock.stock_research.dao.db.ConnectionManager
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.MapListHandler
import scala.collection.JavaConversions._
import java.util.Map
import scalaj.http.HttpOptions
import scalaj.http.Http
import org.apache.commons.io.IOUtils
import java.io.FileOutputStream
 
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import java.net._
 

object DownloadFinanceExcel extends App {

  //http://quotes.money.163.com/service/zycwzb_600036.html?type=report
  
  
  downloadAll
  
  def downloadAll{
    val stocks = getStocks
    val futures  = stocks.map((stock: Map[String, Object]) => 
      {
        val symbol = stock("symbol").asInstanceOf[String].substring(2)
        future(downloadFinanceExcel(symbol))    
      })
      
    Await.result(Future.sequence(futures), 100 minutes)
      
    println("done!!!")
  }
  
 
  def downloadFinanceExcel(symbol:String){
	    try {
	    	 val url = s"http://quotes.money.163.com/service/zycwzb_$symbol.html?type=report"
		    println("downloading... "+url)
		    val data = Http(url).option(HttpOptions.connTimeout(111000)).option(HttpOptions.readTimeout(511000)).asBytes
		    IOUtils.write(data, new FileOutputStream(s"E:/program_data/workspace/github_rep/stock-research/finance-info/$symbol.csv"))
  
		}catch {
		  case t:Exception => t.printStackTrace()
		}
   }
  
  private def getStocks = {
    val conn = ConnectionManager.getConnection
    val runner = new QueryRunner
    var stocks = runner.query(conn, "select * from company", new MapListHandler)
    conn.close
    stocks
  }
}