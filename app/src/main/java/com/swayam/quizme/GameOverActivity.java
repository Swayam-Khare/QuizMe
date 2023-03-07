package com.swayam.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView scoreTxt;  // Shows the Total score of the user on the screen
    TextView correctNum;  // Shows the Number of question correctly answered by the user
    TextView accuracyNum;  // Shows the Accuracy percentage of the user
    Button restart;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // Retrieve the intent
        Intent intent = getIntent();

        // Initialize the variables
        scoreTxt = findViewById(R.id.score_txt);
        correctNum = findViewById(R.id.correctNum);
        accuracyNum = findViewById(R.id.accuracyNum);
        restart = findViewById(R.id.restartBtn);
        exit = findViewById(R.id.exitBtn);

        // Launch the GameActivity if restart button is clicked
        restart.setOnClickListener(v -> {
            Intent i = new Intent(GameOverActivity.this, GameActivity.class);
            startActivity(i);
            finish();
        });

        // Close the current Activity if exit button is clicked
        exit.setOnClickListener(v -> {
            finish();
        });

        // Retrieve the score, number of correct answer
        // Calculate the accuracy
        int scr = intent.getIntExtra("score", 0);
        int correctCount = intent.getIntExtra("correct", 0);
        double accuracyPercent = ((float)correctCount / 10) * 100;

        // Show the info on the screen
        scoreTxt.setText(String.valueOf(scr));
        correctNum.setText(String.valueOf(correctCount));
        accuracyNum.setText((int)accuracyPercent + "%");
    }
}