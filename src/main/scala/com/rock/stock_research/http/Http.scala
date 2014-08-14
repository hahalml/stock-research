package com.rock.stock_research.http

import org.apache.commons.httpclient.methods.PostMethod
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.StringRequestEntity

object Http {
  def post(url: String, data: String, headers: Map[String, String] = Map[String, String]()) = {
    val client = new HttpClient
    val postMethod = new PostMethod(url)
    for ((k, v) <- headers) {
      postMethod.setRequestHeader(k, v)
    }
    postMethod.setRequestEntity(new StringRequestEntity(data))
    client.executeMethod(postMethod)
    val res = HttpResponse(postMethod.getStatusCode(), postMethod.getResponseBody(),postMethod.getResponseHeaders())
    postMethod.releaseConnection()
    res
  }

  def get(url: String, headers: Map[String, String] = Map[String, String]()) = {
    val client = new HttpClient
    val method = new GetMethod(url)
    headers.foreach {
      case (k, v) => method.setRequestHeader(k, v)
    }
    client.executeMethod(method)
    val res = HttpResponse(method.getStatusCode(), method.getResponseBody(),method.getResponseHeaders())
    method.releaseConnection()
    res
  }

  private def request(url: String, isPost: Boolean = false, data: String, headers: Map[String, String] = Map[String, String]()) {
    val client = new HttpClient
    val method = new PostMethod(url)
    for ((k, v) <- headers) {
      method.setRequestHeader(k, v)
    }
    client.executeMethod(method)
    if (isPost) method.setRequestEntity(new StringRequestEntity(data))

    val code = client.executeMethod(method)
    println("http status:" + code)
    println(method.getResponseBodyAsString())
    method.releaseConnection()
  }
}