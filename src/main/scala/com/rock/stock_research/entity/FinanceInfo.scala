package com.rock.stock_research.entity

import java.lang.Double
import scala.beans.BeanProperty
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class FinanceInfo {
  @BeanProperty var symbol: String = null
  @BeanProperty var reportDate: String = null
  @BeanProperty var basicEPS: Double = null //basic EPS ( earnings per share )
  @BeanProperty var napShare: Double = null //net asset value per share 
  @BeanProperty var mjxjl: Double = null
  @BeanProperty var poRevenue: Double = null //prime operating revenue 
  @BeanProperty var ifmOperation: Double = null //Income from main operation
  @BeanProperty var oProfit: Double = null //operating profit
  @BeanProperty var invIncome: Double = null //Investment Income
  @BeanProperty var nnoIncome: Double = null //Net Non-Operating Income 
  @BeanProperty var totalProfit: Double = null
  @BeanProperty var netProfit: Double = null
  @BeanProperty var netProfitSubtract: Double = null
  @BeanProperty var jcashProfit: Double = null
  @BeanProperty var cashExAdd: Double = null
  @BeanProperty var totalAssets: Double = null
  @BeanProperty var currentAsset: Double = null
  @BeanProperty var totalLiabilities: Double = null
  @BeanProperty var currentLiabilities: Double = null
  @BeanProperty var equityInterest: Double = null
  @BeanProperty var roe: Double = null // return on equity
}