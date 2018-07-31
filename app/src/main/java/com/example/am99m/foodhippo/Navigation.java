package com.example.am99m.foodhippo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    ArrayList<String> arrayList,price;
    artist1 art;
    Myadapter myadapter;
    FirebaseDatabase database;
    DatabaseReference reference,ref1;
    Uri u;
    String name;
    TextView et1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            View header=navigationView.getHeaderView(0);
            et1 = (TextView)header.findViewById(R.id.name);
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("rec", MODE_APPEND, null);

            Cursor c = sqLiteDatabase.rawQuery("Select * from login;", null);
            c.moveToLast();

            database = FirebaseDatabase.getInstance();
            reference = database.getReference("users").child(c.getString(0));

        }
        catch (Exception ae) {
            Toast.makeText(getApplicationContext(),ae.getMessage(),Toast.LENGTH_LONG).show();

        }
            reference.addValueEventListener(new ValueEventListener() {

                public void onDataChange(DataSnapshot dataSnapshot) {
                    name = dataSnapshot.getValue(artist.class).getName();
et1.setText(name);

                    //et1.setText(name);
                }




                public void onCancelled(DatabaseError databaseError) {

                }
            });


        recyclerView=(RecyclerView)findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        arrayList=new ArrayList<String>();
        price=new ArrayList<String>();
        database=FirebaseDatabase.getInstance();
        ref1=database.getReference("Product");
        ref1.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                price.clear();
                arrayList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    art=snapshot.getValue(artist1.class);
                    price.add(art.getPrice());
                    arrayList.add(art.getName());
                }

                //arrayList.clear();
                //arrayList.add("Brownie");
                //arrayList.add("Burger");
                //arrayList.add("French fries");
                //arrayList.add("Ice cream");
                //arrayList.add("Parantha");
                //arrayList.add("Pizza");


                myadapter=new Myadapter(getApplicationContext(),arrayList,price);
                recyclerView.setAdapter(myadapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
           // public void onClick(View view) {
          //      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //            .setAction("Action", null).show();
        //    }
        //});

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            try {
                SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
                sqLiteDatabase.execSQL("Delete from login;");
            }catch(Exception e){
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }


            Intent i7=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i7);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.order) {
            Intent i8=new Intent(getApplicationContext(),Orders.class);
            startActivity(i8);


        } else if (id == R.id.nav_gallery) {
            Intent i11=new Intent(getApplicationContext(),Gallery.class);
            startActivity(i11);

        } else if (id == R.id.nav_slideshow) {
            Intent i10=new Intent(getApplicationContext(),Contact.class);
            startActivity(i10);

        } else if (id == R.id.delivery) {

                Intent i12 =new Intent(getApplicationContext(),Delivery.class);
                startActivity(i12);

        }
        else if (id == R.id.rate) {
            Intent i13=new Intent(getApplicationContext(),Rating.class);
            startActivity(i13);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
