package demo.httpconnection.com.httpconnectionsamples;

import android.os.Handler;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhao on 16/3/28.
 */
public class XmlThread extends Thread {
    private String url;

    private TextView textView;
    private Handler handler;

    public XmlThread(String url,Handler handler,TextView textView){
        this.url = url;
        this.handler = handler;
        this.textView = textView;
    }
    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            InputStream in = conn.getInputStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(in,"UTF-8");
            int eventType = parser.getEventType();
            final List<Girl> list = new ArrayList<Girl>();
            Girl girl = null;
            while (eventType!= XmlPullParser.END_DOCUMENT){
                String data = parser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if ("girl".equals(data)){
                            girl = new Girl();
                        }
                        if ("name".equals(data)){
                            girl.setName(parser.nextText());
                        }
                        if ("age".equals(data)){
                            girl.setAge(Integer.valueOf(parser.nextText()));
                        }
                        if ("school".equals(data)){
                            girl.setSchool(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("girl".equals(data) && girl != null){
                            list.add(girl);
                        }
                        break;

                }
                eventType = parser.next();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                textView.setText(list.toString());
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


    }
}
