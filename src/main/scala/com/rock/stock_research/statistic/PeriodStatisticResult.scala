package com.rock.stock_research.statistic

import scala.beans.BeanProperty

class PeriodStatisticResult {

  @BeanProperty var symbol = ""
  @BeanProperty var min = "-"
  @BeanProperty var max = "-"
  @BeanProperty var avg = "-"
  @BeanProperty var curr = "-"
  
  @BeanProperty var totalDealNum = "-"
  @BeanProperty var totalDealPrice = "-"

  
  @BeanProperty var startDate = "-"
  @BeanProperty var endDate = "-"
    

}