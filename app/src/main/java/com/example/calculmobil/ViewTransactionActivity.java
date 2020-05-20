package com.example.calculmobil;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ViewTransactionActivity extends AppCompatActivity {

    FloatingActionButton addTransaction;
    Toolbar toolbar;
    ListView transactionsView;
    ListViewAdapter adapter;
    List<String> transactions = new ArrayList<>();
    List<String> userSelectedTransactions = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewtransaction_activity);
        getTransactions();
        addTransaction = findViewById(R.id.floatingActionButton3);
        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(ViewTransactionActivity.this, AddTransactionActivity.class);
                startActivity(startIntent);
            }
        });
        transactionsView = (ListView)findViewById(R.id.test);

        transactionsView.setLongClickable(true);
        transactionsView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        transactionsView.setMultiChoiceModeListener(modeListener);
        adapter = new ListViewAdapter(transactions, this);
        transactionsView.setAdapter(adapter);
    }

    AbsListView.MultiChoiceModeListener modeListener = new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            if(userSelectedTransactions.contains(transactions.get(position)))
            {
                userSelectedTransactions.remove(transactions.get(position));
            }
            else
                {
                userSelectedTransactions.add(transactions.get(position));
            }
            mode.setTitle(userSelectedTransactions.size() + "items selected");
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.menu_activity, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch(item.getItemId()){
                case R.id.delete:
                    adapter.removeItems(userSelectedTransactions);
                    mode.finish();
                    return true;
                case R.id.edit:
                    Intent startIntent = new Intent(ViewTransactionActivity.this, EditTransactionActivity.class);
                    startActivity(startIntent);
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            userSelectedTransactions.clear();

        }
    };

    private void getTransactions(){
        String[] items = getResources().getStringArray(R.array.transactions);
        for(String item : items){
            transactions.add(item);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}



