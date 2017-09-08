package com.hongyangdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hongyangdemo.widget.Game2048Layout;

public class Game2048Activity extends AppCompatActivity implements Game2048Layout.OnGame2048Listener {
    private Game2048Layout mGame2048Layout;
    private TextView mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mScore = (TextView) findViewById(R.id.id_score);
        mGame2048Layout = (Game2048Layout) findViewById(R.id.id_game2048);
        mGame2048Layout.setOnGame2048Listener(this);
    }

    @Override
    public void onGameOver() {
        new AlertDialog.Builder(this).setTitle("GAME OVER")
                .setMessage("YOU HAVE GOT " + mScore.getText())
                .setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mGame2048Layout.restart();
                    }
                })
                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }

    @Override
    public void onScoreChange(int score) {
        mScore.setText("SCORE: " + score);
    }
}
