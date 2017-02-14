package com.sonikro.deeptalkme.DAO;

import com.sonikro.deeptalkme.api.APIReturn;
import com.sonikro.deeptalkme.api.model.ObjectArray;
import com.sonikro.deeptalkme.model.Subject;
import com.sonikro.deeptalkme.model.Topic;

import java.util.Arrays;

/**
 * Created by Jonathan Nagayoshi on 13/07/2016.
 */
public class TopicDAO extends APIDAO {
    public static final String GET_TOPICS = "GetTopics";
    private static TopicDAO singleton;

    public static TopicDAO getInstance()
    {
        if(singleton == null)
        {
            singleton = new TopicDAO();
        }
        return singleton;
    }

    public Topic[] getSubjectTopics(Subject subject) throws Exception
    {
        APIReturn apiReturn = null;
        try {
            getApi().connect(GET_TOPICS);
            getApi().writeToService(subject);
            apiReturn = getApi().readFromService(Topic[].class);
            Topic[] objectArray = (Topic[])apiReturn.getReturnObject() ;
            return Arrays.copyOf(objectArray,objectArray.length,Topic[].class);
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }
}
