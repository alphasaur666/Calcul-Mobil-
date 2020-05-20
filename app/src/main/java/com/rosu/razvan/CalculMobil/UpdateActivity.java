package com.rosu.razvan.CalculMobil;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rosu.razvan.CalculMobil.R;

public class UpdateActivity extends AppCompatActivity {

    EditText paymentName_input, paymentType_input, paymentAmount_input, paymentDate_input, paymentSender_input;
    Button update_button, delete_button;

    String paymentId, paymentName, paymentType, paymentAmount, paymentDate, paymentSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        paymentName_input = findViewById(R.id.payment_name_input2);
        paymentType_input = findViewById(R.id.payment_type_input2);
        paymentAmount_input = findViewById(R.id.payment_amount_input2);
        paymentDate_input = findViewById(R.id.payment_date_input2);
        paymentSender_input = findViewById(R.id.payment_sender_input2);

        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(paymentName);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                paymentName = paymentName_input.getText().toString().trim();
                paymentType = paymentType_input.getText().toString().trim();
                paymentAmount = paymentAmount_input.getText().toString().trim();
                paymentDate = paymentDate_input.getText().toString().trim();
                paymentSender = paymentSender_input.getText().toString().trim();
                myDB.updateData(paymentId, paymentName, paymentType, paymentAmount, paymentDate, paymentSender);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("type") && getIntent().hasExtra("amount")
        && getIntent().hasExtra("date") && getIntent().hasExtra("sender")) {
            //Getting Data from Intent
            paymentId = getIntent().getStringExtra("id");
            paymentName = getIntent().getStringExtra("name");
            paymentType = getIntent().getStringExtra("type");
            paymentAmount = getIntent().getStringExtra("amount");
            paymentDate = getIntent().getStringExtra("date");
            paymentSender = getIntent().getStringExtra("sender");

            //Setting Intent Data
            paymentName_input.setText(paymentName);
            paymentType_input.setText(paymentType);
            paymentAmount_input.setText(paymentAmount);
            paymentDate_input.setText(paymentDate);
            paymentSender_input.setText(paymentSender);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + paymentName + " ?");
        builder.setMessage("Are you sure you want to delete " + paymentName + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(paymentId);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
