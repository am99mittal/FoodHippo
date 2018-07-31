package com.example.am99m.foodhippo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView tv1,tv2,tv3;
    Button btn1,btn2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.t1);
        tv2=(TextView)findViewById(R.id.t2);
        tv3=(TextView)findViewById(R.id.t3);
        btn1=(Button)findViewById(R.id.b1);
        btn2=(Button)findViewById(R.id.b2);
       try {
           SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("rec", MODE_APPEND, null);
           Cursor cursor = sqLiteDatabase.rawQuery("Select * from login", null);
           cursor.moveToLast();
           if (!cursor.getString(0).equals("")) {
               Intent intent = new Intent(getApplicationContext(), Navigation.class);
               startActivity(intent);
               finish();
           }
       }catch(Exception e){
           Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);


       }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),Login.class);
                startActivity(i1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplication(),Register.class);
                startActivity(i2);
            }
        });

    }
}