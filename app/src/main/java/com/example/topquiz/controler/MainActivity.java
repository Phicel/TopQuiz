package com.example.topquiz.controler;
//package com.example.topquiz.model;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.topquiz.R;

import model.Question;
import model.QuestionBank;
import model.User;
import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private Button mScoreButton;
    private Button mRankingButton;
    private String ChaineNom;
    private Question mQuestion;
    private QuestionBank mQuestionListe;
    private SharedPreferences mPreferences;
    public User mUser;
    //public static final String RANK_KEY_FIRSTNAME = "RANK_KEY_FIRSTNAME";
    //public static final String RANK_KEY_SCORE = "RANK_KEY_SCORE";
    public static final int GAME_ACTIVITY_REQUEST_CODE = 77;
    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input); // Lien de la var avec compo EditText du layer XML'
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        //mPlayButton.setEnabled(false);
        mScoreButton = (Button) findViewById(R.id.activity_main_ranking_btn);
        //final User mUser = new User();

        //enableScoresButton();

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);
                //ChaineNom = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //mGreetingText.setText("coucou"); // TEST
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mGreetingText.setText("cliqué !");
                //mGreetingText.setText("Bonjour " + ChaineNom + " !");
                String firstname = mNameInput.getText().toString();
                //firstname = "";
                //mUser.setFirstname("Test");
                mUser.setFirstName(firstname);
                //mUser.setFirstName(mNameInput.getText().toString()); //  //
                //mUser.setScore(12);

                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstName()).apply();

                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
                //startActivity(gameActivityIntent);


            }
        });

        mScoreButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                mGreetingText.setText("Affichage liste score");
                Intent scoreActivityIntent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(scoreActivityIntent);
            }
        });

        //mCurrentQuestion = mQuestionBank.getQuestion();
        //this.displayQuestion(mCurrentQuestion);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
            //saveScore(score);

            returnPlayer();

        }
    }

    private void returnPlayer() {
        String firstnamePlayer = mPreferences.getString(PREF_KEY_FIRSTNAME, null);
        if (null != firstnamePlayer) {
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);
            String fulltext = "Bonjour, " + firstnamePlayer
                    + "!\nvotre score était de " + score + " points"
                    + ", voulez-vous recommencer ?";
            mGreetingText.setText(fulltext);
            mNameInput.setText(firstnamePlayer);
            mNameInput.setSelection(firstnamePlayer.length()); //
            mPlayButton.setEnabled(true);
        }
    }

    /**
    private void saveScore(int score) {
        Score newScore = new Score(score, mUser);
        Ranks ranking = new Ranks(this);
        List<Score> scoreList = ranking.loadScores();
        // rajoute le score a la liste
        scoreList.add(newScore);
        ranking.saveScores(scoreList);
        enableScoresButton();
    }
    **/

    /**
    private void enableScoresButton() {
        boolean rankingExists = new Ranks(this).isEmpty();
        mScoreButton.setVisibility(rankingExists ? View.VISIBLE : View.INVISIBLE);
    }
    **/

    @Override
    protected void onStart() {
        super.onStart();
        out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        out.println("MainActivity::onDestroy()");
    }

}
