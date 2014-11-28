package com.rock.stock_research.statistic

trait TimePeriod
case object DayPeriod extends TimePeriod
case object WeekPeriod extends TimePeriod
case object MonthPeriod extends TimePeriod
case object YearPeriod extends TimePeriod