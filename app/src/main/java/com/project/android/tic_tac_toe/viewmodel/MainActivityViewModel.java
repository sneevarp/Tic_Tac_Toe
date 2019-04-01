package com.project.android.tic_tac_toe.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;

import com.project.android.tic_tac_toe.Block;
import com.project.android.tic_tac_toe.Game;
import com.project.android.tic_tac_toe.User;

public class MainActivityViewModel extends ViewModel {
    public ObservableArrayMap<String, String> blocks;
    private Game game;

    public void init(String player1, String player2) {
        game = new Game(player1, player2);
        blocks = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (game.blocks[row][column] == null) {
            game.blocks[row][column] = new Block(game.currentUser);
            blocks.put(stringFromNumbers(row, column), game.currentUser.value);
            if (game.hasGameEnded())
                game.reset();
            else
                game.switchPlayer();
        }
    }

    public static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();
        for (int number : numbers)
            sNumbers.append(number);
        return sNumbers.toString();
    }

    public LiveData<User> getWinner() {
        return game.winner;
    }
}
