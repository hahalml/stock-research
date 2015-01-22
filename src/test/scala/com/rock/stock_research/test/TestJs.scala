package com.rock.stock_research.test

import javax.script.ScriptEngineManager
import javax.script.Invocable

object TestJs extends App{
	val mgr = new ScriptEngineManager();
  val engine = mgr.getEngineByExtension("js");
  val script = data.script // "function add(a,b){return a+b;}; add(1,2);";
  println(engine.eval(script));
//  val inv = engine.asInstanceOf[Invocable];
//  val res = inv.invokeFunction("add", 11.asInstanceOf[Object], 12.asInstanceOf[Object]);
//  println("res:" + res);
}
object data{
  val script = """
    
    var parent =  {
	bdcallback: function(data){
		return JSON.stringify(data);
	}
};
try {
    parent.bdcallback({
        "000001.sh": {
            na: "上证指数",
            pc: "3173.052",
            op: "3189.085",
            vo: "410956024",
            tu: "47375866",
            hi: "3337.004",
            lo: "3178.343",
            la: "3323.611",
            type: "1",
            time: "2015-01-21 15:01:19"
        },
        "399001.sz": {
            na: "深证成指",
            pc: "10996.482",
            op: "11051.948",
            vo: "33807874",
            tu: "5144170",
            hi: "11395.100",
            lo: "11000.741",
            la: "11372.180",
            type: "1",
            time: "2015-01-21 15:00:41"
        },
        "399300.sz": {
            na: "沪深300 ",
            pc: "3396.222",
            op: "3420.491",
            vo: "337928210",
            tu: "42543370",
            hi: "3557.794",
            lo: "3409.430",
            la: "3548.885",
            type: "1",
            time: "2015-01-21 15:04:08"
        },
        "601186.sh": {
            na: "中国铁建",
            pc: "13.69",
            op: "13.96",
            vo: "2880376",
            tu: "400968",
            hi: "14.20",
            lo: "13.64",
            la: "14.14",
            type: "2",
            time: "2015-01-21 15:00:19",
            sy: "16.10",
            lt: "102.61",
            sz: "1744.53",
            hs: "2.81",
            is: "0"
        },
        "tofnow": {
            time: "2015-01-21 21:14:48"
        }
    })
} catch (e) {}
    
    """.trim
}