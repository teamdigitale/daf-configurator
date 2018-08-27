
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/ale/Development/teamdigitale/code/daf-backend/daf-configurator/conf/routes
// @DATE:Sat Aug 18 11:53:58 CEST 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:5
  ApiDocController_2: controllers.ApiDocController,
  // @LINE:9
  VocabularyApiController_0: controllers.VocabularyApiController,
  // @LINE:16
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:5
    ApiDocController_2: controllers.ApiDocController,
    // @LINE:9
    VocabularyApiController_0: controllers.VocabularyApiController,
    // @LINE:16
    Assets_1: controllers.Assets
  ) = this(errorHandler, ApiDocController_2, VocabularyApiController_0, Assets_1, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, ApiDocController_2, VocabularyApiController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api""", """controllers.ApiDocController.api"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """v2/vocabularies""", """controllers.VocabularyApiController.addVoc()"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """v2/vocabulary/""" + "$" + """vocId<[^/]+>""", """controllers.VocabularyApiController.deleteVoc(vocId:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """v2/vocabulary/""" + "$" + """vocId<[^/]+>""", """controllers.VocabularyApiController.getVocById(vocId:Long)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """v2/vocabularies""", """controllers.VocabularyApiController.updateVoc()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """v2/vocabulary/""" + "$" + """vocId<[^/]+>""", """controllers.VocabularyApiController.updateVocWithForm(vocId:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.at(file:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """versionedAssets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:5
  private[this] lazy val controllers_ApiDocController_api0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api")))
  )
  private[this] lazy val controllers_ApiDocController_api0_invoker = createInvoker(
    ApiDocController_2.api,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApiDocController",
      "api",
      Nil,
      "GET",
      this.prefix + """api""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_VocabularyApiController_addVoc1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("v2/vocabularies")))
  )
  private[this] lazy val controllers_VocabularyApiController_addVoc1_invoker = createInvoker(
    VocabularyApiController_0.addVoc(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.VocabularyApiController",
      "addVoc",
      Nil,
      "POST",
      this.prefix + """v2/vocabularies""",
      """Functions for Vocabulary API""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_VocabularyApiController_deleteVoc2_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("v2/vocabulary/"), DynamicPart("vocId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_VocabularyApiController_deleteVoc2_invoker = createInvoker(
    VocabularyApiController_0.deleteVoc(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.VocabularyApiController",
      "deleteVoc",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """v2/vocabulary/""" + "$" + """vocId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_VocabularyApiController_getVocById3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("v2/vocabulary/"), DynamicPart("vocId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_VocabularyApiController_getVocById3_invoker = createInvoker(
    VocabularyApiController_0.getVocById(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.VocabularyApiController",
      "getVocById",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """v2/vocabulary/""" + "$" + """vocId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_VocabularyApiController_updateVoc4_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("v2/vocabularies")))
  )
  private[this] lazy val controllers_VocabularyApiController_updateVoc4_invoker = createInvoker(
    VocabularyApiController_0.updateVoc(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.VocabularyApiController",
      "updateVoc",
      Nil,
      "PUT",
      this.prefix + """v2/vocabularies""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_VocabularyApiController_updateVocWithForm5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("v2/vocabulary/"), DynamicPart("vocId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_VocabularyApiController_updateVocWithForm5_invoker = createInvoker(
    VocabularyApiController_0.updateVocWithForm(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.VocabularyApiController",
      "updateVocWithForm",
      Seq(classOf[Long]),
      "POST",
      this.prefix + """v2/vocabulary/""" + "$" + """vocId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_Assets_at6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at6_invoker = createInvoker(
    Assets_1.at(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Assets_versioned7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("versionedAssets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned7_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String]),
      "GET",
      this.prefix + """versionedAssets/""" + "$" + """file<.+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:5
    case controllers_ApiDocController_api0_route(params) =>
      call { 
        controllers_ApiDocController_api0_invoker.call(ApiDocController_2.api)
      }
  
    // @LINE:9
    case controllers_VocabularyApiController_addVoc1_route(params) =>
      call { 
        controllers_VocabularyApiController_addVoc1_invoker.call(VocabularyApiController_0.addVoc())
      }
  
    // @LINE:10
    case controllers_VocabularyApiController_deleteVoc2_route(params) =>
      call(params.fromPath[Long]("vocId", None)) { (vocId) =>
        controllers_VocabularyApiController_deleteVoc2_invoker.call(VocabularyApiController_0.deleteVoc(vocId))
      }
  
    // @LINE:11
    case controllers_VocabularyApiController_getVocById3_route(params) =>
      call(params.fromPath[Long]("vocId", None)) { (vocId) =>
        controllers_VocabularyApiController_getVocById3_invoker.call(VocabularyApiController_0.getVocById(vocId))
      }
  
    // @LINE:12
    case controllers_VocabularyApiController_updateVoc4_route(params) =>
      call { 
        controllers_VocabularyApiController_updateVoc4_invoker.call(VocabularyApiController_0.updateVoc())
      }
  
    // @LINE:13
    case controllers_VocabularyApiController_updateVocWithForm5_route(params) =>
      call(params.fromPath[Long]("vocId", None)) { (vocId) =>
        controllers_VocabularyApiController_updateVocWithForm5_invoker.call(VocabularyApiController_0.updateVocWithForm(vocId))
      }
  
    // @LINE:16
    case controllers_Assets_at6_route(params) =>
      call(params.fromPath[String]("file", None)) { (file) =>
        controllers_Assets_at6_invoker.call(Assets_1.at(file))
      }
  
    // @LINE:17
    case controllers_Assets_versioned7_route(params) =>
      call(params.fromPath[String]("file", None)) { (file) =>
        controllers_Assets_versioned7_invoker.call(Assets_1.versioned(file))
      }
  }
}
