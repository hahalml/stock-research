package com.rock.stock_research.test

import com.github.tototoshi.csv.CSVReader

object CsvTest extends App{
	val csv = "E:\\program_data\\workspace\\github_rep\\stock-research\\finance-info\\000005.csv"// scala.io.Source.fromFile("E:\\program_data\\workspace\\github_rep\\stock-research\\finance-info\\000005.csv").getLines
	val reader = CSVReader.open(csv)
	val data = reader.all
    println(data)
}