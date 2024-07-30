package com.example.healthcareproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {
    HashMap<String,String> item;
    ArrayList List;
    SimpleAdapter sa;
    ListView lst;
    TextView tvtotal;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private  String[][] Packages={};
    private Button dateButton,btnCheckout,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_buy_medicine);
        dateButton=findViewById(R.id.ButtonBMCartdate);
        btnCheckout=findViewById(R.id.buttonBMcartcheckout);
        btnBack=findViewById(R.id.CartbuttonBMback);
        tvtotal=findViewById(R.id.textviewBMcart);
        lst=findViewById(R.id.listviewBMcart);
        SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username= sharedPreferences.getString("username","").toString();

        Database db=new Database(getApplicationContext(),"healthcare",null,1);

        float totalamount=0;
        ArrayList dbData=db.getCartData(username,"lab");
        Toast.makeText(getApplicationContext(), ""+dbData, Toast.LENGTH_SHORT).show();

        Packages =new String[dbData.size()][];
        for(int i=0;i<Packages.length;i++){
            Packages[i]=new String[5];
        }
        for(int i=0;i<dbData.size();i++){
            String arrData=dbData.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
            Packages[i][0]=strData[0];
            Packages[i][4]="cost:"+strData[1]+"/-";
            totalamount =totalamount+Float.parseFloat(strData[1]);
        }

        tvtotal.setText("totalcost:"+totalamount);
        List=new ArrayList();
        for(int i=0;i<Packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",Packages[i][0]);
            item.put("line2",Packages[i][1]);
            item.put("line3",Packages[i][2]);
            item.put("line4",Packages[i][3]);
            item.put("line5",Packages[i][4]);
            List.add(item);

        }
        sa=new SimpleAdapter(this,List,
                R.layout.multi_line,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        lst.setAdapter(sa);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartBuyMedicineActivity.this,BuyMedicineActivity.class));
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent it =new Intent(CartBuyMedicineActivity.this,BuyMedicineBookActivity.class);
                it.putExtra("price",tvtotal.getText());
                it.putExtra("date",dateButton.getText());
                startActivity(it);
            }
        });
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });}
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }





}