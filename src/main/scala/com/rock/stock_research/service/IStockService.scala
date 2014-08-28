package com.rock.stock_research.service

import scala.collection.mutable.ArrayBuffer
import com.rock.stock_research.constant.Period

trait IStockService {
  def getStocksInfo(startDate: String, endDate: String, period:Period):Seq[Map[String,Any]]
  def getCurrentStockInfo(stCode:String):Map[String,String]
  def getStockStatistics(daysAgo:Int, limitNum:Int) :ArrayBuffer[Map[String,Any]]
  def getStocksInfosByDay(startDate: String, endDate: String, stCodes:Seq[String] = Seq.empty[String]):Seq[Map[String, Any]]
}