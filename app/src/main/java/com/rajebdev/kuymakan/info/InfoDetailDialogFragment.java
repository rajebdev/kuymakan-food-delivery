package com.rajebdev.kuymakan.info;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.rajebdev.kuymakan.R;

import java.util.Objects;

public class InfoDetailDialogFragment extends DialogFragment {

    static String TAG = "FullScreenDialog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_fragment_info_detail, container, false);

        // Tombol Close
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });


        // Set Url Web view
        final WebView webView = view.findViewById(R.id.web_info);
        ProgressBar progressBar = view.findViewById(R.id.web_progress_bar);
        webView.setWebViewClient(new myWebclient(progressBar));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://portofolio.rajebdev.xyz");

        // Button KeyCode Back to back web view
        Objects.requireNonNull(getDialog()).setOnKeyListener(new DialogInterface.OnKeyListener()
        {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                switch(keyCode)
                {
                    case KeyEvent.KEYCODE_BACK:
                        if ((webView.getUrl().equals(webView.getOriginalUrl()))) {
                            dismiss();
                        } else {
                            webView.goBack();
                        }
                        break;
                    case KeyEvent.ACTION_DOWN:
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            Objects.requireNonNull(dialog.getWindow()).setLayout(width, height);
        }
    }

    public static class myWebclient extends WebViewClient {
        private ProgressBar bar;

        myWebclient(ProgressBar progressBar) {
            bar = progressBar;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            bar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}