package controllers;

import apiModels.Vocabulary;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import javax.validation.constraints.*;
import it.gov.daf.helpers.TestJava;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-07-19T14:40:06.122+02:00")

public class VocabularyApiControllerImp implements VocabularyApiControllerImpInterface {
    @Override
    public void addVoc(Vocabulary body) throws Exception {
        //Do your magic!!!
    }

    @Override
    public void deleteVoc(Long vocId) throws Exception {
        //Do your magic!!!
    }

    @Override
    public Vocabulary getVocById(Long vocId) throws Exception {
        //Do your magic!!!
        Vocabulary voc = TestJava.test();
        return voc;
    }

    @Override
    public void updateVoc(Vocabulary body) throws Exception {
        //Do your magic!!!
    }

    @Override
    public void updateVocWithForm(Long vocId, String name) throws Exception {
        //Do your magic!!!
    }

}
