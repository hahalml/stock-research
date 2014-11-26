package com.rock.stock_research.tmp

import org.apache.lucene.store.FSDirectory
import org.apache.lucene.store.RAMDirectory
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.util.Version
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.index.IndexReader
import org.apache.lucene.document.FieldType
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import java.io.File
import org.apache.lucene.search.TermQuery
import com.alibaba.fastjson.JSONArray
import org.apache.lucene.index.Term
 
object LuceneManager {
  private val dir = new RAMDirectory()//   FSDirectory.open(new File("e:/tmp"));
  private var indexWriter: IndexWriter = null
  private var reader: IndexReader = null
  private var searcher: IndexSearcher = null
  private var oldReader:DirectoryReader = null
  this.init

  private def init {
    val analyzer = new StandardAnalyzer(Version.LUCENE_4_9);
    val cfg = new IndexWriterConfig(Version.LUCENE_4_9, analyzer);
    indexWriter = new IndexWriter(dir, cfg)

  }

  def getIndexSearch = {
      if(searcher == null){
    	  oldReader = DirectoryReader.open(dir)
          reader = DirectoryReader.open(dir)
          searcher = new IndexSearcher(reader)
      }
     // reader = DirectoryReader.openIfChanged(oldReader)
     // searcher = new IndexSearcher(reader)
      searcher
  }

  def getIndexWriter = indexWriter
  
  
  
  def main(args: Array[String]) {
     val writer = LuceneManager.getIndexWriter
    val doc = new Document()

    val nameType = new FieldType();
    nameType.setIndexed(true);
    nameType.setTokenized(true);
    nameType.setStored(true);
    val name = new Field("content", "hello world", nameType);
    doc.add(name)
    writer.addDocument(doc)
    writer.commit()
   // writer.close()
    
//    val q = "hello"
//    val searcher = LuceneManager.getIndexSearch
//    val query = new TermQuery(new Term("content",q))
//    val docs = searcher.search(query, 10)
//    val jsonArr = new JSONArray
//    println("count:"+docs.scoreDocs.size)
//    for(sd <- docs.scoreDocs){
//    	val content = searcher.doc(sd.doc).get("content") 
//    	jsonArr.add(content)
//    }
//    println("found:\n"+jsonArr.toJSONString())
  }
  
  
  
}