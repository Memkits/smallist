
(ns smallist.updater.core
  (:require [smallist.updater.task :as task]))

(defn updater [store op op-data op-id op-time]
  (case
    op
    :add
    (task/add store op-data op-id op-time)
    :task/text
    (task/update-text store op-data op-id op-time)
    :task/toggle
    (task/toggle store op-data op-id op-time)
    :task/rm
    (task/rm-task store op-data op-id op-time)
    store))
