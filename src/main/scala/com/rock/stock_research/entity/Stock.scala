package com.rock.stock_research.entity
import sorm.sql.Sql.Table
import scala.xml.Null
import scala.beans.BeanProperty

class Stock() {
  @BeanProperty var id: Int = -1
  @BeanProperty var name: String = null
  @BeanProperty var open_price: Double = -1
  @BeanProperty var curr_price: Double = -1
  @BeanProperty var date: String = null
  @BeanProperty var st_code: String = null
  @BeanProperty var time: String = null
  @BeanProperty var deal_stock_num: Int = -1
  @BeanProperty var deal_price: Double = -1
  @BeanProperty var prev_close_price: Double = -1

}