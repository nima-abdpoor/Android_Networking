package com.example.networkconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsynctaskActivity extends AppCompatActivity {

    Button startbtn;
    TextView showresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        SetViews();
    }

    private void SetViews() {
        startbtn =findViewById(R.id.start);
        showresult=findViewById(R.id.text);
        //showresult.setMovementMethod(new ScrollingMovementMethod());
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new Asyncetask().execute();
            }
        });
    }

    public class Asyncetask extends AsyncTask<String ,String ,String>{
        @Override
        protected void onPreExecute() {
            showresult.append("start\n");
        }

        @Override
        protected String doInBackground(String... params) {
            sleeponsecondperparams(params.length);
            return "task finished";
        }

        @Override
        protected void onPostExecute(String result) {
            showresult.append(result+"\n");
        }
        private void sleeponsecondperparams(int numbers) {
            for(int i=0;i<numbers;++i) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        }


    }



