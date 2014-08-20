package com.rock.stock_research.dao

import com.rock.stock_research.constant.Period

case class StockQueryOption(start: Int, offset: Int, sortBy: String, period:Period) 
 