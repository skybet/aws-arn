(defproject aws-arn (or (System/getenv "PROJECT_VERSION") "0.0.0-SNAPSHOT")
  :description "Parse useful information from AWS ARNs"
  :url "http://github.com/skybet/aws-arn"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"
            :year 2017
            :key "mit"}
  :repositories [["snapshots" {:url "https://clojars.org/repo"
                               :username :env/clojars_username
                               :password :env/clojars_password}]
                 ["releases" {:url "https://clojars.org/repo"
                              :username :env/clojars_username
                              :password :env/clojars_password
                              :sign-releases false}]]
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :plugins [[lein-license "0.1.6"]]
  :target-path "target/%s"
  :profiles {:dev {}})
