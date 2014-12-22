package com.rock.stock_research.query

case class StockQueryOption(symbols: Seq[String], startDate: String = "1900-01-01", endDate: String = "9999-01-01", offset: Int = 0, num: Int = Int.MaxValue) {

}