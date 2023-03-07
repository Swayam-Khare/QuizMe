package com.swayam.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/**
 * This Activity shows the main menu
 */
public class MainActivity extends AppCompatActivity {

    Button startBtn;
    Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startButton);
        exitBtn = findViewById(R.id.exitButton);

        // Launching the GameActivity when start game button is clicked
        startBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });

        // Closing the Activity when exit button is clicked
        exitBtn.setOnClickListener(view -> {
            finish();
        });
    }
}