package com.rock.stock_research.service

import com.rock.stock_research.entity.Stock
import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.http.Http

class SinaStockService extends RealTimeStockService {
  val url = "http://hq.sinajs.cn/list="
  override def getStockInfo(stCodeList: Array[String]): ArrayBuffer[Stock] = {
    val stocks = stCodeList.mkString(",")
    val u = url + stocks
    val res = new String(Http.get(u).data)
    parseStock(res)
  }

  def parseStock(res: String) = {
    ArrayBuffer.empty[Stock]
  }
}