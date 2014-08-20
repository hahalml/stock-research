package com.rock.stock_research.dao.db

import com.mchange.v2.c3p0.ComboPooledDataSource
import java.sql.Connection

object ConnectionManager {

  private var dataSource:ComboPooledDataSource = null
  initDataSource
  
  private def initDataSource {
    dataSource = new ComboPooledDataSource()
    dataSource.setUser("root");
    dataSource.setPassword("123");
    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/my_stock?useUnicode=true&characterEncoding=GBK")

    dataSource.setDriverClass("com.mysql.jdbc.Driver");
    dataSource.setInitialPoolSize(2);
    dataSource.setMinPoolSize(1);
    dataSource.setMaxPoolSize(10);
    dataSource.setMaxStatements(50);
    dataSource.setMaxIdleTime(60);
  }
  def getConnection = {
	dataSource.getConnection()  
  }
  
  def closeConnection(conn:Connection){
    conn.close()
  }
}