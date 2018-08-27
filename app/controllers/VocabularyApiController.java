package controllers;

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

import javax.validation.constraints.*;
import play.Configuration;

import swagger.SwaggerUtils.ApiAction;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-08-18T11:53:14.465+02:00")

public class VocabularyApiController extends Controller {

    private final VocabularyApiControllerImpInterface imp;
    private final ObjectMapper mapper;
    private final Configuration configuration;

    @Inject
    private VocabularyApiController(Configuration configuration, VocabularyApiControllerImpInterface imp) {
        this.imp = imp;
        mapper = new ObjectMapper();
        this.configuration = configuration;
    }


    @ApiAction
    public Result addVoc() throws Exception {
        JsonNode nodebody = request().body().asJson();
        Vocabulary body;
        if (nodebody != null) {
            body = mapper.readValue(nodebody.toString(), Vocabulary.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                SwaggerUtils.validate(body);
            }
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
        if (configuration.getBoolean("useOutputBeanValidation")) {
            SwaggerUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result updateVoc() throws Exception {
        JsonNode nodebody = request().body().asJson();
        Vocabulary body;
        if (nodebody != null) {
            body = mapper.readValue(nodebody.toString(), Vocabulary.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                SwaggerUtils.validate(body);
            }
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
