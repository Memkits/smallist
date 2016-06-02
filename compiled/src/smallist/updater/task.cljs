
(ns smallist.updater.task
  (:require [smallist.schema :as schema]))

(defn add [store op-data op-id op-time]
  (-> store (conj (merge schema/task {:id op-id, :text op-data}))))
