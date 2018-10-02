package controllers;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import apiModels.Vocabulary;
import apiModels.VocabularyFromGit;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.gov.daf.common.CredentialManager;
import it.gov.daf.common.utils.UserInfo;
import it.gov.daf.utils.ConfigReader;
import it.gov.daf.utils.java.NotFoundException;
import it.gov.daf.utils.java.UnauthorizedException;
import play.api.mvc.RequestHeader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;



@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-08-31T11:37:42.017+02:00")

public class VocabularyApiControllerImp implements VocabularyApiControllerImpInterface {

    final ActorSystem system = ActorSystem.create("daf-configurator");
    final ActorMaterializer materializer = ActorMaterializer.create(system);

    final String gitVocUrl = ConfigReader.getGitVocUrl();
    final String gitVocPath = ConfigReader.getGitVocPath();

    @Override
    public void addVoc(Vocabulary body, RequestHeader headers) throws Exception {
        //Do your magic!!!
    }

    @Override
    public void deleteVoc(String name, RequestHeader headers) throws Exception {
        //Do your magic!!!
    }

    @Override
    public Vocabulary getVocById(String name, RequestHeader requestHeader) throws Exception {
        UserInfo userInfo = CredentialManager.readCredentialFromRequest(requestHeader);
        if(CredentialManager.isOrgsAdmin(requestHeader, userInfo.groups()) || CredentialManager.isOrgsEditor(requestHeader, userInfo.groups())) {
            URL url = new URL(gitVocUrl + gitVocPath + name);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if(con.getResponseCode() == 404) throw new NotFoundException(name + " not found");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            VocabularyFromGit vocabularyFromGit = new ObjectMapper().readValue(content.toString(), VocabularyFromGit.class);
            String contentString = StringUtils.newStringUtf8(Base64.decodeBase64(vocabularyFromGit.getContent()));
            Vocabulary voc = new Vocabulary();
            voc.setVoc(contentString);
            con.disconnect();
            return voc;
        }
        else throw new UnauthorizedException("unauthorized");
    }

    @Override
    public void updateVoc(Vocabulary body, RequestHeader headers) throws Exception {
        //Do your magic!!!
    }

    @Override
    public void updateVocWithForm(String name, String voc, RequestHeader headers) throws Exception {
        //Do your magic!!!
    }

}
