
(ns app.comp.dashboard
  (:require-macros [respo.macros :refer [defcomp <> div button span input]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(defcomp
 comp-dashboard
 ()
 (div
  {}
  (<> "Dashboard")
  (span {:inner-text "Close", :on {:click (fn [e d! m!] (d! :stack/pop-page nil))}})))
