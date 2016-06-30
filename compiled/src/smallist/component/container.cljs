
(ns smallist.component.container
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div span]]
            [smallist.component.todolist :refer [comp-todolist]]))

(defn render [store]
  (fn [state mutate!] (div {} (comp-todolist store))))

(def comp-container (create-comp :container render))
