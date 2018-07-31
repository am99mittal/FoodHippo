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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class address extends AppCompatActivity {

    EditText e1;
    Button b1;
    FirebaseDatabase database;
    DatabaseReference reference;
    String orderItems;
    String userMobile;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        e1=(EditText)findViewById(R.id.et1);
        b1=(Button)findViewById(R.id.btn1);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Orders");
        orderItems=getIntent().getStringExtra("items");
        SQLiteDatabase sqLiteDatabase=this.openOrCreateDatabase("rec",MODE_APPEND,null);
        Cursor c=sqLiteDatabase.rawQuery("Select * from login;",null);
        c.moveToLast();
        userMobile=c.getString(0);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                artist art = new artist(orderItems, userMobile, e1.getText().toString());
                reference.child(userMobile).setValue(art);
                Toast.makeText(getApplicationContext(),"congratulations your order is successfully placed",Toast.LENGTH_LONG).show();
                Intent intent3=new Intent(getApplicationContext(),Navigation.class);
                startActivity(intent3);


            }
        });
    }
}
