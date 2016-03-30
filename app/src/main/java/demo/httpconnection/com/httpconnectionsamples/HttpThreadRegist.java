package demo.httpconnection.com.httpconnectionsamples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by linhao on 16/3/23.
 */
public class HttpThreadRegist extends Thread {
     String url;
     String name;
     String age;

    public HttpThreadRegist(String url, String name, String age){
        this.url = url;
        this.name = name;
        this.age = age;
    }
    private void doGet(){
        url = url+"?name="+name+"&age="+ age;
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sb = new StringBuffer();
            while((str=br.readLine())!=null)
            {
                sb.append(str);
            }
            System.out.println("result:"+sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void doPost(){
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            OutputStream out = conn.getOutputStream();
            String content = "name="+name+"&age="+age;
            out.write(content.getBytes());
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sb = new StringBuffer();
            while((str=br.readLine())!=null)
            {
                sb.append(str);
            }
            System.out.println("result:" + sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
//        doGet();
        doPost();
    }
}
