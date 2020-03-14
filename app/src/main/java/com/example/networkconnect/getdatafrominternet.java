package com.example.networkconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class getdatafrominternet extends AppCompatActivity {

    public static String URL ="https://www.wikipedia.org/";
    TextView tv;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdatafrominternet);
        tv =findViewById(R.id.textView);
        tv.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Get").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Thread threadforgettingdate=new Thread(new Runnable() {
                     @SuppressLint("HandlerLeak")
                     Handler handler=new Handler(){
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            String content= (String) msg.getData().get("content");
                            tv.setText(content);
                        }
                    };

                    @Override
                    public void run() {
                        String content = getdate();
                        Message msg=new Message();
                        Bundle bundle=new Bundle();
                        bundle.putString("content",content);
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    }
                });
                threadforgettingdate.start();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private String getdate() {
        HttpClient httpClient=new DefaultHttpClient();
        HttpGet method=new HttpGet(URL);
        try {
            HttpResponse response =httpClient.execute(method);
            String content= Utils.inputstreamtoString(response.getEntity().getContent());
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return "null";
        }
    }
}
