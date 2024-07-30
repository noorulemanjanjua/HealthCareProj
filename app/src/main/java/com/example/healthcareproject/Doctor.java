package com.example.healthcareproject;

import static com.example.healthcareproject.R.id.*;
import static com.example.healthcareproject.R.id.all;
import static com.example.healthcareproject.R.id.c;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Doctor extends BaseActivity {
    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

t1=findViewById(R.id.textViewapp);
        t2=findViewById(R.id.textView12);

        LinearLayout layoutcardio=findViewById(R.id.c);
        layoutcardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    Intent it=new Intent(Doctor.this,DoctorDetailsActivity.class);
                it.putExtra("title","Cardiologist");
                startActivity(it);
            }
        });
        LinearLayout layoutpulmo=findViewById(R.id.p);
        layoutpulmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Doctor.this,DoctorDetailsActivity.class);
                it.putExtra("title","Pulmonologist");
                startActivity(it);
            }
        });
        LinearLayout layoutneuro=findViewById(R.id.n);
        layoutneuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent it=new Intent(Doctor.this,DoctorDetailsActivity.class);
               it.putExtra("title","Neurologist");
               startActivity(it);

            }
        });


    }
}