
(ns smallist.component.task
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div input button]]
            [respo.component.debug :refer [comp-debug]]))

(def style-task {:display "flex", :flex-direction "row"})

(defn style-toggle [done?]
  {:background-color (if done? (hsl 0 0 80) (hsl 0 80 50)),
   :width "40px",
   :height "40px"})

(def style-input
 {:line-height 2,
  :font-size "18px",
  :flex 1,
  :padding "0 8px",
  :outline "none",
  :border "none"})

(def style-button
 {:color (hsl 0 0 100),
  :font-size "18px",
  :background-color (hsl 200 80 60),
  :padding "0 8px",
  :border "none"})

(defn handle-input [task-id]
  (fn [e dispatch] (dispatch :task/text [task-id (:value e)])))

(defn handle-toggle [task-id]
  (fn [e dispatch] (dispatch :task/toggle task-id)))

(defn render [task]
  (fn [state mutate]
    (div
      {:style style-task}
      (div
        {:style (style-toggle (:done? task)),
         :event {:click (handle-toggle (:id task))}})
      (input
        {:style style-input,
         :event {:input (handle-input (:id task))},
         :attrs {:value (:text task)}})
      (button {:style style-button, :attrs {:inner-text "Add"}})
      (comp-debug task {}))))

(def comp-task (create-comp :task render))
