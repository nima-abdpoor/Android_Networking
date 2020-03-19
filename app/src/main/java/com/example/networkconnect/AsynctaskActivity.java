package com.example.networkconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AsynctaskActivity extends AppCompatActivity {

    Button startbtn;
    Button getdatabtn;
    EditText giveURL;
    public static TextView showresult;
    public int timerequests=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        SetViews();
    }

    public void reset() {
        showresult.setText("");
    }

    private void SetViews() {
        giveURL =findViewById(R.id.setURL);
        startbtn =findViewById(R.id.start);
        showresult=findViewById(R.id.text);
        getdatabtn=findViewById(R.id.getdate);
        showresult.setMovementMethod(new ScrollingMovementMethod());
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerequests++;
               new Asyncetask()
          //             .execute("bank","kala","salam");
                         .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"bank","kala","salam");
            }
        });
        getdatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                AsyncTaskforgettingdata getinfofromnet=new AsyncTaskforgettingdata(giveURL.getText().toString());
                getinfofromnet.execute();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("RESET").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                reset();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public class Asyncetask extends AsyncTask<String ,String ,String>{
        int thisrequest;
        @Override
        protected void onPreExecute()
        {
            thisrequest=timerequests;
            showresult.append("#"+timerequests+"-start ... \n");
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            for (String p:
                 params) {
                publishProgress("working with : "+p);
            }
            result = sleeponsecondperparams(params.length);
            return result;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            showresult.append(values[0]+"\n");
        }

        @Override
        protected void onPostExecute(String result) {
            showresult.append(result+"\n");
        }
        private String sleeponsecondperparams(int numbers) {
            for(int i=0;i<numbers;++i) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "#"+thisrequest+"-task finished";
            }
        }
    }



