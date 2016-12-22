package com.zhang.game2048;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvScore, tvRecord;
    private Button btnNewGame;
    private GameView gameView;
    private AnimLayer animLayer;

    private int score = 0;

    private static final String BEST_RECORD = "bestRecord";

    public MainActivity() {
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = (TextView) findViewById(R.id.tv_score);
        tvRecord = (TextView) findViewById(R.id.tv_record);
        tvScore.setText(0 + "");
        showBestRecord();

        btnNewGame = (Button) findViewById(R.id.btn_new_game);
        gameView = (GameView) findViewById(R.id.game_view);
        animLayer = (AnimLayer) findViewById(R.id.anim_layer);

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScore();
                showBestRecord();
                gameView.startGame();
            }
        });

    }

    public void clearScore() {
        score = 0;
        showScore();
    }

    public void showScore() {
        tvScore.setText(score + "");
    }

    public void addScore(int s) {
        score += s;
        showScore();

        int bestRecord = Math.max(score, getBestRecord());
        saveBestRecord(bestRecord);
    }

    public void showBestRecord() {
        tvRecord.setText(getBestRecord() + "");
    }

    public void saveBestRecord(int score) {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putInt(BEST_RECORD, score);
        editor.commit();
    }

    public int getBestRecord() {
        return getPreferences(MODE_PRIVATE).getInt(BEST_RECORD, 0);
    }

    public AnimLayer getAnimLayer() {
        return animLayer;
    }

    private static MainActivity mainActivity = null;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }


}
