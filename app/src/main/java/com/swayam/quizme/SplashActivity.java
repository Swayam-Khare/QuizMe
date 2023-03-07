package com.swayam.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.swayam.quizme.db.DatabaseHelper;

/**
 * This Activity is for the splash screen that appears when the app is opened
 */
public class SplashActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dbHelper = new DatabaseHelper(this);

        insertAllQuestions();

        // Intent to start the MainActivity
        Intent i = new Intent(this, MainActivity.class);

        // Launching the MainActivity automatically after 1500 ms and closing this Activity
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(i);
            dbHelper.close();
            finish();
        }, 1500);
    }

    /**
     * This method inserts all the questions along with their answers and options in the Database.
     */
    private void insertAllQuestions() {
        dbHelper.insertQuestion(
                "What is a ^ a?",
                "0",
                "0",
                "1",
                "a",
                "~a"
        );

        dbHelper.insertQuestion(
                "Which of these is not a bitwise operator?",
                "<=",
                "&",
                "&=",
                "|=",
                "<="
        );

        dbHelper.insertQuestion(
                "Which operator is used to invert all the digits in a binary representation of a number?",
                "~",
                "<<",
                ">>",
                "~",
                "^"
        );

        dbHelper.insertQuestion(
                "On applying Left shift operator, <<, on integer bits are lost one they are shifted past which position bit?",
                "31",
                "1",
                "31",
                "32",
                "33"
        );

        dbHelper.insertQuestion(
                "Find operators that work as both Logical operators and Bitwise operators in Java?",
                "All the above",
                "&",
                "|",
                "^",
                "All the above"
        );

        dbHelper.insertQuestion(
                "Right Shift n >> 1 in Java is equivalent to?",
                "n/2",
                "n*2",
                "n+2",
                "n/2",
                "n-2"
        );

        dbHelper.insertQuestion(
                "No. of digits in number n, in base b representation, is given by ______",
                "log base(b) n",
                "log base(b) n",
                "n!",
                "2^n",
                "n/b"
        );

        dbHelper.insertQuestion(
                "What does a ^ 1 give?",
                "~a",
                "0",
                "~a",
                "1",
                "a"
        );

        dbHelper.insertQuestion(
                "Left Shift (<<) in Java is equivalent to?",
                "n*2",
                "n*2",
                "n+2",
                "n/2",
                "n-2"
        );

        dbHelper.insertQuestion(
                "What is the formula to find the rightmost set bit in a binary number n?",
                "n & (-n)",
                "n >> 1",
                "n << 1",
                "n & (-n)",
                "n & (n-1)"
        );
    }
}