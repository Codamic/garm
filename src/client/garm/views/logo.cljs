(ns garm.views.logo)

(defn logo []
  [:div {:class "text-center"}
   [:a {:href "/" :class "logo"}
    LOGO
    ;; <!-- Image Logo here -->
    ;; <!--<a href="index.html" class="logo">-->
    ;; <!--<i class="icon-c-logo"> <img src="assets/images/logo_sm.png" height="42"/> </i>-->
    ;; <!--<span><img src="assets/images/logo_light.png" height="20"/></span>-->
    ;; <!--</a>-->
    ]])
