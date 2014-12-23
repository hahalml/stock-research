package com.rock.stock_research.dao

import com.rock.stock_research.dao.db.ConnectionManager
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.MapListHandler
import scala.collection.JavaConversions._
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global


object DbDao extends App{
  def get(table: String, fields: Set[String]):Seq[Map[String,Any]] = {
    get(table, fields)
  }

  def get(table: String, fields: Set[String], eqField: (String, Any) = null):Seq[Map[String,Object]] = {
    val start = System.currentTimeMillis()
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
    val stocks = data.toSeq.map{row => row.toMap}
    val end = System.currentTimeMillis()
    println("db query cost "+(end - start)+" ms")
    stocks
  }
  
  def get(table: String, fields: Set[String], eqFields: Seq[(String, Any)]): Seq[Map[String,Object]] = {
	  var tasks = eqFields.map{
	    eqField => future(get(table, fields, eqField))
	  }
	  Await.result(Future.sequence(tasks), Duration.Inf ).flatten
  }
  
  var data = get("stock", Set("name", "date"), Seq(("st_code","sz300101"), ("st_code","sz002708"), ("st_code","sz002708"), ("st_code","sz002708"), ("st_code","sz002708")))
  println(data)
  
// overloaded method value get with alternatives: (table: String,fields: Set[String],eqFields: Set[(String, Any)])Set[Seq[Map[String,Object]]] <and> (table: String,fields: 
// Set[String],eqField: (String, Any))Seq[scala.collection.immutable.Map[String,Object]] cannot be applied to (String, scala.collection.immutable.Set[String], 
// scala.collection.immutable.Set[(String, String)])
//  
  
  
  
  
  
  
  
}