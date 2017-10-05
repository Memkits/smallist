
(ns app.comp.missing
  (:require-macros [respo.macros :refer [defcomp <> div button span input]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(defcomp
 comp-missing
 (page page-data)
 (div {} (<> (str "Page missing: " page " " page-data))))
