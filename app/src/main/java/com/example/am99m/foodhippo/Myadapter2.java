package com.example.am99m.foodhippo;

import android.content.Context;
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


public class Myadapter2 extends RecyclerView.Adapter<Myadapter2.CustomHolder> {

    ArrayList<String> list,prices;

    Context context;
    StorageReference storageReference= FirebaseStorage.getInstance().getReference("Product");

    public Myadapter2(Context context, ArrayList<String> list,ArrayList<String> prices){
        this.context=context;
        this.list=list;
        this.prices=prices;


    }

    @Override
    public Myadapter2.CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter,parent,false);

        CustomHolder customHolder=new CustomHolder(view);
        return customHolder;
    }

    @Override
    public void onBindViewHolder(final Myadapter2.CustomHolder holder, int position) {
        storageReference.child(list.get(position)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(holder.imageView);
            }
        });

        holder.t.setText(list.get(position));
        holder.t1.setText(prices.get(position));




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder{

        TextView t,t1,t2;

        ImageView imageView;
        public CustomHolder(final View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            t=(TextView)itemView.findViewById(R.id.textView);
            t1=(TextView)itemView.findViewById(R.id.price);
            t2=(TextView)itemView.findViewById(R.id.mrp);

        }
    }
}
