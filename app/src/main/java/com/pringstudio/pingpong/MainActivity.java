package com.pringstudio.pingpong;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.saujana.pingpong.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PLAYER_RED = 0;
    private static final int PLAYER_BLUE = 1;

    int scoreRed = 0;
    int scoreBlue = 0;

    int currentBall = -1;


    ImageView ballRed;
    ImageView ballBlue;
    TextView textScoreRed;
    TextView textScoreBlue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Click Listener
        RelativeLayout tapRed = (RelativeLayout) findViewById(R.id.score_area_red);
        RelativeLayout tapBlue = (RelativeLayout) findViewById(R.id.score_area_blue);
        ballRed = (ImageView) findViewById(R.id.score_ball_red);
        ballBlue = (ImageView) findViewById(R.id.score_ball_blue);
        textScoreRed = (TextView) findViewById(R.id.score_red);
        textScoreBlue = (TextView) findViewById(R.id.score_blue);

        tapRed.setOnClickListener(this);
        tapBlue.setOnClickListener(this);
        ballRed.setOnClickListener(this);
        ballBlue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.score_area_red:
                if(currentBall >= 0) {
                    updateScore(PLAYER_RED);
                }else{
                    Toast.makeText(this, "Siapa yang punya bola?", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.score_area_blue:

                if(currentBall >= 0) {
                    updateScore(PLAYER_BLUE);
                }else{
                    Toast.makeText(this, "Siapa yang punya bola?", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.score_ball_red:

                if (currentBall < 0) {
                    currentBall = PLAYER_BLUE;

                    ballRed.setVisibility(View.INVISIBLE);
                    ballBlue.setVisibility(View.INVISIBLE);

                    switchBall();
                }


                break;
            case R.id.score_ball_blue:

                if (currentBall < 0) {
                    currentBall = PLAYER_RED;

                    ballRed.setVisibility(View.INVISIBLE);
                    ballBlue.setVisibility(View.INVISIBLE);

                    switchBall();
                }

                break;
            default:
                break;
        }

        Log.d("Click","I'm Clicked!!");
    }

    private void updateScore(int player) {
        if (player == PLAYER_RED) {
            scoreRed++;
        } else if (player == PLAYER_BLUE) {
            scoreBlue++;
        }

        // Tampilkan di screen

        textScoreRed.setText(String.valueOf(scoreRed));
        textScoreBlue.setText(String.valueOf(scoreBlue));


        // Chek Final Score
        if (scoreBlue == 21) {
            Toast.makeText(this, "Player Blue Menang", Toast.LENGTH_SHORT).show();
            showWinPopup();
        }

        if (scoreRed == 21) {
            Toast.makeText(this, "Player Red Menang", Toast.LENGTH_SHORT).show();
            showWinPopup();
        }


        // Pindah Bola
        int totalScore = scoreRed + scoreBlue;

        if ((totalScore % 5) == 0) {
            Toast.makeText(this, "Pindah Bola Coy", Toast.LENGTH_SHORT).show();
            switchBall();
        }
    }

    private void switchBall() {

        switch (currentBall){
            case PLAYER_BLUE:

                currentBall = PLAYER_RED;
                ballRed.setVisibility(View.VISIBLE);
                ballBlue.setVisibility(View.INVISIBLE);
                break;

            case PLAYER_RED:

                currentBall = PLAYER_BLUE;
                ballBlue.setVisibility(View.VISIBLE);
                ballRed.setVisibility(View.INVISIBLE);
                break;

            default:
                break;

        }

    }

    private void resetScore(){
        ballRed.setVisibility(View.VISIBLE);
        ballBlue.setVisibility(View.VISIBLE);
        textScoreBlue.setText("0");
        textScoreRed.setText("0");
        scoreRed = 0;
        scoreBlue = 0;

        currentBall = -1;
    }

    private void showWinPopup(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permainan Usai");
        builder.setMessage("Permainan telah usai, reset score?");
        builder.setPositiveButton("Ya, Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetScore();
            }
        });
        builder.setNegativeButton("Tidak, Lanjut Main", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
