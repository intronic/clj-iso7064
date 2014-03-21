(defproject clj-iso7064 "0.1.0"
  :description "Partial Clojure implementation of iso7064 Pure Systems."
  :url "https://github.com/intronic/clj-iso7064"
  :license {:name "LGPL 3.0 (GNU Lesser General Public License)"
            :url "https://www.gnu.org/licenses/lgpl.html"}

  :dependencies [[org.clojure/clojure "1.5.1"]]

  :source-paths ["src"]
  :test-paths ["test"]

  :profiles {:dev {:dependencies [[expectations "2.0.7"]
                                  [org.clojure/tools.namespace "0.2.4"]
                                  [org.clojure/java.classpath "0.2.2"]
                                  [org.clojure/tools.trace "0.7.8"]]}}

  ;; :global-vars {*warn-on-reflection* true}

  :scm {:name "git" :url "https://github.com/intronic/clj-iso7064"
        :tag ""})
