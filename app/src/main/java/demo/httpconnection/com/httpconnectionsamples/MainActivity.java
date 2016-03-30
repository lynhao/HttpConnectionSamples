package demo.httpconnection.com.httpconnectionsamples;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private WebView webView;
    private Handler mHandle = new Handler();
    //加载图片
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        mImageView = (ImageView) findViewById(R.id.imageView);

        new HttpThread("http://h.hiphotos.baidu.com/image/pic/item/6c224f4a20a446239e8d311c9b22720e0cf3d70d.jpg",mImageView,mHandle)
                .start();
//        new HttpThread("http://www.baidu.com",webView,mHandle)
//                .start();

    }
}
