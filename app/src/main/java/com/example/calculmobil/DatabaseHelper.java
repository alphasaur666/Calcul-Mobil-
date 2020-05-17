package com.example.calculmobil;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "calculmobil.db";
    public static final String TABLE_NAME = "transactions_table";
    public static final String COL_1 = "TransactionID";
    public static final String COL_2 = "PaymentName";
    public static final String COL_3 = "PaymentDate";
    public static final String COL_4 = "PaymentType";
    public static final String COL_5 = "Amount";
    public static final String COL_6 = "Sender";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME + " (TransactionID INTEGER PRIMARY KEY AUTOINCREMENT, PaymentName TEXT, PaymentType TEXT, Amount INTEGER, Sender TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);


    }


    public boolean insertData(String PaymentName, String PaymentDate, String PaymentType, String Amount, String Sender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2 , PaymentName);
        contentValues.put(COL_3 , PaymentDate);
        contentValues.put(COL_4 , PaymentType);
        contentValues.put(COL_5 , Amount);
        contentValues.put(COL_6 , Sender);
        long result = db.insert(TABLE_NAME , null , contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}
