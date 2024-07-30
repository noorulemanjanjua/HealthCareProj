package com.example.healthcareproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
import java.util.regex.Pattern;

public class OrderActivity extends AppCompatActivity {

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        btn = findViewById(R.id.buttonODBack);
        lst = findViewById(R.id.listViewOD);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderActivity.this, HomeActivity.class));

            }

        });
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "").toString();
        ArrayList dbData = db.getOrderData(username);

        String[][] order_details = new String[dbData.size()][];
        for (int i = 0; i < order_details.length; i++) {
            order_details[i] = new String[5];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(Pattern.quote("$"));
            order_details[i][0] = strData[0];
            order_details[i][1] = strData[1];
            if (strData[7].compareTo("medicine") == 0) {
                order_details[i][3] = "Del:" + strData[4];
            } else {
                order_details[i][3] = "Del:" + strData[4] + " " + strData[5];

            }
            order_details[i][2] = "Rs." + strData[6];
            order_details[i][4] = strData[7];
        }
        list = new ArrayList();
        for (String[] orderDetail : order_details) {
            item = new HashMap<String, String>();
            item.put("Line1", orderDetail[0]);
            item.put("Line2", orderDetail[1]);
            item.put("Line3", orderDetail[2]);
            item.put("Line4", orderDetail[3]);
            item.put("Line5", orderDetail[4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_line,
                new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);


    Button viewAppointmentsButton = findViewById(R.id.view_appointments_button);
viewAppointmentsButton.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        Intent intent = new Intent(OrderActivity.this, ViewAppointmentsActivity.class);
        startActivity(intent);
    }
    });
}
}