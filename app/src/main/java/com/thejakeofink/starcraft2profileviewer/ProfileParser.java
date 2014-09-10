package com.thejakeofink.starcraft2profileviewer;

import org.json.JSONObject;

/**
 * Created by Jacob Stokes on 9/9/14.
 */
public class ProfileParser {

    private ProfileResponse response;

    public ProfileParser(ProfileResponse response) {
        this.response = response;
    }

    public void parseResponse() {
        JSONObject root = response.getResponse();


    }

}
