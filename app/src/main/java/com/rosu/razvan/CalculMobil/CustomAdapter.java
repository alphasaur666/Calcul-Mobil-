package com.rosu.razvan.CalculMobil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.rosu.razvan.CalculMobil.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList transactionId, transactionType, transactionName, transactionAmount, transactionDate, transactionSender;

    CustomAdapter(Activity activity, Context context, ArrayList transactionId, ArrayList transactionName, ArrayList transactionType, ArrayList transactionAmount,
                  ArrayList transactionDate, ArrayList transactionSender){
        this.activity = activity;
        this.context = context;
        this.transactionId = transactionId;
        this.transactionName = transactionName;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionSender = transactionSender;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.transaction_id_txt.setText(String.valueOf(transactionId.get(position)));
        holder.transaction_name_txt.setText(String.valueOf(transactionName.get(position)));
        holder.transaction_type_txt.setText(String.valueOf(transactionType.get(position)));
        holder.transaction_amount_txt.setText(String.valueOf(transactionAmount.get(position)));
        holder.transaction_date_txt.setText(String.valueOf(transactionDate.get(position)));
        holder.transaction_sender_txt.setText(String.valueOf(transactionSender.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(transactionId.get(position)));
                intent.putExtra("name", String.valueOf(transactionName.get(position)));
                intent.putExtra("type", String.valueOf(transactionType.get(position)));
                intent.putExtra("amount", String.valueOf(transactionAmount.get(position)));
                intent.putExtra("date", String.valueOf(transactionDate.get(position)));
                intent.putExtra("sender", String.valueOf(transactionSender.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return transactionId.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView transaction_id_txt, transaction_name_txt, transaction_type_txt, transaction_amount_txt, transaction_date_txt, transaction_sender_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            transaction_id_txt = itemView.findViewById(R.id.transaction_id_txt);
            transaction_name_txt = itemView.findViewById(R.id.transaction_name_txt);
            transaction_type_txt = itemView.findViewById(R.id.transaction_type_txt);
            transaction_amount_txt = itemView.findViewById(R.id.transaction_amount_txt);
            transaction_date_txt = itemView.findViewById(R.id.transaction_date_txt);
            transaction_sender_txt = itemView.findViewById(R.id.transaction_sender_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
