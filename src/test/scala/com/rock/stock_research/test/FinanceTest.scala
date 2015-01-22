package com.rock.stock_research.test
import com.github.tototoshi.csv.CSVReader
import java.io.StringReader
import scalaj.http.HttpOptions
import scalaj.http.Http
import com.rock.stock_research.util.NumUtil
import scala.collection.mutable.ArrayBuffer
object FinanceTest extends App {

  //    val csv = "E:\\program_data\\workspace\\github_rep\\stock-research\\finance-info\\000005.csv"// scala.io.Source.fromFile("E:\\program_data\\workspace\\github_rep\\stock-research\\finance-info\\000005.csv").getLines
  //    val reader = CSVReader.open(csv)
  //    CSVReader.open(new StringReader(""))
  //    val data = reader.all
  //    println(data)
  //    val url = "http://quotes.money.163.com/service/zycwzb_601766.html?type=year"
  //    val csv =  Http(url).option(HttpOptions.connTimeout(111000)).option(HttpOptions.readTimeout(511000)).asString
  //    val csvReader  =  CSVReader.open(new StringReader(csv))
  //    val data = csvReader.all
  //    println(data)

  
  showStockFinanceIncData()
  
  def isNum(v: String) = v != "" && v != "--"

  def showBuss(data: List[List[String]], n: Int, title:String = "") {
    println(title)
    val mainBuss = data(n).tail
     println(mainBuss.mkString("\t\t")) //--
    var incArr = ArrayBuffer.empty[String]
    for (i <- 0 to mainBuss.length - 1) {
      val v = mainBuss(i).trim
      val v_n1 = if (i < mainBuss.length - 1) mainBuss(i + 1).trim else ""
      val inc = if (isNum(v) && isNum(v_n1)) NumUtil.incPercent(v_n1, v) else "-"
      incArr += inc
    }
    println(incArr.mkString("\t\t"))
    println
  }

  def showStockFinanceIncData(symbol: String = "600660") {
    val csvReader = CSVReader.open("E:/program_data/workspace/github_rep/stock-research/finance-info/year/600660.csv")
    val data = csvReader.all
    csvReader.close
    println(data(0).tail.mkString("\t"))
    println
    showBuss(data, 4, "主营收入") //主营收入
    showBuss(data, 5, "主营利润") //主营利润
    showBuss(data, 6, "营业利润") //营业利润
     showBuss(data, 12, "经营活动产生的现金流量净额") //经营活动产生的现金流量净额
  }

}