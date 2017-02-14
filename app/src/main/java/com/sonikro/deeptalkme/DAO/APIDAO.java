package com.sonikro.deeptalkme.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sonikro.deeptalkme.api.DeepTalkMeAPI;

/**
 * Created by Jonathan Nagayoshi on 28/06/2016.
 */
public abstract class APIDAO {
    private DeepTalkMeAPI api = null;

    public APIDAO()
    {
        this.setApi(DeepTalkMeAPI.getInstance());
    }

    protected DeepTalkMeAPI getApi() {
        return api;
    }

    protected void setApi(DeepTalkMeAPI api) {
        this.api = api;
    }

}
