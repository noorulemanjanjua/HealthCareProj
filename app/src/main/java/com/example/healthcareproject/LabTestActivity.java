package com.example.healthcareproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {
    private String[][] packages=
            {
                    {"package 1:full body checkup","","","","999"},
                    {"pacakge 2:blood glucose fasting","","","","299"},
                    {"package 3:covid19 antibody","","","","899"},
                    {"package 4:Thyroid check","","","","499"},
                    {"package 5:immunity check","","","","699"}

            };
    private String[] package_details= {
            "blood glucose fasting\n"+
                    "complete hamogram\n"+
                    "HbA1c\n"+
                    "iron studies\n"+
                    "kidney function test\n"+
                    "LDH lactate dehydrogenase, serum\n"+
                    "Lipid profile\n"+
                    "Liver function test\n",
            "blood glucose fasting","covid19 antibody","thyroid profile_total(T3,T4,TSH Ultra sensitive)",
            "complete hemogram\n"+
                    "CRP(C Reactive Protien),quantative,serum\n"+
                    "iron studies\n"+
                    "kidney function test\n"+
                    "Vitamin D  total 25 hydroxy\n"+
                    "Liver Function test\n"+
                    "Lipid Profile\n"
    };
    HashMap<String,String>item;
    ArrayList List;
    Button btnGoToCart,btnBack;
    ListView listview;
    SimpleAdapter sa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);
        btnGoToCart=findViewById(R.id.buttonBMcartcheckout);
        btnBack=findViewById(R.id.CartbuttonBMback);
        listview=findViewById(R.id.listviewBMcart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( LabTestActivity.this,HomeActivity.class));
            }
        });
        List=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total cost:"+packages[i][4]+"/-");
            List.add(item);
        }
        sa=new SimpleAdapter(this,List,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listview.setAdapter(sa);


       listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it=new Intent(LabTestActivity.this,LabTestDetail.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));
            }
        });

}}