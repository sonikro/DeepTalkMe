package com.sonikro.deeptalkme.model;

import com.sonikro.deeptalkme.DAO.DiscussionQueueDAO;

/**
 * Created by Jonathan Nagayoshi on 6/07/2016.
 */
public class DiscussionRequest extends Discussion {

    public void request() throws Exception
    {
        DiscussionQueueDAO.getInstance().requestDiscussion(this);
    }

    public Discussion getDiscussion() throws Exception
    {
        return DiscussionQueueDAO.getInstance().getDiscussion(this);
    }

    public void cancelRequest() throws Exception
    {
        DiscussionQueueDAO.getInstance().cancelQueue(this);
    }
}
