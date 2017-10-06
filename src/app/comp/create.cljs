
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
    (div
     {:style ui/row}
     (textarea
      {:placeholder "Task",
       :value state,
       :class-name "el-create-box",
       :style (merge
               ui/flex
               {:width :auto, :min-height 200, :padding "8px", :margin "8px 8px"}),
       :on {:input (fn [e d! m!] (m! (:value e)))}}))
    (div
     {:style (merge ui/row-center {:justify-content :flex-end, :padding "0 8px"})}
     (span
      {:inner-text "Create",
       :style {:cursor :pointer},
       :on {:click (fn [e d! m!] (d! :task/create state) (m! nil) (d! :stack/pop-page nil))}})))))
