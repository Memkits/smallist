
(ns smallist.component.space
  (:require [respo.alias :refer [create-comp div]]))

(defn style-space [w h]
  (if (some? w)
    {:width w, :display "inline-block", :height 1}
    {:width 1, :display "inline-block", :height w}))

(defn render [w h] (fn [state mutate] (div {:style (style-space w h)})))

(def comp-space (create-comp :space render))
