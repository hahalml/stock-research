//import org.scalatra.example._
import org.scalatra._
import javax.servlet.ServletContext
import com.rock.stock_research.action.StockAction
import com.rock.stock_research.action.LuceneAction

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
//    context.mount(new CookiesExample, "/cookies-example")
//    context.mount(new FileUploadExample, "/upload")
//    context.mount(new FilterExample, "/")
//    context.mount(new HttpExample, "/*")
    context.mount(new StockAction, "/")
    context.mount(new LuceneAction, "/lucene")
  }
}
