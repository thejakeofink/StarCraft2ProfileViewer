package com.thejakeofink.starcraft2profileviewer;

import java.io.ByteArrayOutputStream;

/**
 * Created by Jacob Stokes on 9/8/14.
 */
public class ProfileRequest {
    private static final String region = "http://us.battle.net/api/sc2/profile/";

    String userName;
    String accountNumber;
    ProfileResponse response;

    /*
    Constructor for request, takes in the users profile string
     */
    public ProfileRequest(String userName, String accountNumber) {
        this.userName = userName;
        this.accountNumber = accountNumber;
    }

    /*
    Allows us to get our response after it has come through.
     */
    public ProfileResponse getResponse() {
        return response;
    }

    /*
    Sends request to API
     */
    public void sendRequest() {
        Thread sendRequestToAPI = new Thread(new Runnable() {
            @Override
            public void run() {
                downloadAndLoadJSON();
            }
        });
        sendRequestToAPI.start();
    }

    /*
    Builds string for request URL.
     */
    private String buildURLForRequest() {
        return region + accountNumber + "/1/" + userName + "/";
    }

    private void downloadAndLoadJSON() {
        ByteArrayOutputStream baos = URLConnector.readBytes(buildURLForRequest());
    }
}
