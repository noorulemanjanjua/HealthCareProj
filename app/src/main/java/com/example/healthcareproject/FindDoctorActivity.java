package com.example.healthcareproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        CardView back =findViewById(R.id.cardBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });
        CardView familyPhysician=findViewById(R.id.cardFDFamilyPhysician);
        familyPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title","Family Physician");
                startActivity(intent);
            }
        });

        CardView dietitian=findViewById(R.id.cardfdDietitian);
        dietitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Dietitian");
            }
        });
        CardView dentist=findViewById(R.id.cardfdDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Dentist");
            }
        });
        CardView surgeon=findViewById(R.id.cardfdSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Surgeon");
            }
        });

        CardView cardiologist=findViewById(R.id.cardfdCardiologist);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Cardiologist");
            }
        });



    }
}