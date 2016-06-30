
(ns smallist.component.todolist
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div input button]]
            [smallist.component.task :refer [comp-task]]
            [respo.component.debug :refer [comp-debug]]))

(defn update-state [state text] text)

(defn init-state [tasks] "")

(def style-todolist
 {:width "100%",
  :flex-shrink 0,
  :display "flex",
  :position "absolute",
  :flex-direction "column",
  :height "100%"})

(def style-header
 {:align-items "center",
  :display "flex",
  :flex-direction "row",
  :height "40px"})

(def style-input
 {:line-height 2,
  :vertical-align "middle",
  :font-size "20px",
  :background-color (hsl 0 0 94),
  :flex 1,
  :padding "0 8px",
  :outline "none",
  :display "inline-block",
  :border "none",
  :font-family "Roboto,Helvetica,sans-serif"})

(def style-button
 {:line-height "40px",
  :color (hsl 0 0 100),
  :font-size "14px",
  :background-color (hsl 20 20 20),
  :padding "0 16px",
  :outline "none",
  :display "inline-block",
  :border "none",
  :height "40px",
  :margin "0px"})

(def style-list
 {:overflow "auto",
  :background-color (hsl 0 0 96),
  :flex 1,
  :padding-top "16px",
  :padding-bottom "16px"})

(defn handle-input [e dispatch! mutate!] (mutate! (:value e)))

(defn handle-add [state]
  (fn [e dispatch! mutate!]
    (let [text state]
      (if (> (count text) 0) (do (dispatch! :add text) (mutate! ""))))))

(defn render [tasks]
  (fn [state mutate!]
    (div
      {:style style-todolist}
      (div
        {:style style-header}
        (input
          {:style style-input,
           :event {:input handle-input},
           :attrs {:placeholder "Type idea here...", :value state}})
        (button
          {:style style-button,
           :event {:click (handle-add state)},
           :attrs {:inner-text "Add"}}))
      (div
        {:style style-list}
        (div
          {}
          (->>
            tasks
            (sort
              (fn [entry-a entry-b]
                (- (:id (val entry-b)) (:id (val entry-a)))))
            (filter (fn [entry] (not (:done? (val entry)))))
            (map-indexed
              (fn [index entry] [index (comp-task (val entry))]))))
        (div
          {}
          (->>
            tasks
            (sort
              (fn [entry-a entry-b]
                (- (:id (val entry-b)) (:id (val entry-a)))))
            (filter (fn [entry] (:done? (val entry))))
            (map-indexed
              (fn [index entry] [index (comp-task (val entry))])))))
      (comment comp-debug tasks {}))))

(def comp-todolist
 (create-comp :todolist init-state update-state render))
