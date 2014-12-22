package com.rock.stock_research.view

import scala.beans.BeanProperty
import com.alibaba.fastjson.JSONObject
 
class Style {
  @BeanProperty var id: String = ""
  @BeanProperty var clazz: String = ""
  @BeanProperty var styles:JSONObject = new JSONObject()
  
  
}

object A extends App{
  val json = new JSONObject
  json.put("id", 123)
  println(json)
}


