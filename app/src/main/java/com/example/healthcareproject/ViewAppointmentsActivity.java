package com.example.healthcareproject;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAppointmentsActivity extends AppCompatActivity {


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);

        listView = findViewById(R.id.appointments_list);
        //dbHelper = new Database(this);
        //Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        loadAppointments();
    }

    private void loadAppointments() {
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        Cursor cursor = db.getAppointments();
        String[] from = new String[] {
                "doctor_name", "doctor_address", "doctor_contact", "doctor_fees", "date", "time", "user_name", "user_contact"
        };
        int[] to = new int[] {
                R.id.doctor_name, R.id.doctor_address, R.id.doctor_contact, R.id.doctor_fees, R.id.date, R.id.time, R.id.user_name, R.id.user_contact
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, R.layout.appointment_item, cursor, from, to, 0);

        listView.setAdapter(adapter);
    }


}
