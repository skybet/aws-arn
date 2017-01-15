(ns aws-arn.core-test
  (:require [aws-arn.core :as arn]
            [clojure.test :refer :all]))

;; see http://docs.aws.amazon.com/general/latest/gr/aws-arns-and-namespaces.html

(deftest short-arn-format-test
  (is (= {:partition "partition"
          :service "service"
          :region "region"
          :account-id "account-id"
          :resource-type nil
          :resource "resource"}
         (arn/parse-arn "arn:partition:service:region:account-id:resource"))))

(deftest resourcetype-slash-resource-arn-format-test
  (is (= {:partition "partition"
          :service "service"
          :region "region"
          :account-id "account-id"
          :resource-type "resourcetype"
          :resource "resource"}
         (arn/parse-arn "arn:partition:service:region:account-id:resourcetype/resource"))))

(deftest resourcetype-colon-resource-arn-format-test
  (is (= {:partition "partition"
          :service "service"
          :region "region"
          :account-id "account-id"
          :resource-type "resourcetype"
          :resource "resource"}
         (arn/parse-arn "arn:partition:service:region:account-id:resourcetype:resource"))))

(deftest prefix-arn-returns-parts
  (is (= {:partition "partition",
          :service "service",
          :region "region",
          :account-id nil,
          :resource-type nil,
          :resource nil}
         (arn/parse-arn "arn:partition:service:region"))))

(deftest missing-region-is-nilled
  (is (= {:partition "partition",
          :service "service",
          :region nil,
          :account-id "account-id",
          :resource-type nil,
          :resource nil}
         (arn/parse-arn "arn:partition:service::account-id"))))

(deftest s3-arn-parsing
  (is (= {:partition "aws",
          :service "s3",
          :region nil,
          :account-id nil,
          :resource-type "my_corporate_bucket",
          :resource "exampleobject.png"}
         (arn/parse-arn "arn:aws:s3:::my_corporate_bucket/exampleobject.png"))))

(deftest elasticbeanstalk-arn-parsing
  (is (= {:partition "aws",
          :service "elasticbeanstalk",
          :region "us-east-1",
          :account-id "123456789012",
          :resource-type "environment",
          :resource "My App/MyEnvironment"}
         (arn/parse-arn "arn:aws:elasticbeanstalk:us-east-1:123456789012:environment/My App/MyEnvironment"))))

(deftest nil-arn-returns-nil
  (is (nil? (arn/parse-arn nil))))
