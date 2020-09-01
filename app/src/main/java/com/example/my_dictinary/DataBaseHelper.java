package com.example.my_dictinary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;


public class DataBaseHelper extends SQLiteOpenHelper {

    private  Context context;
    public static final String DICTIONARY_TABLE = "DICTIONARY_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ENGLISH = "ENGLISH";
    public static final String COLUMN_UKRAINIAN = "UKRAINIAN";
    public static final String COLUMN_DATE = "DATE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "my_dictionary.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + DICTIONARY_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ENGLISH + " TEXT NOT NULL, "
                + COLUMN_UKRAINIAN + " TEXT NOT NULL, "
                + COLUMN_DATE + " DATETIME)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean AddOneWord(WordModel wordModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        cv.put(COLUMN_ENGLISH, wordModel.getEnglish());
        cv.put(COLUMN_UKRAINIAN, wordModel.getUkrainian());
        cv.put(COLUMN_DATE, dateFormat.format(wordModel.getDate()));

        long insert = db.insert(DICTIONARY_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    /*public String[] getEnglish(){
        String queryString = "SELECT * FROM " + DICTIONARY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        String[] engWords = new String[cursor.getCount()];
        if (cursor.moveToFirst()){
            int j = 0;
            do{
                engWords[j] = cursor.getString(1);
                j++;
            }while (cursor.moveToNext());
        } else {
            //failure - db is empty
        }
        return engWords;
    }

    public String[] getUkrainian() {
        String queryString = "SELECT * FROM " + DICTIONARY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        String[] ukrWords = new String[cursor.getCount()];
        if (cursor.moveToFirst()) {
            int j = 0;
            do {
                ukrWords[j] = cursor.getString(2);
                j++;
            } while (cursor.moveToNext());
        }else {
            //failure - db is empty
        }
        return ukrWords;
    }*/

    String[] GetSortedData(int sortType, int language){
        String queryString = "SELECT * FROM " + DICTIONARY_TABLE + " ORDER BY ";
        String direction = null;
        String column = null;
        SQLiteDatabase db = this.getReadableDatabase();
        switch (sortType){
            case 1: direction = " ASC;";
                column = COLUMN_DATE;
                break;
            case 2: direction = " DESC;";
                column = COLUMN_DATE;
                break;
            case 3: direction = " ASC;";
                column = COLUMN_ENGLISH;
                break;
            case 4: direction = " DESC;";
                column = COLUMN_ENGLISH;
                break;
            case 5: direction = " ASC;";
                column = COLUMN_UKRAINIAN;
                break;
            case 6: direction = " DESC;";
                column = COLUMN_UKRAINIAN;
                break;
        }
        Cursor cursor = db.rawQuery(queryString + column + direction, null);
        String[] sortedData = new String[cursor.getCount()];
        if (cursor.moveToFirst()) {
            int j = 0;
            do {
                sortedData[j] = cursor.getString(language);
                j++;
            } while (cursor.moveToNext());
        }else {
            //failure - db is empty
        }
        return sortedData;
    }

    void UpdateWord(String engLast, String engNew, String ukrLast, String ukrNew){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ENGLISH, engNew);
        contentValues.put(COLUMN_UKRAINIAN, ukrNew);
        int update = db.update(DICTIONARY_TABLE, contentValues, COLUMN_ENGLISH + " = ? AND " + COLUMN_UKRAINIAN + " = ?", new String[]{engLast, ukrLast});
        if(update == -1){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    void DeleteWord(String engWord, String ukrWord){
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete(DICTIONARY_TABLE, COLUMN_ENGLISH + " = ? AND " + COLUMN_UKRAINIAN + " = ?", new String[]{engWord, ukrWord} );
        if(delete == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


    /*public List<String> getEveryone(int dbColumn) {
        List<String> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + DICTIONARY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            do{
                String ColumnData = cursor.getString(dbColumn);
                int wordID = cursor.getInt(0);
                String wordEnglish = cursor.getString(1);
                String wordUkrainian = cursor.getString(2);
                String wordDate = cursor.getString(3);
                WordModel newWord = new WordModel(wordID, wordEnglish, wordUkrainian, wordDate);
                returnList.add(ColumnData);
            }while (cursor.moveToNext());
        } else{
            //failure - db is empty
        }
        cursor.close();
        db.close();
        return returnList;
    }*/
}
