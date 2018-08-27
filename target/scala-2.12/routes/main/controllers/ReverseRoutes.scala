
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/ale/Development/teamdigitale/code/daf-backend/daf-configurator/conf/routes
// @DATE:Sat Aug 18 11:53:58 CEST 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:5
package controllers {

  // @LINE:9
  class ReverseVocabularyApiController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def addVoc(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "v2/vocabularies")
    }
  
    // @LINE:10
    def deleteVoc(vocId:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "v2/vocabulary/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("vocId", vocId)))
    }
  
    // @LINE:12
    def updateVoc(): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "v2/vocabularies")
    }
  
    // @LINE:13
    def updateVocWithForm(vocId:Long): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "v2/vocabulary/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("vocId", vocId)))
    }
  
    // @LINE:11
    def getVocById(vocId:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "v2/vocabulary/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("vocId", vocId)))
    }
  
  }

  // @LINE:5
  class ReverseApiDocController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def api(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api")
    }
  
  }

  // @LINE:16
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def at(file:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
    }
  
    // @LINE:17
    def versioned(file:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "versionedAssets/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
    }
  
  }


}
