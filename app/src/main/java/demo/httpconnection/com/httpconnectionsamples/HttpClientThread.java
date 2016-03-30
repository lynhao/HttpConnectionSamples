package demo.httpconnection.com.httpconnectionsamples;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by linhao on 16/3/24.
 */
public class HttpClientThread extends Thread {
    private String name;
    private String age;

    private String url;

    public HttpClientThread(String url) {
        this.url = url;
    }

    public HttpClientThread(String url, String name, String age) {
        this.url = url;
        this.name = name;
        this.age = age;
    }

    private void doHttpClientGet() {
        HttpGet httpGet = new HttpGet(url);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        try {
            //发送请求对象
            response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //如果服务器返回信息Ok，则取出取出服务器返回的数据
                String content = EntityUtils.toString(response.getEntity());
                System.out.println("content=" + content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doHttpClientPost() {
        HttpPost httpPost = new HttpPost(url);
        HttpClient httpClient = new DefaultHttpClient();
        //通过NameValuePair去存取数据
        ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("name", name));
        list.add(new BasicNameValuePair("age", age));
        HttpResponse response;
        try {
            //设置要发送的数据
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            //发送请求对象
            response = httpClient.execute(httpPost);
            //判断类型
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //如果服务器返回信息Ok，则取出取出服务器返回的数据
                String content = EntityUtils.toString(response.getEntity());
                System.out.println("content=" + content);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
//        doHttpClientGet();
        doHttpClientPost();
    }
}
