(ns learnreactplayer.views
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]))

;; -------------------------
;; Views
;import React, { Component } from 'react'
;import ReactPlayer from 'react-player'
;
;class App extends Component {
;     render () {
;                 return <ReactPlayer url='https://www.youtube.com/watch?v=ysz5S6PUM-U' playing />
;               }
; }

;; This way work
;(defn home-page []
;  (let [react-player (aget js/window "deps" "react-player")]
;    [:div
;     [:h2 "Zef Style"]
;     [:> react-player {:url "https://youtu.be/uMK0prafzw0"}]]))

;; This way also works
;(def react-player (reagent/adapt-react-class (aget js/window "deps" "react-player")))
;
;(defn home-page []
;  [react-player {:url "https://youtu.be/uMK0prafzw0"}])

;; This way work too.
(def react-player (reagent/adapt-react-class (aget js/window "deps" "react-player")))

(defn home-page-render []
  [react-player {:url "https://youtu.be/uMK0prafzw0"}])

(defn home-page []
  (reagent/create-class {:reagent-render home-page-render}))


;// tutorial1.js
;var CommentBox = React.createClass({
;  render: function() {
;    return (
;       <div className="commentBox">
;          Hello, world! I am a CommentBox.
;       </div>)}})
;                                                                      ;
;                                    ;
;React.render(
;  <CommentBox />,
;     document.getElementById('content'))
;;

;(defn CommentBox []
;  [:div.commentBox
;   "Hello, world! I am a CommentBox"])

;// tutorial2.js
;var CommentList = React.createClass({
;  render: function() {
;    return (
;       <div className="commentList">
;          Hello, world! I am a CommentList.
;       </div>)}})
;
;
;var CommentForm = React.createClass({
;  render: function() {
;    return (
;       <div className="commentForm">
;          Hello, world! I am a CommentForm.
;       </div>)}})
                                ;
;
;// tutorial3.js
;var CommentBox = React.createClass({
;  render: function() {
;     return (
;       <div className="commentBox">
;         <h1>Comments</h1>
;         <CommentList />
;         <CommentForm />
;       </div>)}})
;                                    ;

;(defn CommentList []
;  [:div.commentList
;   "Hello, world! I am a CommentList."])
;
;(defn CommentForm []
;  [:div.commentForm
;   "Hello, world! I am a CommentForm."])
;
;(defn CommentBox []
;  [:div.commentBox
;   [:h1 "Comments"]
;   [CommentList]
;   [CommentForm]])

;; Using Props
;; We can pass information from the parent node to child node, which is called props in child.
;; So when we want to construct a Comment, we can specify the information it needed: author and content:

;// tutorial4.js
;var Comment = React.createClass({
;  render: function() {
;    return (
;      <div className="comment">
;         <h2 className="commentAuthor">  {this.props.author}
;         </h2>
;         {this.props.children}
;      </div>)}})
;                                                                   ;
;
;  And we can specify the information(props) via:

;// tutorial5.js
;var CommentList = React.createClass({
;  render: function() {
;      return (
;        <div className="commentList">
;           <Comment author="Pete Hunt">This is one comment</Comment>
;           <Comment author="Jordan Walke">This is *another* comment</Comment>
;        </div>)}})
;

(defn comment-item [first-comp & rest-comp]
  (let [this (reagent/current-component)]
    [:div
     [:p "The 'props' propertity: " (str (reagent/props this))]
     [:p "The first component: " (str first-comp)]
     [:p "The rest component: " (str rest-comp)]
     [:p "The children component: " (str (reagent/children this))]]))

;(defn comment-item [author & children]
;  [:div.commentBox
;   [:h2.commentAuthor author]
;   children])



;(defn CommentBox []
;  [:div.commentBox
;   [:h1 "Comments"]
;   [CommentList]
;   [CommentForm]])

(defn comment-list []
  [:div
   [comment-item {:author "Your Name"}
    [:p "first component"]
    [:p "second component"]]])

(defn CommentForm []
  [:div.commentForm
   "Hello, world! I am a CommentForm."])

(defn CommentBox []
  [:div.commentBox
   [:h1 "Comments"]
   [comment-list]])


(defn my-div []
  (let [this (reagent/current-component)]
    (into [:div.custom (reagent/props this)]
          (reagent/children this))))

(defn call-my-div []
  [:div
   [my-div "Some text."]
   [my-div {:style {:font-weight 'bold}}
    [:p "Some other text in bold."]
    [:p "some other text"]]])



(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div "Hello from " @name
       [CommentBox]
       [call-my-div]
       [home-page]])))

