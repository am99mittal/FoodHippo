package com.example.am99m.foodhippo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Contact extends AppCompatActivity {

    TextView tv1,tv2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        tv1=(TextView)findViewById(R.id.t1);
        tv2=(TextView)findViewById(R.id.t2);
    }
}
