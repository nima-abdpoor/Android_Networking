package com.example.networkconnect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public  class Requestdata {

    private String URL="";
    private String method ="Get";
    private Map<String ,String> params;

    public Requestdata(){
        this("","Get");
    }

    public Requestdata(String uri,String method){
        this.URL=uri;
        this.method=method;
        params =new HashMap<>();
    }
    public String getURL() {
        return URL;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    public void setparameters(String key,String value){
        this.params.put(key,value);
    }
    public String getencodeparams(){
        StringBuilder sb=new StringBuilder();
        for (String key:params.keySet() ) {
            String value=params.get(key);
            if (sb.length() > 0) sb.append("&");
            sb.append(key);
            sb.append("=");
            try {
                sb.append(URLEncoder.encode(value,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
