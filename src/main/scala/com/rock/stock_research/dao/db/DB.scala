package com.rock.stock_research.dao

import sorm.Instance
import sorm.InitMode
import sorm.Entity
import com.rock.stock_research.entity.Stock
  
object DB extends Instance(
  entities = Set()    + Entity[Stock](),
  url = "jdbc:mysql://localhost:3306/my_stock?useUnicode=true&characterEncoding=GBK",
  user = "root",
  password = "123",
  initMode = InitMode.Create,
  poolSize = 12   
)