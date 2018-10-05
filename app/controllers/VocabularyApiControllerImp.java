package controllers;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import apiModels.Vocabulary;
import it.gov.daf.common.CredentialManager;
import it.gov.daf.common.utils.UserInfo;
import it.gov.daf.utils.ConfigReader;
import it.gov.daf.utils.java.NotFoundException;
import it.gov.daf.utils.java.UnauthorizedException;
import play.api.mvc.RequestHeader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;



@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-08-31T11:37:42.017+02:00")

public class VocabularyApiControllerImp implements VocabularyApiControllerImpInterface {

    final String basePath = System.getProperty("user.dir");
    final String vocPath = ConfigReader.getVocPath();

    @Override
    public void addVoc(RequestHeader headers, Vocabulary vocabulary) throws Exception {
        //Do your magic!!!
    }

    @Override
    public void deleteVoc(RequestHeader headers, String name) throws Exception {
        //Do your magic!!!
    }

    @Override
    public Vocabulary getVocById(RequestHeader requestHeader, String name, String token) throws Exception {
        UserInfo userInfo = CredentialManager.readCredentialFromRequest(requestHeader);
        if(CredentialManager.isOrgsAdmin(requestHeader, userInfo.groups()) || CredentialManager.isOrgsEditor(requestHeader, userInfo.groups())) {
            BufferedReader br = null;
            FileReader fr = null;
            try {
                fr = new FileReader(basePath + vocPath + name);
                br = new BufferedReader(fr);
                String sCurrentLine;
                StringBuffer fileVoc = new StringBuffer();
                while ((sCurrentLine = br.readLine()) != null) {
                    fileVoc.append(sCurrentLine);
                }
                Vocabulary voc = new Vocabulary();
                voc.setVoc(fileVoc.toString());
                return voc;
            } catch (FileNotFoundException e) {
                throw new NotFoundException(e.getMessage());
            }
                finally {
                try {
                    if (br != null)
                        br.close();
                    if (fr != null)
                        fr.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        else throw new UnauthorizedException("unauthorized");
    }

    @Override
    public void updateVoc( RequestHeader headers, Vocabulary body) throws Exception {
        //Do your magic!!!
    }

    @Override
    public void updateVocWithForm(RequestHeader headers, String name, String voc) throws Exception {
        //Do your magic!!!
    }

}
