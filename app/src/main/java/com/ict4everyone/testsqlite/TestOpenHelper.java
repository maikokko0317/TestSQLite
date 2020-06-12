package com.ict4everyone.testsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestOpenHelper extends SQLiteOpenHelper {

    // データベースのバージョン
    private static final int DATABASE_VERSION = 3;

    // データベース情報を変数に格納
    private static final String DATABASE_NAME = "TestQuizDB.db";
    private static final String TABLE_NAME = "testquizdb";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_QUESTION = "question";
    private static final String COLUMN_NAME_COR_ANSWER = "cor_ans";
    private static final String COLUMN_NAME_OPTION1 = "option1";
    private static final String COLUMN_NAME_OPTION2 = "option2";
    private static final String COLUMN_NAME_OPTION3 = "option3";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " ("  +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_QUESTION + " TEXT," +
                    COLUMN_NAME_COR_ANSWER + " TEXT," +
                    COLUMN_NAME_OPTION1 + " TEXT," +
                    COLUMN_NAME_OPTION2 + " TEXT," +
                    COLUMN_NAME_OPTION3 + " TEXT)" ;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    TestOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES
        );

        saveData(db,"question1", "cor_ans1", "option1_1", "option1_2", "option1_3");
        saveData(db,"question2", "cor_ans2", "option2_1", "option2_2", "option2_3");
        saveData(db,"question3", "cor_ans3", "option3_1", "option3_2", "option3_3");
        saveData(db,"question4", "cor_ans4", "option4_1", "option4_2", "option4_3");
        saveData(db,"question5", "cor_ans5", "option5_1", "option5_2", "option5_3");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void saveData(SQLiteDatabase db, String question, String cor_ans, String option1, String option2, String option3 ){
        ContentValues values = new ContentValues();
        values.put("question", question);
        values.put("cor_ans", cor_ans);
        values.put("option1", option1);
        values.put("option2", option2);
        values.put("option3", option3);

        db.insert("testquizdb", null, values);
    }

}
