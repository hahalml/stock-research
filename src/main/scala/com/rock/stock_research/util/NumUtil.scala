package com.rock.stock_research.util

import java.lang.Double
import java.math.BigDecimal

object NumUtil extends App {

  def incPercent(n1: Any, n2: Any): String = {
    if (n1.toString.toDouble.equals(0.0)||n2.toString.toDouble.equals(0.0)) {
      "-"
    } else {
      var delta = (n2.toString.toDouble - n1.toString.toDouble) / n1.toString.toDouble
      delta = delta * 100
      getScale(delta, 2) + "%"
    }

  }
  def percent(num: Number) = {
    getScale(num.toString.toDouble * 100, 2) + "%"
  }
  def getScale(num: Double, scale: Int = 2) = {
    val b = new BigDecimal(num)
    b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }
}