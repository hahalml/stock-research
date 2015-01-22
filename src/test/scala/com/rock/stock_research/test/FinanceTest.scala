package com.rock.stock_research.test
import com.github.tototoshi.csv.CSVReader
import java.io.StringReader
import scalaj.http.HttpOptions
import scalaj.http.Http
object FinanceTest extends App{

//    val csv = "E:\\program_data\\workspace\\github_rep\\stock-research\\finance-info\\000005.csv"// scala.io.Source.fromFile("E:\\program_data\\workspace\\github_rep\\stock-research\\finance-info\\000005.csv").getLines
//    val reader = CSVReader.open(csv)
//    CSVReader.open(new StringReader(""))
//    val data = reader.all
//    println(data)
    val url = "http://quotes.money.163.com/service/zycwzb_601766.html?type=year"
    val csv =  Http(url).option(HttpOptions.connTimeout(111000)).option(HttpOptions.readTimeout(511000)).asString
    val csvReader  =  CSVReader.open(new StringReader(csv))
    val data = csvReader.all
    println(data)
}