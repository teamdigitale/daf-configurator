package controllers;

import apiModels.Vocabulary;

import play.api.mvc.RequestHeader;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import java.io.File;
import swagger.SwaggerUtils;
import com.fasterxml.jackson.core.type.TypeReference;


    import swagger.SwaggerUtils.ApiAction;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-10-05T14:18:59.768+02:00")

    public class VocabularyApiController extends Controller {

        private final VocabularyApiControllerImpInterface imp;
    private final ObjectMapper mapper;

    @Inject
    private VocabularyApiController(VocabularyApiControllerImpInterface imp) {
        this.imp = imp;
    mapper = new ObjectMapper();
    }


        @ApiAction
        public Result addVoc() throws Exception {
                JsonNode nodebody = request().body().asJson();
                Vocabulary body;
                if (nodebody != null) {
                body = mapper.readValue(nodebody.toString(), Vocabulary.class);
                } else {
                    throw new IllegalArgumentException("'body' parameter is required");
                }
            play.api.mvc.RequestHeader requestHeader = request().asScala();
            imp.addVoc(requestHeader, body);
                return ok();
        }

        @ApiAction
        public Result deleteVoc(String name) throws Exception {
            play.api.mvc.RequestHeader requestHeader = request().asScala();
            imp.deleteVoc(requestHeader, name);
                return ok();
        }

        @ApiAction
        public Result getVocById(String name) throws Exception {
                String valueauthorization = request().header("Authorization").get();
                String authorization;
                if (valueauthorization != null) {
                authorization = valueauthorization;
                } else {
                    throw new IllegalArgumentException("'Authorization' parameter is required");
                }
            play.api.mvc.RequestHeader requestHeader = request().asScala();
            Vocabulary obj = imp.getVocById(requestHeader, name, authorization);
                JsonNode result = mapper.valueToTree(obj);
                return ok(result);
        }

        @ApiAction
        public Result updateVoc() throws Exception {
                JsonNode nodebody = request().body().asJson();
                Vocabulary body;
                if (nodebody != null) {
                body = mapper.readValue(nodebody.toString(), Vocabulary.class);
                } else {
                    throw new IllegalArgumentException("'body' parameter is required");
                }
            play.api.mvc.RequestHeader requestHeader = request().asScala();
            imp.updateVoc(requestHeader, body);
                return ok();
        }

        @ApiAction
        public Result updateVocWithForm(String name) throws Exception {
                    String valuevoc = (request().body().asMultipartFormData().asFormUrlEncoded().get("voc"))[0];
                    String voc;
                    if (valuevoc != null) {
                    voc = valuevoc;
                    } else {
                        voc = null;
                    }
            play.api.mvc.RequestHeader requestHeader = request().asScala();
            imp.updateVocWithForm(requestHeader, name, voc);
                return ok();
        }
    }
