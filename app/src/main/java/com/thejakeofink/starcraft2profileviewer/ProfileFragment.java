package com.thejakeofink.starcraft2profileviewer;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    public static final String ARG_PLANET_NUMBER = "planet_number";
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

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) rootView.getLayoutParams();
        lp.setMargins(0, lp.topMargin + getStatusBarHeight(), 0, 0);
        rootView.setLayoutParams(lp);

        return rootView;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}