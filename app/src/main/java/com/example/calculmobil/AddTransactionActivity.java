package com.example.calculmobil;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddTransactionActivity extends AppCompatActivity {

    Button buttonAdd;
    EditText dateText, paymentName, paymentType, amount, sender ;
    Calendar mCurrentDate;
    int day, month, year;
    DatabaseHelper myDb;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtransaction_activity);
        myDb = new DatabaseHelper(this);



        dateText = (EditText)findViewById(R.id.editText4);
        mCurrentDate = Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);

        paymentName = (EditText)findViewById(R.id.editText3);
        paymentType = (EditText)findViewById(R.id.editText5);
        amount = (EditText)findViewById(R.id.editText6);
        sender = (EditText)findViewById(R.id.editText8);




        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTransactionActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        dateText.setText(dayOfMonth+"/"+month+"/"+year);

                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted  = myDb.insertData(paymentName.getText().toString(),
                        paymentType.getText().toString(),
                        dateText.getText().toString(),
                        amount.getText().toString(),
                        sender.getText().toString());
                Intent startIntent = new Intent(AddTransactionActivity.this ,ViewTransactionActivity.class);
                startActivity(startIntent);
            }
        });
    }

}
