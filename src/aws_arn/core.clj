(ns aws-arn.core
  (:require [clojure.string :as string]))

(defn- resource-parts->type-and-resource [resource-or-type nil-or-resource]
  (if (nil? nil-or-resource)
    (let [[type resource] (string/split resource-or-type #"/")]
      (if resource
        {:resource-type type :resource resource}
        {:resource-type nil :resource resource-or-type}))
    {:resource-type resource-or-type :resource nil-or-resource}))

(defn parse-arn
  "Extract a map of partition, service, region, account-id, resource
  and resource-type from a complete AWS ARN. Partial ARNs will be
  parsed as far as possible. Partial parses with return a map with all
  keys but nil values for missing information. The nil ARN will return
  nil."
  [arn]
  (when arn
    (let [[_ partition service region account-id resource-first resource-last] (map
                                                                                #(if (= % "")
                                                                                   nil
                                                                                   %)
                                                                                (string/split arn #":"))]
      (merge
       {:partition partition
        :service service
        :region region
        :account-id account-id
        :resource-type nil
        :resource nil}
       (when resource-first
         (resource-parts->type-and-resource resource-first resource-last))))))
