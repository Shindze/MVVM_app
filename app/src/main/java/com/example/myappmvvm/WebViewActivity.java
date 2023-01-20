
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

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

}
