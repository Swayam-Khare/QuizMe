package com.swayam.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView scoreTxt;
    TextView correctNum;
    TextView accuracyNum;
    Button restart;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();

        scoreTxt = findViewById(R.id.score_txt);
        correctNum = findViewById(R.id.correctNum);
        accuracyNum = findViewById(R.id.accuracyNum);
        restart = findViewById(R.id.restartBtn);
        exit = findViewById(R.id.exitBtn);

        restart.setOnClickListener(v -> {
            Intent i = new Intent(GameOverActivity.this, GameActivity.class);
            startActivity(i);
            finish();
        });

        exit.setOnClickListener(v -> {
            finish();
        });

        int scr = intent.getIntExtra("score", 0);
        int correctCount = intent.getIntExtra("correct", 0);
        double accuracyPercent = ((float)correctCount / 10) * 100;

        scoreTxt.setText(String.valueOf(scr));
        correctNum.setText(String.valueOf(correctCount));
        accuracyNum.setText((int)accuracyPercent + "%");


    }
}