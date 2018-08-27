
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/ale/Development/teamdigitale/code/daf-backend/daf-configurator/conf/routes
// @DATE:Sat Aug 18 11:53:58 CEST 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
