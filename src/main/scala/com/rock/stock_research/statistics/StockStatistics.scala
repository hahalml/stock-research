package com.rock.stock_research.statistics

case class StockStatistics() {
  var stCode: String = null
  var name: String = null
  var startDate: String = null
  var endDate: String = null
  var min: Double = Double.NaN
  var max: Double = Double.NaN
  var avg: Double = Double.NaN
  var curr: Double = Double.NaN
  var totalDealNum: Int = Int.MinValue
  var totalDealPrice: Double = Double.NaN
  var incPercent: String = null
  var lastIncPercent: String = null

  def toMap = {
    Map("stCode" -> stCode, "name" -> name, "startDate" -> startDate, "endDate" -> endDate, "min" -> min, "max" -> max, "avg" -> avg, "curr" -> curr,
      "totalDealNum" -> totalDealNum, "totalDealPrice" -> totalDealPrice, "incPercent" -> incPercent, "lastIncPercent" -> lastIncPercent)
  }
}

