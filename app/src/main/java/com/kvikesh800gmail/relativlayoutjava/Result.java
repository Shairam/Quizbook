package com.kvikesh800gmail.relativlayoutjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Result extends AppCompatActivity {
    TextView correct, incorrect, attempted, score, you;
    int cor = 0, incorr = 0, attempt = 0, scor = 0, yo = 0;
    int x = 0;
    private static final int def = 0;
    private FirebaseAuth mAuth;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        cor = intent.getIntExtra("correct", 0);
        attempt = intent.getIntExtra("attemp", 0);
        incorr = attempt - cor;
        scor = 10 * cor;


        FirebaseUser user = mAuth.getCurrentUser();
        System.out.println(user.getUid());
        database.child("user").child(user.getUid()).child("score").setValue(scor);
        database.child("user").child(user.getUid()).child("correct").setValue(cor);
        database.child("user").child(user.getUid()).child("incorrect").setValue(incorr);
        database.child("user").child(user.getUid()).child("attempted").setValue(attempt);

        correct = (TextView) findViewById(R.id.correct);
        incorrect = (TextView) findViewById(R.id.incorrect);
        attempted = (TextView) findViewById(R.id.attempted);
        score = (TextView) findViewById(R.id.score);
        you = (TextView) findViewById(R.id.you);

        attempted.setText("  " + attempt);
        correct.setText("  " + cor);
        incorrect.setText("  " + incorr);
        score.setText("Score  :    " + scor);
        float x1 = (cor * 100) / attempt;
        if (x1 < 40)
            you.setText("You Need Improvement");
        else if (x1 < 70)
            you.setText("You are an average Quizzer");
        else if (x1 < 90)
            you.setText("You are an above average Quizzer ");
        else
            you.setText("You are a brilliant  Quizzer ");
    }
}
