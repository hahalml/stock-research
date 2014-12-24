package com.rock.stock_research.statistic

import scala.beans.BeanProperty
import com.rock.implict.ImplictTypes._
class StatisticResult {
  @BeanProperty var current = "-"
  @BeanProperty var min = "-"
  @BeanProperty var max = "-"
  @BeanProperty var avg = "-"
  @BeanProperty var total = "-"
  @BeanProperty var start = "-"
  @BeanProperty var end = "-"
  @BeanProperty var symbol = "-"
  @BeanProperty var name = "-"
}
 
object StatisticResult{
	/**
	 * @param stocks  sorted by date
	 * @param field
	 * @return
	 */
	def apply(stocks:Seq[Map[String,Object]], field:String) = {
	    val notEmptyStocks = stocks.filter{stock => stock(field).toString.toDouble != 0.0}
		var res = new StatisticResult
		res.start = stocks.head("date").toString
		res.end = stocks.last("date").toString
		res.symbol = stocks.head("st_code").toString
		res.name = stocks.head("name").asInstanceOf[String]
		if(!notEmptyStocks.isEmpty){ 
			res.current = stocks.last(field).toString.toFix(2)
			val sortedStocks = stocks.sortBy{stock => stock(field).toString.toDouble}
			res.min = sortedStocks.head(field).toString.toFix(2)
			res.max = sortedStocks.last(field).toString.toFix(2)
			val total  = sortedStocks.foldLeft(0.0)((v:Double, stock:Map[String,Object])=> v + stock(field).toString.toDouble)
			res.total = total.toFix(2)
			res.avg = (total/sortedStocks.size).toFix(2)
		}
		res
	}
}