package com.rock.stock_research.dao

case class QueryOption( startDate:String,  endDate:String,  limit:Int)
//object QueryOption{
////  def apply(startDate:String, endDate:String, limit:Int, stCodes:Seq[String]) = new  QueryOption(startDate, endDate, limit, stCodes)
//  def apply(startDate:String, endDate:String, limit:Int) = new  QueryOption(startDate, endDate, limit, null)
//}