package demo.httpconnection.com.httpconnectionsamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by linhao on 16/3/23.
 */
public class RegistActivity extends Activity {
    private EditText name;
    private EditText age;
    private Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        register_btn = (Button) findViewById(R.id.btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.30.204.24:8080/WebSamples/MyServlet";
//              new HttpThreadRegist(url,name.getText().toString(),age.getText().toString()).start();
//                url = url + "?name=" + name.getText().toString() + "&age=" + age.getText().toString();
//                new HttpClientThread(url).start();
                new HttpClientThread(url,name.getText().toString(),age.getText().toString()).start();
            }
        });

    }
}
