
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myappmvvm.MainActivity;

public class WebViewActivity<ActivityWebViewBinding> extends AppCompatActivity {

    private ActivityWebViewBinding mBinding;
    private String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityWebViewBinding.inflate(getLayoutInflater());

        if(getIntent().getExtras().get("url") != null){
            url = getIntent().getExtras().get("url").toString();
        }
        else {
            url = "https://oauth.vk.com/authorize?client_id=51479314" +
                    "https://oauth.vk.com/blank.html&display=mobile";
        }

        mBinding.webView.loadUrl(url);
        mBinding.webView.setWebViewClient(ServiceLocator.getInstance().getVK_API().getAuth().oauth2Vk(this));
        setContentView(mBinding.getRoot());
        System.out.println(mBinding.webView.getUrl());
    }

    public WebViewClient oauth2Vk(WebViewActivity activity){
        return new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if(request.getUrl().toString().contains("https://oauth.vk.com/blank.html#")){
                    Log.e(TAG, "Response " + request.getUrl().toString());
                    String token = Uri.parse(request.getUrl().toString().replace("#", "?")).getQueryParameter("access_token");
                    String email = Uri.parse(request.getUrl().toString().replace("#", "?")).getQueryParameter("email");
                    Log.e(TAG, "Access token " + email);
                    DataModel user = ServiceLocator.getInstance().getCurrentUser();
                    user.setId(token);
                    user.setEmail(email);
                    if(token != null){
                        Intent intent = new Intent(activity, MainActivity.class);
                        intent.putExtra("token", token);

                        activity.startActivity(intent);
                    }
                    return false;
                }
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        };
    }
}
