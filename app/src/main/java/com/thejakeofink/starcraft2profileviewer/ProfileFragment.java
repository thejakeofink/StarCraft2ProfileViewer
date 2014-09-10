package com.thejakeofink.starcraft2profileviewer;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    public static final int RESPONSE_RETRIEVED = 1337;

    TextView textView;

    private ProfileRequest pRequest;

    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RESPONSE_RETRIEVED:
                    ProfileResponse pResponse = null;
                    if (pRequest != null) {
                        pResponse = pRequest.getResponse();
                    }
                    if (pResponse != null && textView != null) {
                        textView.setText(pResponse.getResponse().toString());
                    }
                    break;
            }
        }
    };

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        pRequest = new ProfileRequest("thejakeofink", "346353", mhandler);

        pRequest.sendRequest();

        textView = (TextView)rootView.findViewById(R.id.text_dump);
        return rootView;
    }
}