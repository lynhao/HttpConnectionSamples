package demo.httpconnection.com.httpconnectionsamples;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;

/**
 * Created by linhao on 16/3/29.
 */
public class UploadActivity extends Activity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        btn = (Button) findViewById(R.id.upfile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.30.205.145:8080/Upload/Upload";
                File file = Environment.getExternalStorageDirectory();
                File fileAbs = new File(file,"a.jpg");
                String fileName =fileAbs.getAbsolutePath() ;
                UpLoadThread upLoadThread = new UpLoadThread(url,fileName);
                upLoadThread.start();
            }
        });

    }
}
