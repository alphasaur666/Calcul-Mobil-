package com.example.calculmobil;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class ListViewAdapter extends ArrayAdapter<String>
{

    DatabaseReference reference;
    private List<String> transactions = new ArrayList<>();
    private Context context;

    public ListViewAdapter(List<String> transactions , Context context)
    {
      super(context, R.layout.item_layout, transactions);
      this.context = context;
      this.transactions = transactions;
      this.reference = FirebaseDatabase.getInstance().getReference().child("calculmobil");


    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.item_layout, parent, false);
        TextView transactionsNames = row.findViewById(R.id.transaction_name);
        transactionsNames.setText(transactions.get(position));

        return row;
    }

    public void removeItems(List<String> items){
        for(String item: items){
            transactions.remove(item);
        }
        notifyDataSetChanged();
    }
}
