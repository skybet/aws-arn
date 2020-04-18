(ns aws-arn.core
  "ARN handling support"
  (:require [clojure.string :as string]
            [clojure.string :as str]))

(defn- resource-parts->type-and-resource [resource-parts]
  (cond
    (string/includes? resource-parts "/")
    (let [[type resource] (str/split resource-parts #"/" 2)]
      {:resource-type type :resource resource})

    (string/includes? resource-parts ":")
    (let [[type resource] (str/split resource-parts #":" 2)]
      {:resource-type type :resource resource})

    :default
    {:resource-type nil :resource resource-parts}))

(defn parse-arn
  "Extract a map of partition, service, region, account-id, resource
  and resource-type from a complete AWS ARN. Partial ARNs will be
  parsed as far as possible. Partial parses with return a map with all
  keys but nil values for missing information. The nil ARN will return
  nil."
  [arn]
  (when arn
    (let [[_ partition service region account-id & resource-parts] 
          (map
            #(if (= % "")
               nil
               %)
            (str/split arn #":"))]
      (merge
       {:partition partition
        :service service
        :region region
        :account-id account-id
        :resource-type nil
        :resource nil}
       (when resource-parts
         (resource-parts->type-and-resource (str/join \: resource-parts)))))))



