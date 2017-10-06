
(ns app.comp.task-editor
  (:require-macros [respo.macros :refer [defcomp <> div button span input textarea]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.header :refer [comp-header]]))

(defcomp
 comp-task-editor
 (states task)
 (let [state (or (:data states) (:text task))]
   (div
    {}
    (comp-header "Edit task")
    (div
     {:style (merge ui/row)}
     (textarea
      {:class-name "el-editor-box",
       :value state,
       :placeholder "Task",
       :style (merge ui/flex {:width "100%", :margin 8, :padding 8, :min-height 200}),
       :on {:input (fn [e d! m!] (m! (:value e)))}}))
    (div
     {:style (merge ui/row {:justify-content :flex-end, :padding "0 8px"})}
     (span
      {:inner-text "Edit",
       :style {:cursor :pointer},
       :on {:click (fn [e d! m!]
              (d! :task/edit {:id (:id task), :text state})
              (m! nil)
              (d! :stack/pop-page nil))}})))))
