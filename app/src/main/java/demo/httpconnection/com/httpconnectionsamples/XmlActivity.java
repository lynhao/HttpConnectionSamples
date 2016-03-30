package demo.httpconnection.com.httpconnectionsamples;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by linhao on 16/3/28.
 */
public class XmlActivity extends Activity {
    private TextView textView;

    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myxml);
        textView = (TextView) findViewById(R.id.tv);
        String url = "http://192.168.254.81:8080/WebSamples/girls.xml";
        XmlThread thread = new XmlThread(url,handler,textView);
        thread.start();
    }
}
