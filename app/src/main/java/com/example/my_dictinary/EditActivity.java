package com.example.my_dictinary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText ETUkrainianWord, ETEnglishWord;
    String engWord, ukrWord;
    String englishLast, ukrainianLast, englishNew, ukrainianNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ETEnglishWord = findViewById(R.id.ETEnglishWord);
        ETUkrainianWord = findViewById(R.id.ETUkrainianWord);
        DataBaseHelper dataBaseHelper;

        GetData();
        SetData();
        englishLast = ETEnglishWord.getText().toString();
        ukrainianLast = ETUkrainianWord.getText().toString();
    }

    private void GetData(){
        if(getIntent().hasExtra("englishWord") && getIntent().hasExtra("ukrainianWord")){
            engWord = getIntent().getStringExtra("englishWord");
            ukrWord = getIntent().getStringExtra("ukrainianWord");
        } else {
            Toast.makeText(this, "Data isn't exist", Toast.LENGTH_SHORT);
        }
    }
    private void SetData(){
        ETEnglishWord.setText(engWord);
        ETUkrainianWord.setText(ukrWord);
    }

    public void toSave(View view) {

        englishNew = ETEnglishWord.getText().toString();
        ukrainianNew = ETUkrainianWord.getText().toString();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(EditActivity.this);
        if (ETEnglishWord.getText().toString().isEmpty() || ETUkrainianWord.getText().toString().isEmpty()){
            Toast.makeText(this, "field can not be empty", Toast.LENGTH_SHORT).show();
        }else {
            try {
                dataBaseHelper.UpdateWord(englishLast, englishNew, ukrainianLast, ukrainianNew);
            } catch (Exception ex) {
                Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
            }
        }
        /*try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(EditActivity.this);
            englishNew = ETEnglishWord.getText().toString();
            ukrainianNew = ETUkrainianWord.getText().toString();
            if(dataBaseHelper.UpdateWord(englishLast, englishNew ,ukrainianLast, ukrainianNew)){
                Toast.makeText(EditActivity.this, "Successfull changed", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(EditActivity.this, "Failed changed", Toast.LENGTH_LONG);
            }
        }
        catch (Exception ex){
            Toast.makeText(EditActivity.this, "Failed changed1", Toast.LENGTH_LONG);
        }*/

    }

    public void toDelete(View view) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(EditActivity.this);
        try{
            dataBaseHelper.DeleteWord(englishLast, ukrainianLast);

        }
        catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void toSwapText(View view) {
        ETUkrainianWord = findViewById(R.id.ETUkrainianWord);
        ETEnglishWord = findViewById(R.id.ETEnglishWord);
        String temporary = ETUkrainianWord.getText().toString();
        ETUkrainianWord.setText(ETEnglishWord.getText());
        ETEnglishWord.setText(temporary);
    }
}