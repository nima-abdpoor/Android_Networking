package com.example.networkconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getdate(View view) {
        String tag = (String) view.getTag();
        switch (tag){
            case "1":
                startActivity(new Intent(MainActivity.this,getdatafrominternet.class));
                break;
            case "2":
                startActivity(new Intent(MainActivity.this,AsynctaskActivity.class));
                break;
            case "3":
                startActivity(new Intent(MainActivity.this,Httprequest.class));
                break;
            case "4":
                startActivity(new Intent(MainActivity.this,VolleyActivity.class));
                break;
            default:break;
        }
    }
}
