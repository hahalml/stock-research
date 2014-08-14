package com.rock.stock_research.service

import scala.collection.mutable.ArrayBuffer

trait IStockService {
  def getStocksPriceInfo: String
  def getStocksInfo(startDate: String, endDate: String): ArrayBuffer[ArrayBuffer[Map[String, String]]]
  def getCurrentStockInfo(stCode:String):Map[String,String]
}