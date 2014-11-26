package com.rock.stock_research.entity

import scala.beans.BeanProperty

class PortfolioItem {
  @BeanProperty var portfolioId:Int = -1
  @BeanProperty var id:Int = -1
  @BeanProperty var ticker:String = null
}