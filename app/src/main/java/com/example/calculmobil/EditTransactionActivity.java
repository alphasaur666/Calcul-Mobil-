package com.example.calculmobil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditTransactionActivity extends AppCompatActivity {

    Button buttonEdit;
    EditText editDate;
    Calendar MyCalendar;
    int day, month, year;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edittransaction_activity);
        editDate = (EditText)findViewById(R.id.editText4);
        MyCalendar = Calendar.getInstance();
        day = MyCalendar.get(Calendar.DAY_OF_MONTH);
        month = MyCalendar.get(Calendar.MONTH);
        year = MyCalendar.get(Calendar.YEAR);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditTransactionActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        editDate.setText(dayOfMonth+"/"+month+"/"+year);

                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        buttonEdit = findViewById(R.id.buttonSave);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(EditTransactionActivity.this ,ViewTransactionActivity.class);
                startActivity(startIntent);
            }
        });
    }
}
