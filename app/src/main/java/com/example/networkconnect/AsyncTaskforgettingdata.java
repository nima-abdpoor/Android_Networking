package com.example.networkconnect;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskforgettingdata extends AsyncTask <String ,String ,String>{
    String urladdress ="";
    AsyncTaskforgettingdata(String s){
        urladdress =s;
    }
    @Override
    protected void onPreExecute() {
        AsynctaskActivity.showresult.append("starting ... \n");
    }

    @Override
    protected String doInBackground(String...params) {
       // return getdatafromapache(urladdress);
       return getdatehttpclient(urladdress);
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            AsynctaskActivity.showresult.append(result);
        }
        catch (NullPointerException ex){
            ex.getMessage();
        }
    }

    @Deprecated
    private String getdatafromapache(String URL) {
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


    private String getdatehttpclient(String uri) {
        try {
            URL urlo=new URL(uri);
            HttpURLConnection connection= (HttpURLConnection) urlo.openConnection();
            String result= Utils.inputstreamtoString(connection.getInputStream());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
