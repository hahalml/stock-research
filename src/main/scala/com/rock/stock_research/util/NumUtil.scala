package com.rock.stock_research.util

import java.lang.Double

object NumUtil {

  def percent(n1: Number, n2: Number): String = {
    if (n1.doubleValue() == 0) {
      "-"
    } else {
      val delta = (n2.doubleValue() - n1.doubleValue()) * 100
      String.format("", (delta / n1.doubleValue) + "") + "%"
    }

  }
}