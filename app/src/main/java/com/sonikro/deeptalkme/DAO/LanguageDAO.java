package com.sonikro.deeptalkme.DAO;

import com.sonikro.deeptalkme.api.APIRequestBuilder;
import com.sonikro.deeptalkme.api.APIReturn;
import com.sonikro.deeptalkme.api.model.ObjectArray;
import com.sonikro.deeptalkme.model.Language;
import com.sonikro.deeptalkme.model.Subject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jonathan Nagayoshi on 21/06/2016.
 */
public class LanguageDAO extends APIDAO{
    public static final String GET_ALL_LANGUAGES = "GetAllLanguages";
    private static LanguageDAO instance = null;

    public static LanguageDAO getInstance()
    {
        if(instance == null)
        {
            instance = new LanguageDAO();
        }
        return instance;
    }

    public APIRequestBuilder getAllLanguagesRequest()
    {
        APIRequestBuilder builder = new APIRequestBuilder();
        builder.setServiceName(GET_ALL_LANGUAGES);
        builder.setReturnObjectClass(Language[].class);
        builder.setRequestObject(null);
        return builder;
    }

    public Language[] getAllLanguages() throws Exception
    {
        APIReturn apiReturn;
        try {
            getApi().connect(GET_ALL_LANGUAGES);
            getApi().writeToService(null); //This will ensure the TOKEN is Written to the Request
            apiReturn = getApi().readFromService(ObjectArray.class);
            ObjectArray objectArray = (ObjectArray) apiReturn.getReturnObject();
            Language[] languages = Arrays.copyOf(objectArray.getObjectArray(),
                    objectArray.getObjectArray().length, Language[].class);
            return languages;
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }
}
