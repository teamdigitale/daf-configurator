package swagger;


import apiModels.Error;
import com.typesafe.config.Config;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.*;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.libs.Json;
import play.mvc.Http.*;
import play.mvc.*;

import javax.inject.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import static play.mvc.Results.*;

@Singleton
public class ErrorHandler extends DefaultHttpErrorHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Inject
    public ErrorHandler(Config configuration, Environment environment, OptionalSourceMapper sourceMapper, Provider<Router> routes) {
        super(configuration, environment, sourceMapper, routes);
    }

    @Override
    protected CompletionStage<Result> onDevServerError(RequestHeader request, UsefulException exception) {
        return CompletableFuture.completedFuture(
            handleExceptions(exception, extractTypeException(exception.description))
        );
    }

    @Override
    protected CompletionStage<Result> onProdServerError(RequestHeader request, UsefulException exception) {
        return CompletableFuture.completedFuture(
            handleExceptions(exception, extractTypeException(exception.description))
        );
    }

    @Override
    protected void logServerError(RequestHeader request, UsefulException usefulException) {
        //Since the error is already handled, we don't want to print anything on the console
        //But if you want to have the error printed in the console, just delete this override
    }

    private Result handleExceptions(Throwable t, String nameException) {
        //TODO: Handle exception that need special response (return a special apimodel, notFound(), etc..)
        Error error = new Error();
        error.setMessage(t.getCause().getMessage());
        switch (nameException){
            case "IllegalArgumentException": return badRequest(parseErrorToJson(error));
            case "UnauthorizedException": return unauthorized(parseErrorToJson(error));
            case "NotFoundException": return notFound(parseErrorToJson(error));
            default: return internalServerError(parseErrorToJson(error));
        }
    }


    private String extractTypeException(String str) { return str.substring(0, str.indexOf(":")).trim(); }

    private JsonNode parseErrorToJson(Error error) {
        try{
            return Json.parse(mapper.writeValueAsString(error));
        }catch (Exception e){
            return Json.parse("error in parse object");
        }
    }
}
