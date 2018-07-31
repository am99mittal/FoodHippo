package com.example.am99m.foodhippo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Password extends AppCompatActivity {

    EditText et1,et2;
    Button btn1;
    String password;
    FirebaseDatabase database;
    DatabaseReference reference;
    artist art;
    String a1,a2;
    SQLiteDatabase sqLiteDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        btn1=(Button)findViewById(R.id.b1);
        et1=(EditText)findViewById(R.id.e1);
        et2=(EditText)findViewById(R.id.e2);
        Bundle bu=getIntent().getExtras();
        final String name=bu.getString("name");
        final String mobile=bu.getString("mobile");

        //oast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),mobile,Toast.LENGTH_LONG).show();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1=et1.getText().toString();
                a2=et2.getText().toString();
                Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),mobile,Toast.LENGTH_LONG).show();

                if(a1.equals(a2))
                {
                    Toast.makeText(getApplicationContext(),"match",Toast.LENGTH_LONG).show();
                    password=et1.getText().toString();
                    database=FirebaseDatabase.getInstance();
                    reference=database.getReference("users").child(mobile);
                    art=new artist(name,mobile,password);
                    reference.setValue(art);
                    Intent i6 = new Intent(getApplicationContext(),Navigation.class);
                    startActivity(i6);
                    try {
                        sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
                        sqLiteDatabase.execSQL("CREATE table if not exists login(mobile VARCHAR);");
                        sqLiteDatabase.execSQL("INSERT into login values('" + mobile + "');");
                    }catch (Exception o){
                        Toast.makeText(getApplicationContext(),o.getMessage(),Toast.LENGTH_LONG).show();
                    }


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Does not match",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
