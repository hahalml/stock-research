package com.rock.stock_research.test

import com.mchange.v2.c3p0.ComboPooledDataSource

object TestStock extends App {
  println("Hello")

  val data = List(Map("id"->1223), Map("id"->456))
  println(data)
  val dd = data.sortBy((d:Map[String,Int])=>d("id"))
  println(data)
   println(dd)
}