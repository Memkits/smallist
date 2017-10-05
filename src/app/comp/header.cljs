
(ns app.comp.header
  (:require-macros [respo.macros :refer [defcomp <> div button span input]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(defn comp-header [title]
  (div
   {:style (merge ui/row {:justify-content :space-between, :padding "0 8px"})}
   (<> span title nil)
   (span
    {:inner-text "close",
     :style {:cursor :pointer},
     :on {:click (fn [e d! m!] (d! :stack/pop-page nil))}})))
