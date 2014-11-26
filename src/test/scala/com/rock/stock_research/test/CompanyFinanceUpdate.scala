package com.rock.stock_research.test
import com.github.tototoshi.csv._
import java.io.File
import com.rock.stock_research.entity.FinanceInfo
import java.lang.reflect.Field
import java.lang.Double
import org.apache.commons.dbutils.QueryRunner
import com.rock.stock_research.dao.db.ConnectionManager

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
 

object CompanyFinanceUpdate extends App {
 
//  println(Double.parseDouble("--"))
//  readCsv(null)
  insertFinanceInfos

  
   
  
  
  
  def insertOneStock(file:File) {
    println("parse file:"+file.getName())
    try {
    	val runner = new QueryRunner
	val conn = ConnectionManager.getConnection
	
    val reader = CSVReader.open(file)
    val data = reader.all.take(20)
    val head = data.head
    var colNum = 0
    for(cell <- head if !cell.trim().isEmpty()){
       colNum += 1
    }
    val symbol = file.getName().replace(".csv", "")
    for(j <- 1 to colNum){
       val row1 = data(0)(j) 
       val row2 = parseDouble(data(1)(j))
       val row3 = parseDouble(data(2)(j))
       val row4 = parseDouble(data(3)(j))
       val row5 = parseDouble(data(4)(j))
       
       val row6 = parseDouble(data(5)(j))
       val row7 = parseDouble(data(6)(j))
       val row8 = parseDouble(data(7)(j))
       val row9 = parseDouble(data(8)(j))
       val row10 = parseDouble(data(9)(j))
        
       val row11 = parseDouble(data(10)(j))
       val row12 = parseDouble(data(11)(j))
       val row13 = parseDouble(data(12)(j))
       val row14 = parseDouble(data(13)(j))
       val row15 = parseDouble(data(14)(j))
       
       val row16 = parseDouble(data(15)(j))
       val row17 = parseDouble(data(16)(j))
       val row18 = parseDouble(data(17)(j))
       val row19 = parseDouble(data(18)(j))
       val row20 = parseDouble(data(19)(j))
       
       runner.update(conn, """ insert into FinanceInfo(symbol,reportDate,basicEPS,napShare,mjxjl,
           poRevenue,ifmOperation,oProfit,invIncome,nnoIncome,
           totalProfit,netProfit,netProfitSubtract,jcashProfit,cashExAdd,
           totalAssets,currentAsset,totalLiabilities,currentLiabilities,equityInterest,roe) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)""".trim, 
           symbol,
           row1,row2,row3,row4,row5,
           row6,row7,row8,row9,row10,
           row11,row12,row13,row14,row15,
           row16,row17,row18,row19,row20)
    }
 
    reader.close
    conn.close
	}catch {
	  case t:Exception => t.printStackTrace()
	}
	
  }

 
  def parseDouble(str:String):Double = {
    try {
    	Double.parseDouble(str)
    }catch {
	  case t:Exception => 
	    t.printStackTrace()
	    null
	}
  }
  
  def insertFinanceInfos{
    val dir = new File("E:/program_data/workspace/github_rep/stock-research/finance-info")
    val files = Seq() ++ dir.listFiles()
    
    val futures = files.map((file:File)=> future(insertOneStock(file)))
//     Await.result(Future.sequence(futures), 100 minutes)
      Await.result(Future.sequence(futures), 111 minutes)
      println("done!!!")
    
    
    
  }
  
  
  
}