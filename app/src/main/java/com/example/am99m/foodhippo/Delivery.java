package com.example.am99m.foodhippo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Delivery extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    String mobile,items,address;
    TextView t1,t2,t3,t4,t5,t6,t7;
    Button btn1;
    Cursor c;

    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_delivery);
            t1 = (TextView) findViewById(R.id.hey);
            t2 = (TextView) findViewById(R.id.mobile1);
            t3 = (TextView) findViewById(R.id.order);
            t4 = (TextView) findViewById(R.id.items1);
            t5 = (TextView) findViewById(R.id.delivery);
            t6 = (TextView) findViewById(R.id.address1);
            t7 = (TextView) findViewById(R.id.reach);
            btn1 = (Button) findViewById(R.id.b1);
        try
        {

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("rec", MODE_APPEND, null);

            c = sqLiteDatabase.rawQuery("Select * from login;", null);
            c.moveToLast();
        }
        catch(Exception ae)
        {
            Toast.makeText(getApplicationContext(),ae.getMessage(),Toast.LENGTH_LONG).show();
        }

            database = FirebaseDatabase.getInstance();
            reference = database.getReference("delivery").child(c.getString(0));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChildren()) {
                        mobile = dataSnapshot.getValue(artist.class).getMobile();
                        items = dataSnapshot.getValue(artist.class).getName();
                        address = dataSnapshot.getValue(artist.class).getPassword();
                        t2.setText(mobile);
                        t4.setText(items);
                        t6.setText(address);
                        t1.setText("Hey ");
                        t3.setText("Your Order ");
                        t5.setText("Has Been Delivered To: ");
                        t7.setText("And Will Reach You With In 20 Minutes.");

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            btn1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    reference.removeValue(new DatabaseReference.CompletionListener() {

                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            Toast.makeText(getApplicationContext(), "Successfully Removed", Toast.LENGTH_LONG).show();
                            Intent intent4=new Intent(getApplicationContext(),Navigation.class);
                            startActivity(intent4);


                        }
                    });

                }
            });

    }
}
