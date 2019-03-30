package com.project.android.tic_tac_toe.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;

import com.project.android.tic_tac_toe.Model.Cell;
import com.project.android.tic_tac_toe.Model.Game;
import com.project.android.tic_tac_toe.Model.Player;

import static com.project.android.tic_tac_toe.Utilities.StringUtility.stringFromNumbers;

public class GameViewModel extends ViewModel {
    public ObservableArrayMap<String, String> cells;
    private Game game;

    public void init(String player1, String player2) {
        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            cells.put(stringFromNumbers(row, column), game.currentPlayer.value);
            if (game.hasGameEnded())
                game.reset();
            else
                game.switchPlayer();
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }
}
