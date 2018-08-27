
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/ale/Development/teamdigitale/code/daf-backend/daf-configurator/conf/routes
// @DATE:Sat Aug 18 11:53:58 CEST 2018

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:5
package controllers.javascript {

  // @LINE:9
  class ReverseVocabularyApiController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def addVoc: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.VocabularyApiController.addVoc",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "v2/vocabularies"})
        }
      """
    )
  
    // @LINE:10
    def deleteVoc: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.VocabularyApiController.deleteVoc",
      """
        function(vocId0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "v2/vocabulary/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("vocId", vocId0))})
        }
      """
    )
  
    // @LINE:12
    def updateVoc: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.VocabularyApiController.updateVoc",
      """
        function() {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "v2/vocabularies"})
        }
      """
    )
  
    // @LINE:13
    def updateVocWithForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.VocabularyApiController.updateVocWithForm",
      """
        function(vocId0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "v2/vocabulary/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("vocId", vocId0))})
        }
      """
    )
  
    // @LINE:11
    def getVocById: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.VocabularyApiController.getVocById",
      """
        function(vocId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v2/vocabulary/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("vocId", vocId0))})
        }
      """
    )
  
  }

  // @LINE:5
  class ReverseApiDocController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def api: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApiDocController.api",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api"})
        }
      """
    )
  
  }

  // @LINE:16
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def at: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.at",
      """
        function(file0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("file", file0)})
        }
      """
    )
  
    // @LINE:17
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "versionedAssets/" + (""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("file", file0)})
        }
      """
    )
  
  }


}
