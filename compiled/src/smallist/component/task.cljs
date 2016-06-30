
(ns smallist.component.task
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div input button]]
            [respo.component.debug :refer [comp-debug]]
            [smallist.component.space :refer [comp-space]]))

(def style-task
 {:display "flex",
  :flex-direction "row",
  :border-bottom (str "1px solid " (hsl 0 0 90))})

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
  :border "none",
  :font-family "Roboto,Helvetica,sans-serif"})

(def style-button
 {:color (hsl 0 0 100),
  :font-size "18px",
  :background-color (hsl 200 80 60),
  :padding "0 8px",
  :border "none"})

(def style-rm
 {:background-color (hsl 0 80 40),
  :width "40px",
  :outline "none",
  :display "inline-block",
  :border "none",
  :height "40px"})

(defn handle-input [task-id]
  (fn [e dispatch! mutate!]
    (dispatch! :task/text [task-id (:value e)])))

(defn handle-toggle [task-id]
  (fn [e dispatch! mutate!] (dispatch! :task/toggle task-id)))

(defn handle-rm [task-id]
  (fn [e dispatch! mutate!] (dispatch! :task/rm task-id)))

(defn render [task]
  (fn [state mutate!]
    (div
      {:style style-task}
      (div
        {:style (style-toggle (:done? task)),
         :event {:click (handle-toggle (:id task))}})
      (comp-space 8 nil)
      (input
        {:style style-input,
         :event {:input (handle-input (:id task))},
         :attrs {:placeholder "empty", :value (:text task)}})
      (comp-space 8 nil)
      (button
        {:style style-rm, :event {:click (handle-rm (:id task))}})
      (comment comp-debug task {:z-index 100, :left 100}))))

(def comp-task (create-comp :task render))
