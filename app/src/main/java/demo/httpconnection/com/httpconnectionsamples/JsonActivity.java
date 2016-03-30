package demo.httpconnection.com.httpconnectionsamples;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

/**
 * Created by linhao on 16/1/3.
 */
public class JsonActivity extends Activity {
    private ListView listView;

    private JsonAdapter adapter;
//    private Context context;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new JsonAdapter(this);
        String url = "http://10.10.16.16:8080/WebSamples/JsonServerServlet";
        new HttpJson(url,this,listView,adapter,handler).start();
    }
}
