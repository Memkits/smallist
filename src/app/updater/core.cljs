
(ns app.updater.core (:require [app.schema :as schema]))

(defn updater [store op op-data op-id]
  (case op
    :stack/push-page (update store :stack (fn [stack] (conj stack op-data)))
    :stack/pop-page (update store :stack pop)
    :task/create
      (assoc-in store [:tasks op-id] (merge schema/task {:id op-id, :text op-data}))
    (do (println "Unknown op:" op) store)))
