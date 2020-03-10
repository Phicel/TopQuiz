package com.example.topquiz.controler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.topquiz.R;

import java.util.Arrays;

import model.Question;
import model.QuestionBank;

//public class GameActivity extends AppCompatActivity {
public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionText;
    private Button mChoiceButton1;
    private Button mChoiceButton2;
    private Button mChoiceButton3;
    private Button mChoiceButton4;
    private int mScore;
    private int mNbrQuestions = 4;
    private  boolean mEnabledChoiceButton; // touches/pas touches
    private QuestionBank mQuestionBank;
    private Question mInProgressQuestion;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //System.out.println("GameActivity::onCreate()");

        mEnabledChoiceButton = true; // touches OK

        //User mUser;
        mQuestionBank = this.generateQuestions(); // création liste

        mQuestionText = (TextView) findViewById(R.id.activity_game_question_text);
        mChoiceButton1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        mChoiceButton2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        mChoiceButton3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        mChoiceButton4 = (Button) findViewById(R.id.activity_game_answer4_btn);

        mChoiceButton1.setTag(0);
        mChoiceButton2.setTag(1);
        mChoiceButton3.setTag(2);
        mChoiceButton4.setTag(3);

        mChoiceButton1.setOnClickListener(this);
        mChoiceButton2.setOnClickListener(this);
        mChoiceButton3.setOnClickListener(this);
        mChoiceButton4.setOnClickListener(this);

        mInProgressQuestion = mQuestionBank.getQuestion();
        this.affQuestion(mInProgressQuestion);

    }

    private void affQuestion(final Question question) {
        mQuestionText.setText(question.getQuestion());
        mChoiceButton1.setText(question.getChoiceList().get(0));
        mChoiceButton2.setText(question.getChoiceList().get(1));
        mChoiceButton3.setText(question.getChoiceList().get(2));
        mChoiceButton4.setText(question.getChoiceList().get(3));
    }

    // BANQUE DE QUESTIONS //
    private QuestionBank generateQuestions() {
        Question q1 = new Question("Lequel de ses noms ne represente pas la musique classique ?",
                Arrays.asList("Beethoven", "Passi", "Schubert", "Vivaldi"), 1);

        Question q2 = new Question("Où se situe la Statue de la Liberté ?",
                Arrays.asList("Los Angeles", "Las Vegas", "New York", "Paris"), 2);

        Question q3 = new Question("Quel est l'âge de Jeanne d'Arc à sa mort ?",
                Arrays.asList("19", "21", "23", "17"), 0);

        Question q4 = new Question("Quelle est la capitale du Royaume-Uni ?",
                Arrays.asList("Londres", "Dublin", "Berlin", "Royaume-Uni"), 0);

        return new QuestionBank(Arrays.asList(q1, q2, q3, q4));
    }


    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == mInProgressQuestion.getAnswerIndex()) {
            // ok !
            Toast.makeText(this, "Réponse correcte !", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            // bad // ca marche !!
            Toast.makeText(this, "Mauvaise réponse !", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, "## TEST OK ##", Toast.LENGTH_SHORT).show();

        mEnabledChoiceButton = false; // on touche plus les touches !
        // mChoiceButton1.setEnabled(false);
        // mChoiceButton2.setEnabled(false);
        // mChoiceButton3.setEnabled(false);
        // mChoiceButton4.setEnabled(false);

        mNbrQuestions--;

        // plus de questions ?
        if (mNbrQuestions <= 0) {
            // FIN
            endTopQuiz();
        } else { // sinon on affiche d'autres questions
            mInProgressQuestion = mQuestionBank.getQuestion();
            affQuestion(mInProgressQuestion);
        }
    }

    private void endTopQuiz() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Le jeu est terminé")
                    .setMessage("Votre score est de : " + mScore + " points.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // fermeture de l 'activité //
                            //gameActivityIntent.
                            //Intent intent = new Intent();
                            //intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                            //setResult(RESULT_OK, intent);
                            //setResult(RESULT_OK, 1);
                            Intent intent = new Intent();
                            intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                            setResult(RESULT_OK, intent);
                            finish();

                        }
                    });
                    //.setCancelable(false)
                    //.create()
                    //.show();
        }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("GameActivity::onDestroy()");
    }

    }

