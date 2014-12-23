package com.rock.stock_research.test
import sys.process._
import scala.actors.threadpool.AtomicInteger
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object EsTest extends App {
  //    val setting = """{
  //    "settings" : {
  //        "number_of_shards" : 3,
  //        "number_of_replicas" : 2
  //    }
  //}"""
  //	"curl -I http://www.baidu.com" !

 
  val lastInteger = new AtomicInteger(0)
  def futureInt() = future {
    Thread sleep 2000
    123
  }

 

  // use for construct to extract values when futures complete
  val b1 = futureInt
  val b2 = futureInt
//  val i: Future[Seq[Int]] = for (i1 <- b1; i2 <- b2) yield Seq(i1,i2)
//  i.onSuccess{
//    case v => println(v)
//  }
//  Thread sleep 3000

   val re =  Await.result(Future.sequence(Seq(b1,b2,b1,b1,b2)), Duration.Inf )
   println(re)
   
   Thread sleep 3000
}