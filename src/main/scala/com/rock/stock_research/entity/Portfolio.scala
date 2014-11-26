package com.rock.stock_research.entity

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer

class Portfolio {
	 @BeanProperty var id: Int = -1
	 @BeanProperty var name:String = null
	 @BeanProperty var stocks:ArrayBuffer[Stock] = ArrayBuffer.empty[Stock]
}