package com.swayam.quizme.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.swayam.quizme.db.entity.Question;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "question_db";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Question.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Question.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertQuestion(String question, String ans, String optA, String optB, String optC, String optD){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Question.COLUMN_QUE, question);
        values.put(Question.COLUMN_ANS, ans);
        values.put(Question.COLUMN_A, optA);
        values.put(Question.COLUMN_B, optB);
        values.put(Question.COLUMN_C, optC);
        values.put(Question.COLUMN_D, optD);

        long id = db.insert(Question.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public Question getQuestion(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Question.TABLE_NAME,
                new String[]{Question.COLUMN_ID,
                        Question.COLUMN_QUE,
                        Question.COLUMN_ANS,
                        Question.COLUMN_A,
                        Question.COLUMN_B,
                        Question.COLUMN_C,
                        Question.COLUMN_D,
                },
                Question.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null
        );

        Question question = null;

        if (cursor != null) {
            cursor.moveToFirst();

            question = new Question(
                    cursor.getInt(cursor.getColumnIndexOrThrow(Question.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Question.COLUMN_QUE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Question.COLUMN_ANS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Question.COLUMN_A)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Question.COLUMN_B)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Question.COLUMN_C)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Question.COLUMN_D))
            );

            cursor.close();
        }

        return question;
    }
}
