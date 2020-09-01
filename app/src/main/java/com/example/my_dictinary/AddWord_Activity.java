package com.example.my_dictinary;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class AddWord_Activity extends Activity {

    EditText ukrainianWord;
    EditText englishWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addword_activity);

    }
    public void swapText(View view) {
        ukrainianWord = findViewById(R.id.ukrainianWord);
        englishWord = findViewById(R.id.englishWord);
        String temporary = ukrainianWord.getText().toString();
        ukrainianWord.setText(englishWord.getText());
        englishWord.setText(temporary);
    }

    public void toMainActivity(View view) {
        Intent intent_main = new Intent(AddWord_Activity.this, MainActivity.class);
        startActivity(intent_main);
    }

    public void toAddWord(View view) {
        ukrainianWord = findViewById(R.id.ukrainianWord);
        englishWord = findViewById(R.id.englishWord);
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        WordModel wordModel;
        if (englishWord.getText().toString().isEmpty() || ukrainianWord.getText().toString().isEmpty()){
            Toast.makeText(this, "field can not be empty", Toast.LENGTH_SHORT).show();
        }else {
            try {
                wordModel = new WordModel(-1, englishWord.getText().toString(), ukrainianWord.getText().toString(), date);
            } catch (Exception ex) {
                wordModel = new WordModel(-1, "error", "error", null);
                Toast.makeText(AddWord_Activity.this, "Error adding word", Toast.LENGTH_LONG).show();
            }
            DataBaseHelper dataBaseHelper = new DataBaseHelper(AddWord_Activity.this);
            boolean success = dataBaseHelper.AddOneWord(wordModel);
            Toast.makeText(AddWord_Activity.this, "Success = " + success, Toast.LENGTH_LONG).show();
            ukrainianWord.setText("");
            englishWord.setText("");
        }
    }

}
