
(ns app.comp.stack
  (:require-macros [respo.macros :refer [defcomp <> div button span input]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(def style-page
  {:transition-duration "400ms",
   :transition-timing-function :ease-in-out,
   :position :absolute})

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
             (div {:style (merge ui/fullscreen style-page {:left "100%"})})
             (div
              {:style (merge
                       ui/fullscreen
                       style-page
                       {:background-color :white, :left "0%", :opacity 1})}
              (renderer (:name router) (:data router))))])))))
