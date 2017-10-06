
(ns app.comp.dashboard
  (:require-macros [respo.macros :refer [defcomp <> div button span input]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(defcomp
 comp-dashboard
 (tasks)
 (div
  {}
  (div
   {:style (merge
            ui/row-center
            {:padding "0 8px",
             :justify-content :space-between,
             :background-color (hsl 200 80 80),
             :color :white,
             :line-height "40px"})}
   (<> "Dashboard")
   (span
    {:inner-text "Add",
     :style {:cursor :pointer},
     :on {:click (fn [e d! m!]
            (d! :stack/push-page {:name :create})
            (js/setTimeout
             (fn [timestamp] (.focus (.querySelector js/document ".el-create-box")))
             300))}}))
  (div
   {}
   (->> tasks
        (map val)
        (filter (fn [task] (not (:done? task))))
        (map
         (fn [task]
           [(:id task)
            (div
             {:style (merge
                      ui/row
                      {:padding "0 8px",
                       :border-bottom (str "1px solid " (hsl 0 0 90)),
                       :line-height "40px",
                       :align-items :center}),
              :draggable true,
              :on {:dragstart (fn [e d! m!]
                     (println "start")
                     (let [event (:original-event e)]
                       (.setData event.dataTransfer "Text" "id"))),
                   :drop (fn [e d! m!] )}}
             (span
              {:class-name "ion-android-done",
               :style {:cursor :pointer, :color (hsl 0 0 80), :font-size 18},
               :on {:click (fn [e d! m!] (d! :task/toggle (:id task)))}})
             (=< 16 nil)
             (span {:inner-text (:text task), :cursor :pointer}))]))))))
