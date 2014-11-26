package com.rock.stock_research.sort

import com.rock.stock_research.model.StockStatistics
import java.lang.Boolean

object StockStatisticsSorter {
	def sortByPriceIncPercent(s1:StockStatistics, s2:StockStatistics):Boolean={
		if(s1.incPercent.equals("-")){
			return false
		}
		if(s2.incPercent.equals("-")){
			return true
		}
		return s1.incPercent.replace("%", "").toDouble > s2.incPercent.replace("%", "").toDouble 
	}
}