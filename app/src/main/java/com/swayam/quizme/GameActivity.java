package com.swayam.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.swayam.quizme.db.DatabaseHelper;
import com.swayam.quizme.db.entity.Question;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    Handler handler;
    Button optionA;
    Button optionB;
    Button optionC;
    Button optionD;
    TextView question;
    TextView countNumber;
    DatabaseHelper dbHelper;
    int currentQueNumber;
    Question currentQuestion;
    int score;
    int correctQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        handler = new Handler();
        dbHelper = new DatabaseHelper(this);

        correctQuestion = 0;
        score = 0;
        currentQueNumber = 1;
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        question = findViewById(R.id.questionTxt);
        countNumber = findViewById(R.id.queCount);

        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);

        showQuestion();
    }

    private void showQuestion() {

        enableButtons();

        if (currentQueNumber > 10){
            Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("correct", correctQuestion);

            startActivity(intent);
            finish();
            return;
        }

        currentQuestion = dbHelper.getQuestion(currentQueNumber);

        countNumber.setText(currentQueNumber + " / 10");
        question.setText(currentQuestion.getQue());
        optionA.setText(currentQuestion.getOptA());
        optionB.setText(currentQuestion.getOptB());
        optionC.setText(currentQuestion.getOptC());
        optionD.setText(currentQuestion.getOptD());
    }

    private void enableButtons() {
        optionA.setClickable(true);
        optionB.setClickable(true);
        optionC.setClickable(true);
        optionD.setClickable(true);
    }

    @Override
    public void onClick(View view) {
        Button btnPressed = (Button) view;
        disableButtons();

        handler.postDelayed(() -> {
            btnPressed.setBackgroundColor(getColor(R.color.white));
            btnPressed.setTextColor(getColor(R.color.primaryDark));
            showQuestion();
        }, 2000);

        if(currentQuestion.getAns().equals(btnPressed.getText().toString())){
            btnPressed.setBackgroundColor(getColor(R.color.primaryDark));
            btnPressed.setTextColor(getColor(R.color.white));
            score += 10;
            currentQueNumber++;
            correctQuestion++;
        }
        else {
            btnPressed.setBackgroundColor(getColor(R.color.wrongAnswer));
            btnPressed.setTextColor(getColor(R.color.white));
            currentQueNumber++;
        }
    }

    private void disableButtons() {
        optionA.setClickable(false);
        optionB.setClickable(false);
        optionC.setClickable(false);
        optionD.setClickable(false);
    }
}