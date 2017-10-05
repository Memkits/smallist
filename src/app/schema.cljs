
(ns app.schema )

(def store {:states {}, :stack [], :tasks {}})

(def task {:id nil, :done? false, :text "", :active? true, :tag-ids #{}})
