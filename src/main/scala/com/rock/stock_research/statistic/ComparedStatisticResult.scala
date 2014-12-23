package com.rock.stock_research.statistic

import scala.beans.BeanProperty
import com.rock.stock_research.util.NumUtil

class ComparedStatisticResult extends StatisticResult{
	@BeanProperty var incPercent = "-"
}
object ComparedStatisticResult{
  def apply(r1:StatisticResult, r2:StatisticResult) = {
    var res = new ComparedStatisticResult
    res.start = r2.start 
    res.end = r2.end
    res.current = r2.current 
    res.min = r2.min
    res.max = r2.max 
    res.avg = r2.avg
    res.total = r2.total 
    res.symbol = r2.symbol 
    res.incPercent = NumUtil.incPercent(r1.current , r2.current )
  }
}