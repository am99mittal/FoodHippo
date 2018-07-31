package com.example.am99m.foodhippo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Rating extends AppCompatActivity {

    RatingBar r1;
    float r2;
    Button btn1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        r1= (RatingBar) findViewById(R.id.ratingBar); // initiate a rating bar
        btn1=(Button)findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r2=r1.getRating();
                Toast.makeText(getApplicationContext(),"selected rating is:"+r2,Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Thanks for rating our app",Toast.LENGTH_LONG).show();

            }
        });
    }
}
