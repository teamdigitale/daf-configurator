package swagger;

import com.google.inject.Inject;
import it.gov.daf.common.authentication.Authentication;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import org.pac4j.play.store.PlaySessionStore;
import play.api.Configuration;

import java.util.concurrent.CompletionStage;

public class ApiCall extends Action<SwaggerUtils.ApiAction> {

    Authentication authentication;

    @Inject
    private ApiCall(Configuration configuration, PlaySessionStore playSessionStore) {
        authentication.apply(configuration, playSessionStore);
    }

    public CompletionStage<Result> call(Http.Context ctx) {
        try {
            //TODO: Do stuff you want to handle with each API call (metrics, logging, etc..)
            return delegate.call(ctx);
        } catch (Throwable t) {
            //TODO: log the error in your metric

            //We rethrow this error so it will be caught in the ErrorHandler
            throw t;
        }
    }
}