package com.rosu.razvan.CalculMobil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "CalculMobil.db";
    public static final String TABLE_NAME = "transactions_table";
    public static final String COL_1 = "TransactionID";
    public static final String COL_2 = "PaymentName";
    public static final String COL_3 = "PaymentType";
    public static final String COL_4 = "Amount";
    public static final String COL_5 = "PaymentDate";
    public static final String COL_6 = "Sender";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_2  + " TEXT, " +
                        COL_3 + " TEXT, " +
                        COL_4 + " TEXT, " +
                        COL_5 + " INTEGER, " +
                        COL_6 + " TEXT);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addTransaction(String PaymentName, String PaymentType, int Amount, String PaymentDate, String Sender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_2, PaymentName);
        cv.put(COL_3, PaymentType);
        cv.put(COL_4, Amount);
        cv.put(COL_5, PaymentDate);
        cv.put(COL_6, Sender);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String PaymentName, String PaymentType, String Amount, String PaymentDate, String Sender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_2, PaymentName);
        cv.put(COL_3, PaymentType);
        cv.put(COL_4, Amount);
        cv.put(COL_5, PaymentDate);
        cv.put(COL_6, Sender);

        long result = db.update(TABLE_NAME, cv, "TransactionID=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "TransactionID=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
