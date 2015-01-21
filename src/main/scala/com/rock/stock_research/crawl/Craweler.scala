package com.rock.stock_research.crawl
import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.http.Http

object Craweler extends App {

  val indexURLS = Array("http://app.finance.ifeng.com/hq/list.php?type=stock_a&class=ha", "http://app.finance.ifeng.com/hq/list.php?type=stock_a&class=sa", "http://app.finance.ifeng.com/hq/list.php?type=stock_a&class=gem")

  val stockRegex = "http://finance.ifeng.com/app/hq/stock/([a-z0-9]+)/index.shtml".r

  val stocks = ArrayBuffer.empty[String]

  for (url <- indexURLS) {

    val html = new String(Http.get(url).data)

    stockRegex.findAllIn(html).matchData.foreach {

      m => stocks += m.group(1)

    }

  }

  println(stocks)

  println(stocks.length + "," + stocks.toSet.size)
  
  
  //     http://hq.sinajs.cn/list=sh600000
  
  
  testDebug
  
  def testDebug{
    var i = 0;
    var j=1;
    println(i+j)
  }
  
  
  
  
  
  
  

}