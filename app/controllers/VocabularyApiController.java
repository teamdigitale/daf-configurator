package controllers;

import apiModels.Error;
import apiModels.Vocabulary;

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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-09-10T12:22:52.345+02:00")

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
        imp.addVoc(body);
        return ok();
    }

    @ApiAction
    public Result deleteVoc(Long vocId) throws Exception {
        imp.deleteVoc(vocId);
        return ok();
    }

    @ApiAction
    public Result getVocById(Long vocId) throws Exception {
        Vocabulary obj = imp.getVocById(vocId);
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
        imp.updateVoc(body);
        return ok();
    }

    @ApiAction
    public Result updateVocWithForm(Long vocId) throws Exception {
        String valuename = (request().body().asMultipartFormData().asFormUrlEncoded().get("name"))[0];
        String name;
        if (valuename != null) {
            name = valuename;
        } else {
            name = null;
        }
        imp.updateVocWithForm(vocId, name);
        return ok();
    }
}
