package com.rock.stock_research.statistic

import scala.beans.BeanProperty
import com.rock.stock_research.util.NumUtil

class ComparedStatisticResult extends StatisticResult{
	@BeanProperty var incPercent = "-"
	
    def overrideWith(stmt:ComparedStatisticResult){ 
        this.name = stmt.name
        this.start = stmt.start 
        this.end = stmt.end
        this.current = stmt.current 
        this.min = stmt.min
        this.max = stmt.max 
        this.avg = stmt.avg
        this.total = stmt.total 
        this.symbol = stmt.symbol 
        this.incPercent = stmt.incPercent 
	}
}
object ComparedStatisticResult{
  def apply(r1:StatisticResult, r2:StatisticResult) = {
    var res = new ComparedStatisticResult
    res.name = r2.name
    res.start = r2.start 
    res.end = r2.end
    res.current = r2.current 
    res.min = r2.min
    res.max = r2.max 
    res.avg = r2.avg
    res.total = r2.total 
    res.symbol = r2.symbol 
    res.incPercent = if(r1.current == "-" || r2.current == "-") "-" else NumUtil.incPercent(r1.current , r2.current )
    res
  }
  
}