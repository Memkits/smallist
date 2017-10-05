
(ns app.comp.create
  (:require-macros [respo.macros :refer [defcomp <> div button span input textarea]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.header :refer [comp-header]]))

(defcomp
 comp-create
 (states category)
 (let [state (or (:data states) "")]
   (div
    {}
    (comp-header "Create task")
    (textarea
     {:placeholder "Task",
      :value state,
      :style {:width "100%", :min-height 200},
      :on {:input (fn [e d! m!] (m! (:value e)))}})
    (div
     {:style (merge ui/row-center {:justify-content :flex-end, :padding "0 8px"})}
     (span
      {:inner-text "Create",
       :style {:cursor :pointer},
       :on {:click (fn [e d! m!] (d! :task/create state) (m! nil) (d! :stack/pop-page nil))}})))))
