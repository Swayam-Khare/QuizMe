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

    Handler handler;  // Handler to automatically change to question after user selects an option
    Button optionA;
    Button optionB;
    Button optionC;
    Button optionD;
    TextView question;
    TextView countNumber;
    DatabaseHelper dbHelper;
    int currentQueNumber;  // Keep track of the current question shown to the user.
    Question currentQuestion;  // Question that is currently on the screen
    int score;  // Score of the user
    int correctQuestion;  // Number of question user answered correctly

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initializing the variables
        handler = new Handler();
        dbHelper = new DatabaseHelper(this);

        correctQuestion = 0;
        score = 0;
        currentQueNumber = 1;  // Starting with question number 1
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

        showQuestion();  // Show the question to the user
    }

    /**
     * This method shows the question to the user according to the question number user
     * currently is at.
     */
    private void showQuestion() {

        enableButtons();  // Make the option Buttons clickable

        // If user has answered all the questions then Launch the GameOverActivity
        if (currentQueNumber > 10){

            // Create intent and store the score and number of question correctly answer by user
            // in the intent
            Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("correct", correctQuestion);

            // Start the GameOverActivity and close current Activity
            startActivity(intent);
            finish();
            return;
        }

        // If there are still question left to show then show the question and wait for the
        // response from the user
        currentQuestion = dbHelper.getQuestion(currentQueNumber);

        // Update the UI according to the current question
        countNumber.setText(currentQueNumber + " / 10");
        question.setText(currentQuestion.getQue());
        optionA.setText(currentQuestion.getOptA());
        optionB.setText(currentQuestion.getOptB());
        optionC.setText(currentQuestion.getOptC());
        optionD.setText(currentQuestion.getOptD());
    }

    /**
     * Method to enable all the buttons
     */
    private void enableButtons() {
        optionA.setClickable(true);
        optionB.setClickable(true);
        optionC.setClickable(true);
        optionD.setClickable(true);
    }

    /**
     * OnClick method for all the option Buttons
     * @param view The Button View which is clicked by the user
     */
    @Override
    public void onClick(View view) {
        Button btnPressed = (Button) view;
        disableButtons();  // Disable the buttons to stop user from selecting multiple answers

        // Reset the color of the option buttons and show the next question
        handler.postDelayed(() -> {
            btnPressed.setBackgroundColor(getColor(R.color.white));
            btnPressed.setTextColor(getColor(R.color.primaryDark));
            showQuestion();
        }, 2000);

        // If the option chosen by the user is correct then change the color to primaryDark
        // Increase the score, current question number and number of correct question
        if(currentQuestion.getAns().equals(btnPressed.getText().toString())){
            btnPressed.setBackgroundColor(getColor(R.color.primaryDark));
            btnPressed.setTextColor(getColor(R.color.white));
            score += 10;
            currentQueNumber++;
            correctQuestion++;
        }
        // If the option chosen is wrong then change the color to red and only
        // Increase the current question number
        else {
            btnPressed.setBackgroundColor(getColor(R.color.wrongAnswer));
            btnPressed.setTextColor(getColor(R.color.white));
            currentQueNumber++;
        }
    }

    /**
     * This method disables all the option buttons
     */
    private void disableButtons() {
        optionA.setClickable(false);
        optionB.setClickable(false);
        optionC.setClickable(false);
        optionD.setClickable(false);
    }
}