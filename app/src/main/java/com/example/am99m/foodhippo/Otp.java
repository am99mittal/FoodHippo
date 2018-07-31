package com.example.am99m.foodhippo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Otp extends AppCompatActivity {

    EditText e1;
    Button btn1;
    String check;
    SQLiteDatabase sqLiteDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        e1=(EditText)findViewById(R.id.et1);
        btn1=(Button)findViewById(R.id.b1);
        Bundle b=getIntent().getExtras();
        //final String match=b.getString("op");
        final String name=b.getString("name");
        final String mobile=b.getString("mobile");
        //Toast.makeText(this,match,Toast.LENGTH_LONG).show();
        sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
        final Cursor c = sqLiteDatabase.rawQuery("Select * from otp", null);
        c.moveToLast();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=e1.getText().toString();
                if(check.equals(c.getString(0)))
                {
                    Toast.makeText(getApplicationContext(),"Match",Toast.LENGTH_LONG).show();
                    Intent i5=new Intent(getApplicationContext(),Password.class);
                    Bundle bund=new Bundle();
                    bund.putString("name",name);
                    bund.putString("mobile",mobile);
                    i5.putExtras(bund);
                    startActivity(i5);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"does not Match",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
