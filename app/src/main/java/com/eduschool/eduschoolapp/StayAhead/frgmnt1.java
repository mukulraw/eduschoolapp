package com.eduschool.eduschoolapp.StayAhead;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.eduschool.eduschoolapp.R;

/**
 * Created by user on 6/26/2017.
 */

public class frgmnt1 extends Fragment {

    WebView web;
    ProgressDialog progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.stay_ahead_frgmnt1, container, false);
        progress = new ProgressDialog(getActivity());



        web = (WebView) view.findViewById(R.id.web);
        //web.loadUrl("https://en.wikipedia.org/wiki/Main_Page");

        // Enable Javascript
        //WebSettings webSettings = web.getSettings();
        //webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        web.setWebViewClient(new WebViewClient());
        startWebView("https://en.wikipedia.org/wiki/Main_Page");


        return view;
    }

    private void startWebView(String url) {

        WebSettings settings = web.getSettings();

        settings.setJavaScriptEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setLoadWithOverviewMode(true);

        progress = new ProgressDialog(getActivity());
        progress.setMessage("Loading...");
        progress.show();

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progress.isShowing()) {
                    progress.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });
        web.loadUrl(url);
    }

}
