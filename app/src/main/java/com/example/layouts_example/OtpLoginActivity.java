package com.example.layouts_example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OtpLoginActivity extends AppCompatActivity {

    TextView txt_otp_txt;
    EditText edt_otp;
    Button btn_otp;

    Intent intent;

    String otp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_login);
        FindViewByID();
        Body();
    }

    private void Body()
    {
        intent = getIntent();
       otp = intent.getStringExtra("otp");
        System.out.println("otp enter : "+otp);
        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otp.equals(edt_otp.getText().toString()))
                {
                    intent = new Intent(OtpLoginActivity.this, ProductActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(OtpLoginActivity.this, "Enter Valid Otp !!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void FindViewByID() {
        txt_otp_txt = findViewById(R.id.txt_otp_txt);
        edt_otp = findViewById(R.id.edt_otp);
        btn_otp = findViewById(R.id.btn_otp);
    }
}