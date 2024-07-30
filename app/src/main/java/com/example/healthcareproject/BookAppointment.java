package com.example.healthcareproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity {
    EditText e1, e2, e3, e4, eUserName, eUserContact;
    TextView t1;
    private DatePickerDialog dpd;
    private TimePickerDialog tpd;
    private Button d, tb, book;
    private Database dbHelper;
    private String selectedDate, selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_appointment);

        t1 = findViewById(R.id.textView2);
        e1 = findViewById(R.id.appname);
        e2 = findViewById(R.id.appaddress);
        e3 = findViewById(R.id.appcontact);
        e4 = findViewById(R.id.appfees);
        eUserName = findViewById(R.id.user_name);
        eUserContact = findViewById(R.id.user_contact);
        d = findViewById(R.id.buttonappdate);
        tb = findViewById(R.id.buttonapptime);
        book = findViewById(R.id.buttonbookappointment);

        e1.setKeyListener(null);//not editable, just display all details
        e2.setKeyListener(null);
        e3.setKeyListener(null);
        e4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");
        t1.setText(title);
        e1.setText(fullname);
        e2.setText(address);
        e3.setText(contact);
        e4.setText("Cons Fees" + fees + "/-");

        initDatePicker();
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show();
            }
        });

        initTimePicker();
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tpd.show();
            }
        });



        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bookAppointment();
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dsl = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                selectedDate = i2 + "/" + i1 + "/" + i;
                d.setText(selectedDate);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        dpd = new DatePickerDialog(this, style, dsl, year, month, day);
        dpd.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener tsl = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                selectedTime = i + ":" + i1;
                tb.setText(selectedTime);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        tpd = new TimePickerDialog(this, style, tsl, hrs, mins, true);
    }

    private void bookAppointment() {
        String doctorName = e1.getText().toString();
        String doctorAddress = e2.getText().toString();
        String doctorContact = e3.getText().toString();
        String doctorFees = e4.getText().toString();
        String userName = eUserName.getText().toString();
        String userContact = eUserContact.getText().toString();

        if (userName.isEmpty() || userContact.isEmpty() || selectedDate == null || selectedTime == null) {
            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show();
        } else {
            Database db=new Database(getApplicationContext(),"healthcare",null,1);
            boolean isInserted = db.insertAppointment(doctorName, doctorAddress, doctorContact, doctorFees,
                    selectedDate, selectedTime, userName, userContact);
            if (isInserted) {
                Toast.makeText(this, "Appointment booked successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to book appointment", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
