package com.project.android.tic_tac_toe.Model;

import com.project.android.tic_tac_toe.Utilities.StringUtility;

public class Cell {
    public Player player;


    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null || StringUtility.isNullOrEmpty(player.value);
    }
}
