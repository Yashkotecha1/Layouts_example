package com.example.layouts_example;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RelativeActivity extends AppCompatActivity {

    ImageView img_id;

    TextView txt_discription;
    Button btn_addtocart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_relative);

        FindViewByID();
        Body();

    }



    private void FindViewByID() {
        img_id = findViewById(R.id.img_id);
        txt_discription = findViewById(R.id.txt_discription);
        btn_addtocart = findViewById(R.id.btn_addtocart);

    }

    @SuppressLint("SetTextI18n")
    private void Body()
    {

        Intent i = getIntent();

        txt_discription.setText(R.string.productdiscription);

        btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}