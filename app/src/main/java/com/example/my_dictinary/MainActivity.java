package com.example.my_dictinary;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int sortingType;
    String[] englishWords, ukrainianWords;
    RecyclerView recyclerView;
    Spinner spinner;
    TextView column1, column2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onResume(){
        super.onResume();

        recyclerView = findViewById(R.id.recyclerView);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        spinner = findViewById(R.id.spinner);
        String selectedItem = spinner.getSelectedItem().toString();
        SpinnerSelectedItem(selectedItem);
        englishWords = dataBaseHelper.GetSortedData(1,1);
        ukrainianWords = dataBaseHelper.GetSortedData(1,2);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, englishWords, ukrainianWords);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public void toAddWordActivity(View view) {
        Intent intent_addWord = new Intent(MainActivity.this, AddWord_Activity.class);
        startActivity(intent_addWord);
    }

    public void startSorting(View view) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        column1 = findViewById(R.id.column1);
        String selectedItem = spinner.getSelectedItem().toString();
        SpinnerSelectedItem(selectedItem);
        RecyclerViewAdapter recyclerViewAdapter;
        englishWords = dataBaseHelper.GetSortedData(sortingType, 1);
        ukrainianWords = dataBaseHelper.GetSortedData(sortingType, 2);
        if (column1.getText().toString() == "Ukrainian"){
            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, ukrainianWords, englishWords);
        }else{
            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, englishWords, ukrainianWords);
        }
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public int SpinnerSelectedItem(String item){
        switch (item){
            case "Date ↑": sortingType = 1;
                break;
            case "Date ↓": sortingType = 2;
                break;
            case "A → Z": sortingType = 3;
                break;
            case "Z → A": sortingType = 4;
                break;
            case "А → Я": sortingType = 5;
                break;
            case "Я → А": sortingType = 6;
                break;
        }
        return sortingType;
    }

    public void toSwapColumn(View view) {
        column1 = findViewById(R.id.column1);
        column2 = findViewById(R.id.column2);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        String selectedItem = spinner.getSelectedItem().toString();
        SpinnerSelectedItem(selectedItem);
        RecyclerViewAdapter recyclerViewAdapter;
        englishWords = dataBaseHelper.GetSortedData(sortingType, 1);
        ukrainianWords = dataBaseHelper.GetSortedData(sortingType, 2);
        if (column1.getText().toString() == "English"){
            column1.setText("Ukrainian");
            column2.setText("English");
            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, ukrainianWords, englishWords);
        }else{
            column1.setText("English");
            column2.setText("Ukrainian");
            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, englishWords, ukrainianWords);
        }
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}