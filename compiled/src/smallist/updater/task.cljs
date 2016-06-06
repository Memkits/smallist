
(ns smallist.updater.task
  (:require [smallist.schema :as schema]))

(defn add [store op-data op-id op-time]
  (-> store
   (assoc op-id (merge schema/task {:id op-id, :text op-data}))))

(defn update-text [db op-data op-id op-time]
  (let [[task-id text] op-data]
    (-> db (assoc-in [task-id :text] text))))

(defn toggle [db op-data op-id op-time]
  (-> db (update-in [op-data :done?] not)))
