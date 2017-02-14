package com.sonikro.deeptalkme.DAO;

import com.sonikro.deeptalkme.api.APIRequestBuilder;
import com.sonikro.deeptalkme.api.APIReturn;
import com.sonikro.deeptalkme.api.model.ObjectArray;
import com.sonikro.deeptalkme.model.Language;
import com.sonikro.deeptalkme.model.Subject;

import java.util.Arrays;

/**
 * Created by Jonathan Nagayoshi on 21/06/2016.
 */
public class SubjectDAO extends APIDAO {
    private static SubjectDAO instance = null;
    public static final String GET_ALL_SUBJECTS = "GetAllSubjects";

    public static SubjectDAO getInstance()
    {
        if(instance == null)
        {
            instance = new SubjectDAO();
        }
        return instance;
    }
    public APIRequestBuilder getAllSubjectsRequest()
    {
        //Build API Request
        APIRequestBuilder requestBuilder = new APIRequestBuilder();
        requestBuilder.setServiceName(GET_ALL_SUBJECTS);
        requestBuilder.setReturnObjectClass(Subject[].class);
        requestBuilder.setRequestObject(null);
        return requestBuilder;
    }
    public Subject[] getAllSubjects() throws Exception
    {
        APIReturn apiReturn;
        try {
            getApi().connect(GET_ALL_SUBJECTS);
            getApi().writeToService(null); //This will ensure the TOKEN is Written to the Request
            apiReturn = getApi().readFromService(ObjectArray.class);
            ObjectArray objectArray = (ObjectArray) apiReturn.getReturnObject();
            Subject[] subjects = Arrays.copyOf(objectArray.getObjectArray(),
                                objectArray.getObjectArray().length,Subject[].class);
            return subjects;
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }
}
