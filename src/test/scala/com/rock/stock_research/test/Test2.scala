package com.rock.stock_research.test

import scalaj.http.Http
import scalaj.http.HttpOptions
import org.apache.commons.io.IOUtils
import java.io.FileOutputStream
import com.rock.stock_research.dao.DbDao

object Test2 extends App {
//	val lines = io.Source.fromFile("E:/program_data/workspace/github_rep/stock-research/urls.txt").getLines
//	var i = 0
//	println("total "+i)
//	for(line <- lines ){
//	   if(line.startsWith("http://")){
//         val bytes = Http(line).option(HttpOptions.readTimeout(111111)).option(HttpOptions.connTimeout(111111)).asBytes
//         val html = new String(bytes, "utf-8")
//         i += 1	
//         IOUtils.write(html, new FileOutputStream(s"d:/tmp/$i.txt"))
//         println(i)
//	   }
//	}
	
//	var firstName = None:Option[String]
////	firstName = Some("rock")
//	firstName = Option("rock")
//	println(firstName .getOrElse())
  
  for(i <- 1 to 10){
    val stocks =  DbDao.get("stock", null, ("st_code", "sz300096"))
  }
  
//  println("stock size "+stocks.size)
  
}