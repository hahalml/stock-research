package com.rock.stock_research.view

import scala.beans.BeanProperty

class GridView {
  @BeanProperty var headData: Seq[String] = Seq.empty
  @BeanProperty var bodyData: Seq[Seq[String]] = Seq.empty
  @BeanProperty var styles: Seq[Style] = Seq.empty//for cell
}