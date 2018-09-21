package controllers;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import apiModels.Vocabulary;
import it.gov.daf.common.CredentialManager;
import it.gov.daf.common.authentication.Authentication;
import it.gov.daf.helpers.TestJava;

import it.gov.daf.utils.java.NotFoundException;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;



@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-08-31T11:37:42.017+02:00")

public class VocabularyApiControllerImp implements VocabularyApiControllerImpInterface {

    final ActorSystem system = ActorSystem.create("daf-configurator");
    final ActorMaterializer materializer = ActorMaterializer.create(system);

    @Override
    public void addVoc(Vocabulary body) throws Exception {
        //Do your magic!!!
    }

    @Override
    public void deleteVoc(Long vocId) throws Exception {
        //Do your magic!!!
    }

    @Override
    public Vocabulary getVocById(Long vocId, String token) throws Exception {
//        if(false) throw new NotFoundException("voc not found");
        System.out.println("credentials = " + Authentication.getClaimsFromToken2(token));
        return TestJava.test();
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
