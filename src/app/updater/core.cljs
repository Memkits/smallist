
(ns app.updater.core )

(defn updater [store op op-data]
  (case op
    :stack/add-page (update store :stack (fn [stack] (conj stack op-data)))
    :stack/pop-page (update store :stack pop)
    (do (println "Unknown op:" op) store)))
