package demo.httpconnection.com.httpconnectionsamples;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by linhao on 16/3/29.
 */
public class FileDownLoadActivity extends Activity {
    private Button btn;
    private TextView textView;
    private int count = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int result = msg.what;
            count += result;
            if (count == 3) {
                textView.setText("downLoad success");
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.down);
        btn = (Button) findViewById(R.id.btn_down);
        textView = (TextView) findViewById(R.id.showText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        DownLoad load = new DownLoad(handler);
                        load.downLoadFile("http://10.30.248.51:8080/WebSamples/a.jpg");
                    }
                }.start();

            }
        });
    }
}
