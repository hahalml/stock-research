package com.rock.stock_research.model

import scala.beans.BeanProperty

class StockStatistics {
  @BeanProperty var stCode: String = null
  @BeanProperty var name: String = null
  @BeanProperty var statisticsName: String = ""
  @BeanProperty var startDate: String = null
  @BeanProperty var endDate: String = null

  @BeanProperty var min: Double = Double.NaN
  @BeanProperty var max: Double = Double.NaN
  @BeanProperty var avg: Double = Double.NaN
  @BeanProperty var curr: Double = Double.NaN

  @BeanProperty var totalDealNum: Int = Int.MinValue
  @BeanProperty var totalDealPrice: Double = Double.NaN
  @BeanProperty var incPercent: String = null
  @BeanProperty var lastIncPercent: String = null
}

