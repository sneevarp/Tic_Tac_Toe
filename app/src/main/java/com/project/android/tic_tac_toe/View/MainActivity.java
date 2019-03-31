package com.project.android.tic_tac_toe.View;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.android.tic_tac_toe.Model.Player;
import com.project.android.tic_tac_toe.R;
import com.project.android.tic_tac_toe.viewmodel.MainActivityViewModel;
import com.project.android.tic_tac_toe.databinding.ActivityMainBinding;

import static com.project.android.tic_tac_toe.Utilities.StringUtility.isNullOrEmpty;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayers();
    }

    public void promptForPlayers() {
        PlayerList dialog = PlayerList.newInstance(this);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "playerNames displayed");
    }

    public void onPlayersSet(String player1, String player2) {
        initDataBinding(player1, player2);
    }

    private void initDataBinding(String player1, String player2) {
        ActivityMainBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gameViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        gameViewModel.init(player1, player2);
        activityGameBinding.setGameViewModel(gameViewModel);
        setUpOnGameEndListener();
    }

    private void setUpOnGameEndListener() {
        gameViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    @VisibleForTesting
    public void onGameWinnerChanged(Player winner) {
        String winnerName = winner == null || isNullOrEmpty(winner.name) ? "Match Tied" : winner.name;
        Result dialog = Result.newInstance(this, winnerName);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "Result Displayed");
    }
}
