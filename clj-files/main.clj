(ns main
(:gen-class)
)
(require '[clj-http.client :as http])
(require '[clojure.string :as str])
(require '[clj-time.core :as t])
(require '[clj-time.format :as f])
(import 'java.util.TimerTask)
(import 'java.util.Timer)
(import 'java.util.Date)
(import 'java.util.TimeZone)
(import 'java.text.SimpleDateFormat)

(TimeZone/setDefault (TimeZone/getTimeZone "GMT+0800"))

(def stock-url-prefix "http://hq.sinajs.cn/list=")

(def stocks-code-urls ["http://app.finance.ifeng.com/hq/list.php?type=stock_a&class=ha"
               "http://app.finance.ifeng.com/hq/list.php?type=stock_a&class=sa"
               "http://app.finance.ifeng.com/hq/list.php?type=stock_a&class=gem"])

(def root-dir "/home/rock/stocks")
(def date-formatter (f/formatter "yyyy-MM-dd"))

(defn get-content
  ([url] (-> url http/get :body))
  ([url encoding] (println url) (-> url (http/get {:as :byte-array :socket-timeout 20000 :conn-timeout 20000 }) :body (String. encoding))))

(defn get-gbk-content [url]
  (get-content url "gbk"))

(defn create-daily-file [] (let [file-name (str root-dir "/" (f/unparse date-formatter (t/now)) ".txt")] (spit file-name "") file-name ))


(defn crawl-stocks [] (let [daily-file (create-daily-file) stocks-code
      (for [stock-code-url stocks-code-urls]
        (map second (re-seq #"http://finance.ifeng.com/app/hq/stock/([a-z0-9]+)/index.shtml"
                (get-content stock-code-url "utf-8"))))]
    (doseq [stock-group (partition-all 100 (apply concat stocks-code))]
      (spit daily-file (get-gbk-content (str "http://hq.sinajs.cn/list=" (str/join "," stock-group))) :append true ))) (println "today's stocks crawl done!"))


(def timer (proxy [TimerTask] [] (run [] (try (crawl-stocks) (catch Exception e (println e))   ))))

(defn run-schedule [] (-> (java.util.Timer.) (.schedule timer (Date. 114 7 12 21 0 0 ) (* 24 3600 1000))))

(defn -main [& args] (println "crawl start...") (run-schedule))
;;(defn -main [& args](println "start...") (spit "/home/rock/stocks/kk.txt" "" )(spit "/home/rock/stocks/kk.txt" (get-gbk-content "http://hq.sinajs.cn/list=sh600000") :append true))

