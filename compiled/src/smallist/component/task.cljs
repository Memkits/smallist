
(ns smallist.component.task
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div input button]]))

(defn render [task] (fn [state mutate] (div {})))

(def comp-task (create-comp :task render))
