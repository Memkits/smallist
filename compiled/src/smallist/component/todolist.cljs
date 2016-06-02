
(ns smallist.component.todolist
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div input button]]
            [smallist.component.task :refer [comp-task]]))

(defn update-state [state text] text)

(defn init-state [tasks] "")

(def style-todolist
 {:width "100%",
  :display "flex",
  :position "absolute",
  :flex-direction "column",
  :height "100%"})

(def style-header
 {:align-items "center", :flex-drection "row", :display "flex"})

(def style-input
 {:line-height 2,
  :vertical-align "middle",
  :font-size "20px",
  :background-color (hsl 0 0 94),
  :flex 1,
  :padding "0 8px",
  :outline "none",
  :display "inline-block",
  :border "none"})

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

(def style-list {:background-color (hsl 0 0 96), :flex 1})

(defn handle-input [mutate] (fn [e dispatch] (mutate (:value e))))

(defn handle-add [state mutate]
  (fn [e dispatch]
    (let [text state]
      (if (> (count text) 0) (do (dispatch :add text) (mutate ""))))))

(defn render [tasks]
  (fn [state mutate]
    (div
      {:style style-todolist}
      (div
        {:style style-header}
        (input
          {:style style-input,
           :event {:input (handle-input mutate)},
           :attrs {:value state}})
        (button
          {:style style-button,
           :event {:click (handle-add state mutate)},
           :attrs {:inner-text "Add"}}))
      (div
        {:style style-list}
        (->>
          tasks
          (map (fn [task] [(:id task) (comp-task (val task))]))
          (into {}))))))

(def comp-todolist
 (create-comp :todolist init-state update-state render))
