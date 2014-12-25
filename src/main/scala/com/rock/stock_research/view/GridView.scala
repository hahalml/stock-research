package com.rock.stock_research.view

import scala.beans.BeanProperty

class GridView {
  @BeanProperty var headData: Seq[String] = Seq.empty
  @BeanProperty var bodyData: Seq[Seq[String]] = Seq.empty
  
  def this(headData:Seq[String], bodyData:Seq[Seq[String]]){
    this()
    this.headData = headData
    this.bodyData = bodyData
  }
}
object GridView{
  def apply() = new GridView
  def apply(headData:Seq[String], bodyData:Seq[Seq[String]]) = new GridView(headData, bodyData)
}