package com.example.healthcareproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import java.util.HashMap;

public class DoctorDetailsActivity extends BaseActivity {
    private String[][] ddetails1={{"Doctor Name:Mehtab","Hosp Address:Islamabad","Experience:5years","Mobile No:12345678987","1000"},
            {"Doctor Name:Shehiryar","Hosp Address:Rawalpindi","Experience:3years","Mobile No:123456789879","800"},
            {"Doctor Name:Javeria","Hosp Address:Islamabad","Experience:3years","Mobile No:12345678988","1000"},
            {"Doctor Name:Areeba","Hosp Address:Rawalpindi","Experience:6years","Mobile No:12345678986","1200"},
            {"Doctor Name:AbdulRehman","Hosp Address:Islamabad","Experience:5years","Mobile No:12345678985","1000"}};
    private String[][] ddetails2={{"Doctor Name:Ibrahim","Hosp Address:Islamabad","Experience:5years","Mobile No:62345678987","1000"},
            {"Doctor Name:Zeeshan","Hosp Address:Lahore","Experience:6years","Mobile No:22345678987","1200"},
            {"Doctor Name:Pervez","Hosp Address:Karachi","Experience:2years","Mobile No:32345678987","1000"},
            {"Doctor Name:Irum","Hosp Address:Islamabad","Experience:3years","Mobile No:42345678987","1000"},
            {"Doctor Name:Esha","Hosp Address:Rawalpindi","Experience:2years","Mobile No512345678987","1000"}};
    private String[][] ddetails3={{"Doctor Name:Yasir","Hosp Address:Rawalpindi","Experience:5years","Mobile No:12345678987","1000"},
            {"Doctor Name:Meher Bano","Hosp Address:Islamabad","Experience:7years","Mobile No:15445678987","12200"},
            {"Doctor Name:Ayesha","Hosp Address:Lahore","Experience:5years","Mobile No:92345678307","1000"},
            {"Doctor Name:Sumaiya","Hosp Address:Karachi","Experience:2years","Mobile No:82345634987","1000"},
            {"Doctor Name:Arsalan","Hosp Address:Islamabad","Experience:3years","Mobile No:53629578987","1000"}};
       TextView tv;
    Button btn;
    String[][] ddetails={};
    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv=findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.buttonDDback);

        String title = getIntent().getStringExtra("title");

// Update UI with the new data
        tv.setText(title);
        /*Intent it=getIntent();
        String title= it.getStringExtra("title ");
        tv.setText(title);*/
        if(title.compareTo("Cardiologist")==0)
            ddetails=ddetails1;
        else

        if(title.compareTo("Pulmonologist")==0)
            ddetails=ddetails2;
        else


            ddetails=ddetails3;




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, Doctor.class));

            }
        });
        list=new ArrayList();
        for(int i=0;i<ddetails.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",ddetails[i][0]);
            item.put("line2",ddetails[i][1]);
            item.put("line3",ddetails[i][2]);
            item.put("line4",ddetails[i][3]);
            item.put("line5","Cons Fees:"+ddetails[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        ListView lst=findViewById(R.id.listViewdd);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it=new Intent(DoctorDetailsActivity.this, BookAppointment.class);
                it.putExtra("text1",title);

                it.putExtra("text2",ddetails[i][0]);
                it.putExtra("text3",ddetails[i][1]);
                it.putExtra("text4",ddetails[i][3]);
                it.putExtra("text5",ddetails[i][4]);
                startActivity(it);
            }
        });
    }
}