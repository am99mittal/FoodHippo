package com.example.am99m.foodhippo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {

    RecyclerView rc;
    Button b,b1;
    ArrayList<String> items,price;
    Myadapter2 adapter2;
    artist1 art;
    FirebaseDatabase database;
    DatabaseReference reference,ref1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        rc=(RecyclerView)findViewById(R.id.rv1);
        b=(Button)findViewById(R.id.btn2);
        b1=(Button)findViewById(R.id.btn3);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Orders");

        items=new ArrayList<>();
        price=new ArrayList<String>();
        try
        {
            database = FirebaseDatabase.getInstance();
            ref1 = database.getReference("Product");
            rc.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
            items.clear();
            price.clear();
            Cursor c = sqLiteDatabase.rawQuery("Select * from ord1", null);
            c.moveToFirst();

            items.add(c.getString(0));
            price.add(c.getString(1));
            while (c.moveToNext()) {
                items.add(c.getString(0));

                price.add(c.getString(1));

            }

            Toast.makeText(getApplicationContext(), items.toString(), Toast.LENGTH_LONG).show();
            adapter2 = new Myadapter2(getApplicationContext(), items, price);
            rc.setAdapter(adapter2);
        }
        catch (Exception ae)
        {
            Toast.makeText(getApplicationContext(),ae.getMessage(),Toast.LENGTH_LONG).show();
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AlertDialog.Builder builder=new AlertDialog.Builder(Orders.this);
                    builder.setTitle("Confirm");
                    builder.setMessage("Sure to order?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String addr = "";
                                Intent intent=new Intent(getApplicationContext(), address.class);
                            intent.putExtra("items",String.valueOf(items));



                                SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
                                sqLiteDatabase.execSQL("Delete from ord1;");
                            startActivity(intent);




                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                    //Intent i9=new Intent(getApplicationContext(),Orders.class);
                    //startActivity(i9);
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),Orders.class);
                SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
                sqLiteDatabase.execSQL("Delete from ord1;");
                startActivity(intent1);

            }
        });


    }
}
