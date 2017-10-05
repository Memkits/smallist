
(ns app.comp.stack
  (:require-macros [respo.macros :refer [defcomp <> div button span input]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(def style-page
  {:transition-duration "400ms",
   :transition-timing-function :ease-in-out,
   :position :absolute,
   :background-color :white})

(defcomp
 comp-stack
 (stack renderer)
 (div
  {}
  (->> (conj stack nil)
       (map-indexed
        (fn [idx router]
          [idx
           (if (nil? router)
             (div
              {:style (merge ui/fullscreen style-page {:left "100%", :opacity 0.7})}
              (div
               {:style {:width 300, :height 16, :background-color (hsl 0 0 90), :margin 8}})
              (div
               {:style {:width 260, :height 16, :background-color (hsl 0 0 90), :margin 8}})
              (div
               {:style {:width 280, :height 16, :background-color (hsl 0 0 90), :margin 8}}))
             (div
              {:style (merge
                       ui/fullscreen
                       style-page
                       {:left (str (* 100 (inc (- idx (count stack)))) "%"), :opacity 1})}
              (renderer (:name router) (:data router))))])))))
