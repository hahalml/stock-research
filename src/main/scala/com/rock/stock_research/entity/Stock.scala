package com.rock.stock_research.entity

import sorm.sql.Sql.Table

//import sorm.ddl.Table
abstract class ST

case class Stock(stCode:String, name: String, openPrice: Double, prevClosePrice: Double, currPrice: Double,  dealStockNum: Long, dealPrice: Double,  date: String, time: String, originalStr:String) extends ST

case class StockIndex(stCode:String, name: String, open: Double, prevClose: Double, curr: Double,  dealStockNum: Long, dealPrice: Double,  date: String, time: String, originalStr:String) extends ST
