package com.swayam.quizme.db.entity;

public class Question {
    // Constants for database
    public static final String TABLE_NAME = "questions";
    public static final String COLUMN_ID = "que_id";
    public static final String COLUMN_QUE = "question";
    public static final String COLUMN_ANS = "answer";
    public static final String COLUMN_A = "option_a";
    public static final String COLUMN_B = "option_b";
    public static final String COLUMN_C = "option_c";
    public static final String COLUMN_D = "option_d";

    // Variables to store each question, its options and its answer
    private int id;
    private String que;
    private String ans;
    private String optA;
    private String optB;
    private String optC;
    private String optD;

    private Question(){};

    public Question(int id, String que, String ans, String optA, String optB, String optC, String optD) {
        this.id = id;
        this.que = que;
        this.ans = ans;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.optD = optD;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getOptD() {
        return optD;
    }

    public void setOptD(String optD) {
        this.optD = optD;
    }

    // SQL query string to create the questions table
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_QUE
            + " TEXT, " + COLUMN_ANS + " TEXT, " + COLUMN_A
            + " TEXT, " + COLUMN_B + " TEXT, " + COLUMN_C + " TEXT, "
            + COLUMN_D + " TEXT" + ")";
}
