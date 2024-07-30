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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String [][] packages={
            {"uprise-D3 1000IU capsule","","","","50"},
            {"Healthhvit chromium picolinate 200mcg capsule","","","","305"},
            {"vitamin B complex capsule","","","","448"},
            {"Inlife vitamin E wheat germ oil Capsule ","","","","539"},
            {"Dolo 650 Tablet","","","","30"},
            {"Crocin 650  Advanced Tablet","","","","50"},
            {"Strepsils medicated Lozenges for sore throat ","","","","40"},
            {"Tata 1mg calcium+vitamin 03","","","","40"},
            {"feronia -XT Tablet","","","","130"},
    };
    private String[] Package_details={
            "Building and keeping the bones & teeth strong\n"+
                    "Reducing Fatigue/stress and muscular pains\n"+
                    "boosting immunity and increasing resistance against infections\n ",
            "Provide relief from vitamin B deficincies\n"+
                    "Help in formation of red blood cells\n"+
                    "Maintain healthy nervous system\n",
            "it promotes health as well as skin benefit\n"+
                    "it help reduce skin blemish and pigmentation\n"+
                    "it act as safeguard the skin from harsh UVA and UVB sunrays\n",
            "help relieve fever and bring down high temoerature \n",
            "suitable for people  with heart condition or high blood pressure "
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnback,btngoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine);
        lst=findViewById(R.id.listviewBM);
        btnback=findViewById(R.id.buttonBMback);
        btngoToCart=findViewById(R.id.buttonBMcartcheckout);

        btngoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total cost:"+packages[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",Package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
    }
}