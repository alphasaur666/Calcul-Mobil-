package com.rosu.razvan.CalculMobil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rosu.razvan.CalculMobil.R;


public class AddActivity extends AppCompatActivity {

    EditText paymentName, paymentType, paymentAmount , paymentSender , paymentDate;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        paymentName = findViewById(R.id.payment_name_input);
        paymentType = findViewById(R.id.payment_type_input);
        paymentAmount = findViewById(R.id.payment_amount_input);
        paymentSender = findViewById(R.id.payment_sender_input);
        paymentDate = findViewById(R.id.payment_date_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addTransaction(paymentName.getText().toString().trim(),
                                    paymentType.getText().toString().trim(),
                                    Integer.valueOf(paymentAmount.getText().toString().trim()),
                                    paymentDate.getText().toString().trim(),
                                    paymentSender.getText().toString().trim());

                //Pentru partea de aruncare de push notifications, am incercat sa dezvolt acest cod pentru adaugarea
                //unei noi tranzactii ca notificare push, insa, intent ul nu functioneaza cum trebuie.
                String message = "Added a new transaction!";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(AddActivity.this
                    )
                        .setSmallIcon(R.drawable.ic_add)
                        .setContentTitle("Transaction added.")
                        .setContentText(message)
                        .setAutoCancel(true);
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent pendingIntent = PendingIntent.getActivity(AddActivity.this, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
            }
        });
    }
}
