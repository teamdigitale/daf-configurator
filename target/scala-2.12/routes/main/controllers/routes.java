
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/ale/Development/teamdigitale/code/daf-backend/daf-configurator/conf/routes
// @DATE:Sat Aug 18 11:53:58 CEST 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseVocabularyApiController VocabularyApiController = new controllers.ReverseVocabularyApiController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseApiDocController ApiDocController = new controllers.ReverseApiDocController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseVocabularyApiController VocabularyApiController = new controllers.javascript.ReverseVocabularyApiController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseApiDocController ApiDocController = new controllers.javascript.ReverseApiDocController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
