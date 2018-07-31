package com.example.am99m.foodhippo;

import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Gallery extends AppCompatActivity {

    ImageView i1,i2,i3,i4,i5,i6;
    StorageReference ref;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        i1=(ImageView)findViewById(R.id.imageView1);
        i2=(ImageView)findViewById(R.id.imageView2);
        i3=(ImageView)findViewById(R.id.imageView3);
        i4=(ImageView)findViewById(R.id.imageView4);
        i5=(ImageView)findViewById(R.id.imageView5);
        i6=(ImageView)findViewById(R.id.imageView6);
        ref= FirebaseStorage.getInstance().getReference("Product");
        ref.child("Burger").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(i1);
            }
        });
        ref.child("Brownie").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(i2);
            }
        });
        ref.child("French fries").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(i3);
            }
        });
        ref.child("Parantha").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(i4);
            }
        });
        ref.child("Pizza").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(i5);
            }
        });
        ref.child("Ice cream").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(i6);
            }
        });
    }
}
