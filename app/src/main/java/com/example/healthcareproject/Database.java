/*package com.example.healthcareproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper{

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1="create table users(username text,email text,password text)";
        sqLiteDatabase.execSQL(qry1);
        String qry2="create table cart(username text,product text,price float,otype text)";
        sqLiteDatabase.execSQL(qry2);
        String qry3="create table orderplace(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        sqLiteDatabase.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void register(String username,String email,String password){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String username,String password){
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from users where username=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }
    public void addCart(String username,String product,float price,String otype){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }
    public int checkCart(String username,String product){
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from cart where username=? and product=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }
    public void removeCart(String username,String otype){

        String str[]=new String[2];
        str[0]=username;
        str[1]=otype;
        SQLiteDatabase db=getReadableDatabase();
        db.delete("cart","username=? and otype=?",str);
        db.close();
        }
    public ArrayList getCartData(String username,String otype){
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String str[]=new String[2];
        str[0]=username;
        str[1]=otype;

        Cursor c=db.rawQuery("select * from cart where username=? and otype=?",str);
        if(c.moveToFirst()){
          do{
              String product=c.getString(1);
              String price=c.getString(2);
              arr.add(product+"$"+price);
          }
          while(c.moveToNext());
        }
        db.close();
        return arr;
    }
    public void addOrder(String username,String fullname,String address,String contact,int pincode,String date,String time,float price,String otype){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("product",fullname);
        cv.put("address",address);
        cv.put("contactno",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",price);
        cv.put("otype",otype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }
    public ArrayList getOrderData(String username){
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String str[]=new String[1];
        str[0]=username;


        Cursor c=db.rawQuery("select * from orderplace where username=? ",str);
        if(c.moveToFirst()){
            do{

                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));

            }
            while(c.moveToNext());
        }
        db.close();
        return arr;
    }
}*/


package com.example.healthcareproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class Database extends SQLiteOpenHelper {

    private static final String TAG = "Database";
    private static final String COLUMN_ID = "id"; // No need to change this
    private static final String COLUMN_DOCTOR_NAME = "doctor_name";
    private static final String COLUMN_DOCTOR_ADDRESS = "doctor_address";
    private static final String COLUMN_DOCTOR_CONTACT = "doctor_contact";
    private static final String COLUMN_DOCTOR_FEES = "doctor_fees";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_CONTACT = "user_contact";
    private static final String TABLE_APPOINTMENTS = "appointments";

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "CREATE TABLE users(username TEXT, email TEXT, password TEXT)";
        String qry2 = "CREATE TABLE cart(username TEXT, product TEXT, price REAL, otype TEXT)";
        String qry3 = "CREATE TABLE orderplace(username TEXT, fullname TEXT, address TEXT, contactno TEXT, pincode INTEGER, date TEXT, time TEXT, amount REAL, otype TEXT)";
        String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE " + TABLE_APPOINTMENTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DOCTOR_NAME + " TEXT,"
                + COLUMN_DOCTOR_ADDRESS + " TEXT,"
                + COLUMN_DOCTOR_CONTACT + " TEXT,"
                + COLUMN_DOCTOR_FEES + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_TIME + " TEXT,"
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_CONTACT + " TEXT" + ")";

        sqLiteDatabase.execSQL(qry1);
        sqLiteDatabase.execSQL(qry2);
        sqLiteDatabase.execSQL(qry3);
        sqLiteDatabase.execSQL(CREATE_APPOINTMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade as needed
    }

    public void register(String username, String email, String password) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("username", username);
            cv.put("email", email);
            cv.put("password", password);
            SQLiteDatabase db = getWritableDatabase();
            db.insert("users", null, cv);
            db.close();
        } catch (Exception e) {
            Log.e(TAG, "Error registering user", e);
        }
    }

    public int login(String username, String password) {
        int result = 0;
        try {
            String[] str = new String[]{username, password};
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", str);
            if (c.moveToFirst()) {
                result = 1;
            }
            c.close();
            db.close();
        } catch (Exception e) {
            Log.e(TAG, "Error logging in", e);
        }
        return result;
    }

    public void addCart(String username, String product, float price, String otype) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                ContentValues cv = new ContentValues();
                cv.put("username", username);
                cv.put("product", product);
                cv.put("price", price);
                cv.put("otype", otype);
                SQLiteDatabase db = getWritableDatabase();
                db.insert("cart", null, cv);
                db.close();
                Log.d(TAG, "Item added to cart: " + product);
            } catch (Exception e) {
                Log.e(TAG, "Error adding to cart", e);
            }
        });
    }

    public int checkCart(String username, String product) {
        int result = 0;
        try {
            String[] str = new String[]{username, product};
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM cart WHERE username=? AND product=?", str);
            if (c.moveToFirst()) {
                result = 1;
            }
            c.close();
            db.close();
        } catch (Exception e) {
            Log.e(TAG, "Error checking cart", e);
        }
        return result;
    }

    public void removeCart(String username, String otype) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                String[] str = new String[]{username, otype};
                SQLiteDatabase db = getWritableDatabase();
                db.delete("cart", "username=? AND otype=?", str);
                db.close();
                Log.d(TAG, "Items removed from cart for user: " + username);
            } catch (Exception e) {
                Log.e(TAG, "Error removing from cart", e);
            }
        });
    }

    public ArrayList<String> getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            String[] str = new String[]{username, otype};
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM cart WHERE username=? AND otype=?", str);
            if (c.moveToFirst()) {
                do {
                    String product = c.getString(1);
                    String price = c.getString(2);
                    arr.add(product + "$" + price);
                } while (c.moveToNext());
            }
            c.close();
            db.close();
        } catch (Exception e) {
            Log.e(TAG, "Error getting cart data", e);
        }
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                ContentValues cv = new ContentValues();
                cv.put("username", username);
                cv.put("fullname", fullname);
                cv.put("address", address);
                cv.put("contactno", contact);
                cv.put("pincode", pincode);
                cv.put("date", date);
                cv.put("time", time);
                cv.put("amount", price);
                cv.put("otype", otype);
                SQLiteDatabase db = getWritableDatabase();
                db.insert("orderplace", null, cv);
                db.close();
                Log.d(TAG, "Order added for user: " + username);
            } catch (Exception e) {
                Log.e(TAG, "Error adding order", e);
            }
        });
    }

    public ArrayList<String> getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            String[] str = new String[]{username};
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE username=?", str);
            if (c.moveToFirst()) {
                do {
                    arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$" + c.getString(4) + "$" + c.getString(5) + "$" + c.getString(6) + "$" + c.getString(7) + "$" + c.getString(8));
                } while (c.moveToNext());
            }
            c.close();
            db.close();
        } catch (Exception e) {
            Log.e(TAG, "Error getting order data", e);
        }
        return arr;
    }
    /////

    public boolean insertAppointment(String doctorName, String doctorAddress, String doctorContact, String doctorFees,
                                     String date, String time, String userName, String userContact) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCTOR_NAME, doctorName);
        values.put(COLUMN_DOCTOR_ADDRESS, doctorAddress);
        values.put(COLUMN_DOCTOR_CONTACT, doctorContact);
        values.put(COLUMN_DOCTOR_FEES, doctorFees);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_USER_NAME, userName);
        values.put(COLUMN_USER_CONTACT, userContact);
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TABLE_APPOINTMENTS, null, values);
        //return result != -1;
        db.close();
        return result!=-1;
    }

    public Cursor getAppointments() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id AS _id, doctor_name, doctor_address, doctor_contact, doctor_fees, date, time, user_name, user_contact FROM " + TABLE_APPOINTMENTS;
        return db.rawQuery(query, null);
    }

}


