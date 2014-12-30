package com.rock.stock_research.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar

object DateUtil {
  private def sdf = new SimpleDateFormat("yyyy-MM-dd")

  def parse(date: String) = {
    sdf.parse(date)
  }

  def parse(date: String, format: String) = {
    var sdf = this.sdf
    if (format != null)
      sdf = new SimpleDateFormat(format)
    sdf.parse(date)
  }

  def getWeekOfYear(date: Date):Int = {
    val calendar = Calendar.getInstance()
    calendar.setTime(date)
    calendar.get(Calendar.WEEK_OF_YEAR)
  }
  def getWeekOfYear(dateStr: String):Int = {
    getWeekOfYear(sdf.parse(dateStr))
    
  }
  def getDateOfMonth(date: Date) = {
    val calendar = Calendar.getInstance()
    calendar.setTime(date)
    calendar.get(Calendar.DAY_OF_MONTH)
  }
  def getDateOfYear(date:Date) = {
    val calendar = Calendar.getInstance()
    calendar.setTime(date)
    calendar.get(Calendar.DAY_OF_MONTH)
  }
  def daysAgo(days: Int) = {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, days * (-1))
    sdf.format(calendar.getTime())
  }
}