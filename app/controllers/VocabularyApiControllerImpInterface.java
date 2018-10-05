package controllers;

import apiModels.Vocabulary;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import play.api.mvc.RequestHeader;


    @SuppressWarnings("RedundantThrows")
    public interface VocabularyApiControllerImpInterface {
        void addVoc(RequestHeader headers, Vocabulary body) throws Exception;

        void deleteVoc(RequestHeader headers, String name) throws Exception;

        Vocabulary getVocById(RequestHeader headers, String name) throws Exception;

        void updateVoc(RequestHeader headers, Vocabulary body) throws Exception;

        void updateVocWithForm(RequestHeader headers, String name, String voc) throws Exception;

    }
