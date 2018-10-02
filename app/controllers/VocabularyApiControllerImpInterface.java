package controllers;

import apiModels.Vocabulary;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import play.api.mvc.RequestHeader;


    @SuppressWarnings("RedundantThrows")
    public interface VocabularyApiControllerImpInterface {
        void addVoc(Vocabulary body, RequestHeader headers) throws Exception;

        void deleteVoc(String name, RequestHeader headers) throws Exception;

        Vocabulary getVocById(String name, RequestHeader headers) throws Exception;

        void updateVoc(Vocabulary body, RequestHeader headers) throws Exception;

        void updateVocWithForm(String name, String voc, RequestHeader headers) throws Exception;

    }
