package eskool.com.eskoolapp.StayAhead;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by user on 6/26/2017.
 */

public class MyWebViewClient extends WebViewClient {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return true;
    }
}
