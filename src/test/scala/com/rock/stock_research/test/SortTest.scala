package com.rock.stock_research.test

import scala.collection.mutable.ArrayBuffer

object SortTest extends App{
	var arr = ArrayBuffer("1","2%","-", "-1%")
	arr = arr.sortWith((s1: String, s2: String) => {
      if (s1.equals("-")) {
        false
      } else if (s2.equals("-")) {
        true
      } else s1.replace("%", "").toDouble > s2.replace("%", "").toDouble
    }).take(100)
	println(arr)
}