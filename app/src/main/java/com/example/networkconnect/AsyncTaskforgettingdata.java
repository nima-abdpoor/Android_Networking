package com.example.networkconnect;

import android.os.AsyncTask;
import android.text.Editable;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class AsyncTaskforgettingdata extends AsyncTask <String ,String ,String>{
    String URL="";
    AsyncTaskforgettingdata(String s){
        URL=s;
    }
    @Override
    protected void onPreExecute() {
        AsynctaskActivity.showresult.append("starting ... \n");
    }

    @Override
    protected String doInBackground(String...params) {
        return getdate(URL);
    }

    @Override
    protected void onPostExecute(String result) {
        AsynctaskActivity.showresult.append(result);
    }

    private String getdate(String URL) {
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
