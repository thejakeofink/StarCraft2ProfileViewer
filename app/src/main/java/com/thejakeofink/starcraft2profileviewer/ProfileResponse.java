package com.thejakeofink.starcraft2profileviewer;

import org.json.JSONObject;

/**
 * Created by Jacob Stokes on 9/8/14.
 */
public class ProfileResponse {
    private JSONObject responseObject;

    public JSONObject getResponse() {
        return responseObject;
    }

    public void setResponse(JSONObject response) {
        this.responseObject  = response;
    }
}
