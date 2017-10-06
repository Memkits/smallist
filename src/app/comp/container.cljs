
(ns app.comp.container
  (:require-macros [respo.macros :refer [defcomp <> cursor-> div button span input code]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [respo.comp.inspect :refer [comp-inspect]]
            [app.comp.stack :refer [comp-stack]]
            [app.comp.dashboard :refer [comp-dashboard]]
            [app.comp.missing :refer [comp-missing]]
            [app.comp.create :refer [comp-create]]))

(def style-container
  {:overflow :hidden,
   :width "100%",
   :height "100%",
   :position :absolute,
   :background-color (hsl 0 0 90),
   :font-size 16})

(defcomp
 comp-container
 (store)
 (let [states (:states store)]
   (div
    {:style (merge ui/global ui/column style-container)}
    (comp-stack
     (:stack store)
     (fn [page page-data]
       (case page
         :dashboard (comp-dashboard (:tasks store))
         :create (cursor-> :create comp-create states page-data)
         (comp-missing page page-data))))
    (div
     {}
     (span
      {:inner-text "Launch Dashboard",
       :on {:click (fn [e d! m!] (d! :stack/push-page {:name :dashboard}))}}))
    (<> (:stack store)))))
