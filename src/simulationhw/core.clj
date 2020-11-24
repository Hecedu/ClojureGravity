;;Physics simulation program
;;by Hector Magana
;;Simulates models using certain quirks of the clojure language
;;1)Leiningen
(ns simulation.core(:gen-class))

;;functions
;;2)anonymous function
(defn position [velocity acceleration currentTime]
  (
    (fn [velocity acceleration currentTime] 
      (+ 
      (* velocity currentTime) 
      (* 0.5 (* acceleration (* currentTime currentTime ))))
    ) 
    velocity acceleration currentTime
  )
)
;;3)future and deref
;;4)atom and @
;;5)map
;;?)Recursion
;;?)vector
(defn positionUser []
  (println "This program simulates two moving objects over the same amount of time")
  (println "input simulation time:")
  (def simTime (atom (Float/parseFloat(read-line))))
  (println "input initial velocity:")
  (def velocity (Float/parseFloat (read-line)))
  (println "input initial acceleration:")
  (def acceleration (Float/parseFloat (read-line)))
  (println)
  (println "   simulation results: \n -----------------------")
  (map deref (map #(future 
    (do (dotimes [n %1]
    (println "position at " (+ n 1) " is: " 
      (position velocity acceleration (+ n 1)))
    ) %1)) 
  [@simTime]))
)
;;6)let
(defn freefall [velocity initialPosition currentTime]
  (
    let [v velocity x initialPosition t currentTime]
    (+ 
      (+ (* -4.9 t t)) (* v t)
      x)
  )
)
(defn freefallUser []
  (do
    (println "This program calculates free fall over a lapse of time")
    (println "input simulation time")
    (def simTime (atom (Float/parseFloat(read-line))))
    (println "input initial velocity")
    (def velocity (Float/parseFloat (read-line)))
    (println "input initial position")
    (def position (Float/parseFloat (read-line)))
    (println "   simulation results: \n -----------------------")
    (map deref (map #(future 
      (do (dotimes [n %1]
      (println "position at " (+ n 1) " is: " 
        (freefall velocity position (+ n 1)))
      ) %1)) 
    [@simTime]))
  )
)
;;I/O
;;7) cond
;;8)while loop
(defn menu []
  (println "welcome to the simulation software\nplease enter which simulation you want to execute\n1)position simulation\n2)free fall simulation")
  (def userInput (Integer/parseInt(read-line)))
  (cond 
  (= userInput 1) (positionUser)
  (= userInput 2) (freefallUser)
  :else (println "not valid")
  )
)

(menu)

;;this part was not possible to complete for some reason. It messes up the future in my other code and it stops working properly
(def n 1)
(while 
  (= n 1)
  (do
    (menu)
    (def n 0)
  )
)

  
    
 

