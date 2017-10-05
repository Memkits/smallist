
(ns app.comp.footer
  (:require-macros [respo.macros :refer [defcomp <> div button span input]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(defcomp
 comp-footer
 ()
 (div {:style {:background-color (hsl 0 0 90), :height 48}} (<> "Add")))
