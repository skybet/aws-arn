(defproject Mcblumpreson/aws-arn (or (System/getenv "PROJECT_VERSION") "0.0.0-SNAPSHOT")
  :description "Parse useful information from AWS ARNs"
  :url "http://github.com/Mcblumperson/aws-arn"
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
  :plugins [[lein-license "0.1.6"]
            [lein-ancient "0.6.10"]
            [lein-kibit "0.1.2" :exclusions [org.clojure/clojure
                                             org.clojure/tools.cli]]
            [jonase/eastwood "0.2.3"]
            [lein-bikeshed "0.3.0"]
            [lein-cloverage "1.0.6"]
            [lein-codox "0.9.6"]]
  :target-path "target/%s"
  :aliases {"qa" ["do"
                  ["clean"]
                  ["check"]
                  ["eastwood"]
                  ["bikeshed" "-m" "120"]
                  ["ancient"]
                  ["cloverage"]]}
  :profiles {:dev {}
             :provided {:dependencies [[org.clojure/clojure "1.8.0"]]}})
