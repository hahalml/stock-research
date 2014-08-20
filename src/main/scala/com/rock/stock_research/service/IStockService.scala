package com.rock.stock_research.service

import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.constant.Period

trait IStockService {
  def getStocksInfo(startDate: String, endDate: String, period:Period): ArrayBuffer[ArrayBuffer[Map[String, String]]]
  def getCurrentStockInfo(stCode:String):Map[String,String]
  def getStockStatistics(limitNum:Int) :ArrayBuffer[Map[String,Any]]
}