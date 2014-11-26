package com.rock.stock_research.action

import org.scalatra.FlashMapSupport
import org.scalatra.scalate.ScalateSupport
import org.scalatra.ScalatraServlet
import com.alibaba.fastjson.JSON
import org.apache.lucene.document.Document
import com.rock.stock_research.tmp.LuceneManager
import org.apache.lucene.document.Field
import org.apache.lucene.document.FieldType
import org.apache.lucene.search.TermQuery
import org.apache.lucene.index.Term
import com.alibaba.fastjson.JSONArray
import org.apache.commons.logging.LogFactory

class LuceneAction extends ScalatraServlet with FlashMapSupport with ScalateSupport {
  val log = LogFactory.getLog(classOf[LuceneAction])
  get("/search") {
    val q = request.getParameter("q")
    val searcher = LuceneManager.getIndexSearch
    val query = new TermQuery(new Term("content",q))
    val docs = searcher.search(query, 10)
    val jsonArr = new JSONArray
    for(sd <- docs.scoreDocs){
    	val content = searcher.doc(sd.doc).get("content") 
    	jsonArr.add(content)
    }
    jsonArr
    
  }
  post("/index") {
    val data = request.getParameter("data")
    val json = JSON.parseObject(data)
    val field = json.getString("field");
    val content = json.getString("content");
    val writer = LuceneManager.getIndexWriter
    val doc = new Document()

    val nameType = new FieldType();
    nameType.setIndexed(true);
    nameType.setTokenized(true);
    nameType.setStored(true);
    val name = new Field("content", content, nameType);
    doc.add(name)
    writer.addDocument(doc)
    writer.commit()
   // writer.close()
    "ok"
  }
  error {
  case e: Throwable => {
    e.printStackTrace()
    log.error(e)
  }
  e.toString()
}
  def main(args: Array[String]) {
    val str = """ {"id":123, "name":"john"}  """.trim()
    val json = JSON.parseObject(str)
    println(json.getInteger("id"))
    println(json.getString("name"))
  }
}