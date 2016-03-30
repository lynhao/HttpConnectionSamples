package demo.httpconnection.com.httpconnectionsamples;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by linhao on 16/3/28.
 */
public class DownLoad {
    private Handler handler;
    public DownLoad(Handler handler){
        this.handler = handler;
    }
    //创建线程池
    private Executor threadPool = Executors.newFixedThreadPool(3);

    static class DownLoadRunnable implements Runnable {
        private String url;
        private String fileName;
        private long start;
        private long end;
        private Handler handler;

        public DownLoadRunnable(String url,String fileName,long start,long end,Handler handler){

            this.url = url;
            this.fileName = fileName;
            this.start = start;
            this.end =end;
            this.handler = handler;
        }

        @Override
        public void run() {
            try {
                URL httpUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
                conn.setReadTimeout(5000);
                conn.setRequestProperty("Range","bytes="+start+"-"+end);
                conn.setRequestMethod("GET");
                RandomAccessFile access = new RandomAccessFile(new File(fileName), "rwd");
                access.seek(start);
                InputStream in = conn.getInputStream();
                byte[] b = new byte[1024 * 4];
                int len;
                while ((len = in.read(b)) != -1) {
                    access.write(b, 0, len);
                }
                if (access != null) {
                    access.close();
                }
                if (in != null) {
                    in.close();
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void downLoadFile(String url) {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            int count = conn.getContentLength();
            int block = count / 3;

            String fileName = getFileName(url);
            File parent = Environment.getExternalStorageDirectory();//下载到SD卡
            File FileDownLoad = new File(parent,fileName);

            /**
             * 11/3 3 ..2
             * 第一个线程 0-2
             * 第二个线程 3-5
             * 第三个线程 6-10
             */
            for (int i =0;i<3;i++){
                long start = i * block;
                long end = (i+1)*block-1;
                if (i==2){
                    end = count;
                }
                DownLoadRunnable runnable = new DownLoadRunnable(url,FileDownLoad.getAbsolutePath(),start,end,handler);
                threadPool.execute(runnable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getFileName(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }
}
