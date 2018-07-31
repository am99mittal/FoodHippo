package com.example.am99m.foodhippo;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText e1,e2;
    Button btn1;
    String name,mobile;
    FirebaseDatabase database;
    DatabaseReference reference;
    artist art;
    int max=9999, min=1000;
    String otp;
    int ot;
    SQLiteDatabase sqLiteDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        e1=(EditText)findViewById(R.id.et1);
        e2=(EditText)findViewById(R.id.et2);
        btn1=(Button)findViewById(R.id.b1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    name = e1.getText().toString();
                    mobile = e2.getText().toString();
                    ot = (int) Math.floor(Math.random() * (max - min + 1) + min);
                    otp = String.valueOf(ot);
                    sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
                    sqLiteDatabase.execSQL("CREATE table if not exists otp(otp VARCHAR);");
                    sqLiteDatabase.execSQL("INSERT into otp values('" + otp + "');");
                    Intent i = new Intent(getApplicationContext(), Otp.class);
                    //Toast.makeText(getApplicationContext(), otp, Toast.LENGTH_LONG).show();
                    Bundle bundle = new Bundle();
                    //bundle.putString("op", otp);
                    bundle.putString("name", name);
                    bundle.putString("mobile", mobile);
                    i.putExtras(bundle);

                    PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, i, 0);
                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(mobile, null, otp, pi, null);

                    //Intent i4=new Intent(getApplicationContext(),Otp.class);
                    //database=FirebaseDatabase.getInstance();
                    //reference=database.getReference("users");
                    //art=new artist(name,mobile);
                    //reference.child(mobile).setValue(art);
                }
                catch(Exception ae)
                {
                    Toast.makeText(getApplicationContext(),ae.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
