package com.example.am99m.foodhippo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText e1,e2;
    Button btn1;
    String mobile,password;
    FirebaseDatabase database;
    DatabaseReference reference;
    String mb,pass;
    SQLiteDatabase sqLiteDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        e1=(EditText)findViewById(R.id.et1);
        e2=(EditText)findViewById(R.id.et2);
        btn1=(Button)findViewById(R.id.b1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mobile = e1.getText().toString();
                    password = e2.getText().toString();
                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("users");
                    reference.child(e1.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChildren())
                            {


                                mb = dataSnapshot.getValue(artist.class).getMobile();
                                pass = dataSnapshot.getValue(artist.class).getPassword();
                                if (mb.equals(mobile) && pass.equals(password)) {
                                    try {
                                        sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
                                        sqLiteDatabase.execSQL("CREATE table if not exists login(mobile VARCHAR);");
                                        sqLiteDatabase.execSQL("INSERT into login values('" + mobile + "');");
                                    } catch (Exception o) {
                                        Toast.makeText(getApplicationContext(), o.getMessage(), Toast.LENGTH_LONG).show();
                                    }


                                    Intent i1 = new Intent(getApplicationContext(), Navigation.class);
                                    startActivity(i1);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Entries are incorrect", Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);

                                builder.setTitle("Alert Dialog");

                                builder.setMessage("Incorrect mobile no.\nHave you Registerd?");
                                builder.setIcon(R.drawable.off);



                                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(),"Re-enter you mobile and password",Toast.LENGTH_LONG).show();

                                    }
                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(),"Register First",Toast.LENGTH_LONG).show();

                                    }
                                }).show();
                            }

                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });





            }
        });

    }
}
