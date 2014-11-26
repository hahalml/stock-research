package com.rock.stock_research.model

import scala.beans.BeanProperty
import scala.collection.Seq

class StockGroupStatistics {
  @BeanProperty var stCode = ""
  @BeanProperty var name = ""
  @BeanProperty var statisticsDatas = Seq.empty[StockStatistics]
}