package com.rock.stock_research.entity

import scala.xml.Null
import scala.beans.BeanProperty

class Company {
  @BeanProperty var id: Int = -1
  @BeanProperty var name: String = null
  @BeanProperty var symbol: String = null
  @BeanProperty var marketCap: Double = 0.0
  @BeanProperty var pe: Double = 0.0
  @BeanProperty var totalShare:Double = 0.0
}


object M{
  def convert(d:scala.Double):java.lang.Double = {
    d
  }
}