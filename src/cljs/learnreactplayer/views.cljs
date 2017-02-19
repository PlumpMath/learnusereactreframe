(ns learnreactplayer.views
    (:require [re-frame.core :as re-frame]))

;; -------------------------
;; Views
(defn home-page []
  (let [react-player (aget js/window "deps" "react-player")]
    [:div
     [:h2 "Zef Style"]
     [:> react-player {:url "https://youtu.be/uMK0prafzw0"}]]))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div "Hello from " @name
       [home-page]])))
