package com.example.healthcareproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;





import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

        public class HealthArticlesDetailsActivity extends AppCompatActivity {
            TextView tv1;
            ImageView img;
            Button btnBack;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_health_articles_details);
                btnBack= findViewById(R.id.buttonHADBack);
                tv1= findViewById(R.id.textViewHADTitle);
                img= findViewById(R.id.imageView2HAD);

                Intent intent=getIntent();
                tv1.setText(((Intent) intent).getStringExtra("text1"));
                Bundle bundle= getIntent().getExtras();
                if(bundle!=null){
                    int resId=bundle.getInt("text2");
                    img.setImageResource(resId);
                }
                btnBack.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        startActivity(new Intent(HealthArticlesDetailsActivity.this, HealthArticlesDetailsActivity.class));
                    }
                });


            }
        }




