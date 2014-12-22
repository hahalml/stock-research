package com.rock.implict

import java.text.DecimalFormat
import java.lang.Double

object ImplictTypes extends App{
  class ScaleNum(value: AnyVal) {
    def this(value: String) {
      this(Double.parseDouble(value))
    }
    def toFix(scale: Int = 2) = {
      val str = value.toString
      val df = scale match {
        case 0 => new DecimalFormat("#")
        case i if i > 0 => new DecimalFormat("#." + ("0" * scale))
        case _ => throw new IllegalArgumentException("scale can't be negative")
      }
      df.format(value)
    }

  }
  implicit def toScaleNum(value: AnyVal) = new ScaleNum(value)
  implicit def toScaleNum(value: String) = new ScaleNum(value)
  println(3.toFix(3))
  println(3.126.toFix(2))
  println("3.126".toFix(2))
}