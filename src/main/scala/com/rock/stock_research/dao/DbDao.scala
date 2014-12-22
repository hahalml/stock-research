package com.rock.stock_research.dao

import com.rock.stock_research.dao.db.ConnectionManager
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.MapListHandler
import scala.collection.JavaConversions._
import scala.xml.Null

object DbDao extends App{
  def get(table: String, fields: Set[String]):Seq[Map[String,Any]] = {
    get(table, fields, null)
  }

  def get(table: String, fields: Set[String], eqField: (String, Any)) = {
    val runner = new QueryRunner()
    val conn = ConnectionManager.getConnection
    var sql = fields match {
      case fields if fields == null || fields.isEmpty => s"select * from $table"
      case _ => 
        val fieldsStr = fields.mkString(",")
        s"select $fieldsStr from $table"
    }
    
    var args: Array[Object] = null
    if(eqField != null){
      sql = sql + s" where ${eqField._1} = ?"  
      args = Array(eqField._2.asInstanceOf[Object])
    }
    println("sql statement: "+sql)
    val data =  runner.query(conn, sql, args,  new MapListHandler) 
    conn.close
    data.toSeq.map{row => row.toMap}
  }
  
  
  
}