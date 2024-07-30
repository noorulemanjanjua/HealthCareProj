package com.example.healthcareproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class BuyMedicineDetailsActivity extends AppCompatActivity {
    TextView tvpackagename,tvTotalCost;
    EditText edDetails;
    Button btnback,btnAddtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine_details);
        tvpackagename=findViewById(R.id.textviewBMDpackagename);
        tvTotalCost=findViewById(R.id.textviewBMDTotalCost);
        edDetails=findViewById(R.id.editTextBMDTextMultLine);
        edDetails.setKeyListener(null);
        btnback=findViewById(R.id.buttonBMDback);
        btnAddtocart=findViewById(R.id.buttonBMDAddToCart);
        Intent intent=getIntent();
        tvpackagename.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("totalcost:"+intent.getStringExtra("text3")+"/-");
        btnback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });
        btnAddtocart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=tvpackagename.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"product already added",Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(), "record inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                }
            }
        });
    }
}