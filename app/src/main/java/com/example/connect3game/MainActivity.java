package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 for yellow, 1 for red
    //boolean will work fine, but with 2 players int would be better
    int activePlayer;
    //2 : empty, 0:yellow, 1: red
    int[] gameState = new int[9];
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reinitialiseValues();
    }

    public void dropIn(View view) {

        // finds the view that was tapped
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    gameActive = false;
                    //Someone has won!
                    TextView textView = findViewById(R.id.textView);
                    textView.setVisibility(View.VISIBLE);
                    Button button = findViewById(R.id.button);
                    button.setVisibility(View.VISIBLE);
                    if (gameState[winningPosition[0]] == 1) {
                        textView.setText("Red has won!");
                        textView.setTextColor(Color.parseColor("#FF1100"));
                    } else {
                        textView.setText("Yellow has won!");
                        textView.setTextColor(Color.parseColor("#FFEB3B"));
                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        reinitialiseValues();
    }

    private void reinitialiseValues() {
        gameActive = true;
        activePlayer = 0;
        TextView textView = findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        Button button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=findViewById(R.id.gridLayoutId);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter= (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i < 9; i++) {
            gameState[i] = 2;
        }
    }
}
