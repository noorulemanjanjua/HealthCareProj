package com.example.healthcareproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {
    private String[][] health_details = {
            {"Walking Daily", "", "", "", "Click More Details"},
            {"Home care of COVID-19", "", "", "", "Click More Details"},
            {"Stop smoking", "", "", "", "Click More Details"},
            {"Menstrual Cramps", "", "", "", "Click More Details"},
            {"Healthy Gut", "", "", "", "Click More Details"},
    };

    private int[] images = {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5,
    };

    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    View btnBack;
    ListView lst;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        lst = findViewById(R.id.listViewHA);
        btnBack = findViewById(R.id.buttonHADBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList<>();
        for (String[] health_detail : health_details) {
            item = new HashMap<>();
            item.put("line1", health_detail[0]);
            item.put("line2", health_detail[1]);
            item.put("line3", health_detail[2]);
            item.put("line4", health_detail[3]);
            item.put("line5", health_detail[4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_line,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(HealthArticlesActivity.this, HealthArticlesDetailsActivity.class);
                it.putExtra("text1", health_details[i][0]);
                it.putExtra("text2", images[i]);
                startActivity(it);
            }
        });
    }
}
