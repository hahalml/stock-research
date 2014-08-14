package com.rock.stock_research.http

case class HttpResponse(status: Int, data: Array[Byte], headers:Array[org.apache.commons.httpclient.Header]) {

}