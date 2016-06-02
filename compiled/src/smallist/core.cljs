
(ns smallist.core
  (:require [respo-spa.core :refer [render]]
            [smallist.component.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]
            [smallist.updater.core :refer [updater]]))

(defonce store-ref (atom {}))

(defonce states-ref (atom {}))

(defn dispatch [op op-data]
  (let [op-time (.valueOf (js/Date.))
        op-id op-time
        store (updater @store-ref op op-data op-id op-time)]
    (reset! store-ref store)))

(defn render-app []
  (let [target (.querySelector js/document "#app")]
    (render (comp-container @store-ref) target dispatch states-ref)))

(defn -main []
  (enable-console-print!)
  (render-app)
  (add-watch store-ref :changes render-app)
  (add-watch states-ref :changes render-app)
  (println "app started!")
  (let [configEl (.querySelector js/document "#config")
        config (read-string (.-innerHTML configEl))]
    (if (and (some? navigator.serviceWorker) (:build? config))
      (-> navigator.serviceWorker
       (.register "./sw.js")
       (.then
         (fn [registration]
           (println "resigtered:" registration.scope)))
       (.catch (fn [error] (println "failed:" error)))))))

(set! js/window.onload -main)

(defn on-jsload [] (render-app) (println "code updated."))
