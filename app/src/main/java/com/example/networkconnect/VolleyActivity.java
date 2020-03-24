package com.example.networkconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyActivity extends AppCompatActivity {
    TextView textresult;
    ImageView image;
    public static String URL="https://google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        setviewparams();
    }


    private void setviewparams() {
        textresult =findViewById(R.id.respons);
        image = findViewById(R.id.Image);
    }

    public void volleylib() {
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        visibility(false);
                        textresult.setText("Response is: "+response.substring(0,500));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textresult.setText("That didn't work!");
                        visibility(true);
                    }
                }
        );
        queue.add(request);
    }

    public void LoadImage() {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        String uri="https://androidnimatest.000webhostapp.com/fuck.jpg";
        ImageRequest request=new ImageRequest(
                uri,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        image.setImageBitmap(response);
                        visibility(true);
                    }
                }
                , 497, 542,
                ImageView.ScaleType.FIT_CENTER,
                Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        visibility(false);
                        Toast.makeText(VolleyActivity.this,"Turn Your Fucking VPN On",Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(request);
    }
    public void visibility(Boolean bool){
        if (bool == true) {
            image.setVisibility(View.VISIBLE);
            textresult.setVisibility(View.INVISIBLE);
        } else if (bool == false) {
            image.setVisibility(View.INVISIBLE);
            textresult.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Image").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                LoadImage();
                return false;
            }
        });
        menu.add("Request").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                volleylib();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
