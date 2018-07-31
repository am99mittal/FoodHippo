package com.example.am99m.foodhippo;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class Myadapter extends RecyclerView.Adapter<Myadapter.CustomHolder> {

    ArrayList<String> list;
    ArrayList<String> prices;

    Context context;
    StorageReference storageReference= FirebaseStorage.getInstance().getReference("Product");

    public Myadapter(Context context, ArrayList<String> list,ArrayList<String> prices){
        this.context=context;
        this.list=list;
        this.prices=prices;


    }

    @Override
    public Myadapter.CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.abcd,parent,false);

        CustomHolder customHolder=new CustomHolder(view);
        return customHolder;
    }

    @Override
    public void onBindViewHolder(final Myadapter.CustomHolder holder, int position) {

        storageReference.child(list.get(position)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(holder.imageView);
            }
        });

        holder.t.setText(list.get(position));
        holder.price1.setText(prices.get(position));



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder{

        TextView t,mrp1,price1;
        ImageView imageView;
        Button b;
        public CustomHolder(final View itemView) {
            super(itemView);
            t=(TextView)itemView.findViewById(R.id.t2);
            mrp1=(TextView)itemView.findViewById(R.id.mrp);
            price1=(TextView)itemView.findViewById(R.id.price);
            imageView=(ImageView)itemView.findViewById(R.id.i2);
            b=(Button)itemView.findViewById(R.id.button2);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context c=itemView.getContext();
                    try {
                        Toast.makeText(c, "Added to my Orders", Toast.LENGTH_LONG).show();
                        SQLiteDatabase sqLiteDatabase = c.openOrCreateDatabase("rec", c.MODE_APPEND, null);
                        sqLiteDatabase.execSQL("Create table if not exists ord1(items VARCHAR,price VARCHAR);");
                        sqLiteDatabase.execSQL("Insert into ord1(items,price) values('" + t.getText() + "','"+price1.getText()+"');");
                    }catch(Exception e){
                        Toast.makeText(c,e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                }
            });

        }
    }
}
