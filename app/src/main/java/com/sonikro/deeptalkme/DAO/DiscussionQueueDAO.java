package com.sonikro.deeptalkme.DAO;

import com.sonikro.deeptalkme.api.APIReturn;
import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.DiscussionRequest;

/**
 * Created by Jonathan Nagayoshi on 5/07/2016.
 */
public class DiscussionQueueDAO extends APIDAO {
    public static final String REQUEST_DISCUSSION = "RequestDiscussion";
    public static final String GET_DISCUSSION = "GetDiscussion";
    public static final String CANCEL_REQUEST_DISCUSSION = "CancelRequestDiscussion";
    private static DiscussionQueueDAO singleton;

    public static DiscussionQueueDAO getInstance()
    {
        if(singleton == null)
        {
            singleton = new DiscussionQueueDAO();
        }

        return singleton;
    }

    public APIReturn requestDiscussion(DiscussionRequest request) throws Exception
    {
        APIReturn apiReturn;
        try {
            getApi().connect(REQUEST_DISCUSSION);
            getApi().writeToService(request);
            apiReturn = getApi().readFromService(null);
            return apiReturn;
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }

    public Discussion getDiscussion(DiscussionRequest request) throws Exception
    {
        APIReturn apiReturn;
        try {
            getApi().connect(GET_DISCUSSION);
            getApi().writeToService(request);
            apiReturn = getApi().readFromService(Discussion.class);
            return (Discussion) apiReturn.getReturnObject();
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }

    public void cancelQueue(DiscussionRequest request) throws Exception
    {
        APIReturn apiReturn;
        try {
            getApi().connect(CANCEL_REQUEST_DISCUSSION);
            getApi().writeToService(request);
            apiReturn = getApi().readFromService(null);
            return;
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }
}
