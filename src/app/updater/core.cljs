
(ns app.updater.core (:require [app.schema :as schema] [respo.cursor :refer [mutate]]))

(defn updater [store op op-data op-id]
  (case op
    :states (update store :states (mutate op-data))
    :stack/push-page (update store :stack (fn [stack] (conj stack op-data)))
    :stack/pop-page (update store :stack pop)
    :task/create
      (assoc-in store [:tasks op-id] (merge schema/task {:id op-id, :text op-data}))
    :task/toggle (update-in store [:tasks op-data :done?] not)
    :task/edit (assoc-in store [:tasks (:id op-data) :text] (:text op-data))
    (do (println "Unknown op:" op) store)))
