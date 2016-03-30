package demo.httpconnection.com.httpconnectionsamples;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by linhao on 16/3/29.
 */
public class UpLoadThread extends Thread {
    private String fileName;
    private String url;

    public UpLoadThread(String url,String fileName){
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        String boundary="----WebKitFormBoundarymOVZPKVlej8t4zbU";
        String prefix="--";
        String end="\r\n";
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type","multipart/form-data; boundary="+boundary);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(prefix+boundary+end);
            out.writeBytes("Content-Disposition: form-data;"+"name=\"file\"; filename=\""+"a.jpg"+"\""+end);
            out.writeBytes(end);
            FileInputStream in = new FileInputStream(new File(fileName));
            byte[] b = new byte[1024*4];
            int len;
            while ((len = in.read(b))!= -1){
                out.write(b,0,len);
            }
            out.writeBytes(end);
            out.writeBytes(prefix + boundary + prefix + end);
            out.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str=reader.readLine())!=null){
                sb.append(str);
            }
            System.out.println("respose="+sb.toString());
            if (out!=null){
                out.close();
            }
            if (reader!=null){
                reader.close();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
