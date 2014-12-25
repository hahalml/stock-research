package com.rock.stock_research.service

import com.rock.stock_research.statistic.DayPeriod
import com.alibaba.fastjson.JSON
import com.lambdaworks.jacks.JacksMapper
import com.rock.stock_research.statistic.MonthPeriod

object StockServiceTest extends App {
  	val service = new StockService
  	val data = service getStockStat("curr_price", Seq("sz300155"), period = DayPeriod, startDate = "2014-12-03")
  	println(JacksMapper.writeValueAsString(data))

  

}