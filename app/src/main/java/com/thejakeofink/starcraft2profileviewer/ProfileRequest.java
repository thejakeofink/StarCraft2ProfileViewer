package com.thejakeofink.starcraft2profileviewer;

import android.os.Handler;
import android.os.Message;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by Jacob Stokes on 9/8/14.
 */
public class ProfileRequest {
    private static final String region = "http://us.battle.net/api/sc2/profile/";

    protected String userName;
    protected String accountNumber;
    protected ProfileResponse response;
    protected Handler handler;

    /*
    Constructor for request, takes in the users profile string
     */
    public ProfileRequest(String userName, String accountNumber, Handler handler) {
        this.userName = userName;
        this.accountNumber = accountNumber;
        this.handler = handler;
        response = new ProfileResponse();
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
                Message message = Message.obtain(handler, ProfileActivity.RESPONSE_RETRIEVED);
                handler.sendMessage(message);
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

        if (baos != null) {
            String json = baos.toString();

            try {
                JSONObject root = new JSONObject(json);

                response.setResponse(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
