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

    /**
     * Method to insert a question in the database along with its answer and options
     *
     * @param question is the question String to be inserted
     * @param ans      is the answer of the question
     * @param optA     is the first option
     * @param optB     is the second option
     * @param optC     is the third option
     * @param optD     is the fourth option
     */
    public void insertQuestion(String question, String ans, String optA, String optB, String optC, String optD){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Question.COLUMN_QUE, question);
        values.put(Question.COLUMN_ANS, ans);
        values.put(Question.COLUMN_A, optA);
        values.put(Question.COLUMN_B, optB);
        values.put(Question.COLUMN_C, optC);
        values.put(Question.COLUMN_D, optD);

        db.insert(Question.TABLE_NAME, null, values);
        db.close();

    }

    /**
     * Method to retrieve the question by passing its row id in the database
     * @param id is the id of the row in which the question resides
     * @return the Question object containing the question String along with its answer and options
     */
    public Question getQuestion(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        // Query: SELECT * FROM questions WHERE que_id = id
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

            // Creating a Question object by passing in the values from the cursor.
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
