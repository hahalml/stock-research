package com.rock.stock_research.dao.jdbc

import java.sql.Connection
import java.sql.DriverManager

object DBUtil {
   
	init
	private var conn:Connection = null
   
    private def init() = {
	  Class.forName("com.mysql")
	  conn = DriverManager.getConnection("")
	}
  
	def getConnection = {
	  conn
	}
}