package controllers;

import apiModels.Vocabulary;
import it.gov.daf.common.CredentialManager;
import it.gov.daf.common.utils.UserInfo;
import it.gov.daf.utils.ConfigReader;
import it.gov.daf.utils.java.NotFoundException;
import it.gov.daf.utils.java.UnauthorizedException;
import play.Environment;
import play.Logger;
import play.api.mvc.RequestHeader;

import java.io.*;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-08-31T11:37:42.017+02:00")

public class VocabularyApiControllerImp implements VocabularyApiControllerImpInterface {

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
    public Vocabulary getVocByName(RequestHeader requestHeader, String name) throws Exception {
        UserInfo userInfo = CredentialManager.readCredentialFromRequest(requestHeader);
        Logger.debug(userInfo.username() + " get voc " + name);
        if(CredentialManager.isOrgsAdmin(requestHeader, userInfo.groups()) || CredentialManager.isOrgsEditor(requestHeader, userInfo.groups())) {
            BufferedReader br = null;
            FileReader fr = null;
            try {
                fr = new FileReader(Environment.simple().getFile(vocPath + name + ".json"));
                br = new BufferedReader(fr);
                String sCurrentLine;
                StringBuffer fileVoc = new StringBuffer();
                while ((sCurrentLine = br.readLine()) != null) {
                    fileVoc.append(sCurrentLine.replaceAll("\n", "").trim());
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
        } else throw new UnauthorizedException("unauthorized to get voc");
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
