package com.example.layouts_example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);
        gridView = findViewById(R.id.grid_view);
        ArrayList<ProductModel> list = new ArrayList<>();

        list.add(new ProductModel("samsung s24", R.drawable.s));
        list.add(new ProductModel("realme 60 pro 5g", R.drawable.r));
        list.add(new ProductModel("iphone 15", R.drawable.i));
        list.add(new ProductModel("oppo reno 11", R.drawable.oppo));
        list.add(new ProductModel("vivo v40", R.drawable.vivo));


        GridViewAdapter adapter = new GridViewAdapter(this, list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0)
                {
                    Intent i = new Intent(ProductActivity.this,RelativeActivity.class);
                    i.putExtra("img","s");
                    i.putExtra("name","samsung s24");
                    startActivity(i);
                } else if (position == 1)
                {
                    Intent i = new Intent(ProductActivity.this,RelativeActivity.class);
                    i.putExtra("img","r");
                    i.putExtra("name","realme 60 pro 5g");
                    startActivity(i);

                } else {
                    Toast.makeText(ProductActivity.this, "Here is no Activity :)", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}