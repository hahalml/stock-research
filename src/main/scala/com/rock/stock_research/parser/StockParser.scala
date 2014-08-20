package com.rock.stock_research.parser

import com.rock.stock_research.entity.Stock
import com.rock.stock_research.entity.StockIndex
import com.rock.stock_research.entity.ST
 
class StockParser {
  val indexes = Set("sh000001","sz399001","sz399006","sz159902")
  
  def parse(exp: String): List[ST] = {
    println("original response:" + exp)
    var list = List.empty[ST]
    val stList = exp.split(";\\s*")
    for (st <- stList) {
      System.out.println("parse: " + st);
      var stParts = st.split("\\s*=\\s*")
      try {
        var stCode = stParts(0).replace("var", "").replace("hq_str_", "").trim()
        var stValue = stParts(1).replace(";", "").replace("\"", "")
        if (stValue.contains(",")) {
          var stFields = stValue.split(",")
          list = parseByCode(stCode, stValue,st) :: list
        }
      } catch {
        case t: Throwable => t.printStackTrace()
      }
    }
    return list
  }

  def isIndex(stockCode:String) = {
    indexes.contains(stockCode) 
  }
  
  def parseByCode(stCode: String, stValue: String, stExpress:String): ST = {
    val fields = stValue.split(",")
    System.out.println("stValue:" + stValue)
    if (fields.length < 32) { 
      return null
    }
    var st: ST = null
    stCode match {
      case stockCode:String if isIndex(stockCode) => st = StockIndex(stCode, fields(0), fields(1).toDouble, fields(2).toDouble, fields(3).toDouble, fields(8).toLong, fields(9).toDouble, fields(30), fields(31),stExpress)
      case _ => st = Stock(stCode, fields(0), fields(1).toDouble, fields(2).toDouble, fields(3).toDouble, fields(10).toLong, fields(11).toDouble, fields(30), fields(31),stExpress)
    }
    st
  }

}