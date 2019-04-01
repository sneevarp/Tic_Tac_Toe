package com.project.android.tic_tac_toe;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

public class Game {
    private static final String TAG = Game.class.getSimpleName();
    private static final int BOARD_SIZE = 3;

    public User user1;
    public User user2;

    public User currentUser = user1;
    public Block[][] blocks;

    public MutableLiveData<User> winner = new MutableLiveData<>();

    public Game(String playerOne, String playerTwo) {
        blocks = new Block[BOARD_SIZE][BOARD_SIZE];
        user1 = new User(playerOne, "x");
        user2 = new User(playerTwo, "o");
        currentUser = user1;
    }

    public boolean hasGameEnded() {
        if (hasThreeSameHorizontalCells() || hasThreeSameVerticalCells() || hasThreeSameDiagonalCells()) {
            winner.setValue(currentUser);
            return true;
        }

        if (isBoardFull()) {
            winner.setValue(null);
            return true;
        }

        return false;
    }

    public boolean hasThreeSameHorizontalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(blocks[i][0], blocks[i][1], blocks[i][2]))
                    return true;

            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public boolean hasThreeSameVerticalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(blocks[0][i], blocks[1][i], blocks[2][i]))
                    return true;
            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public boolean hasThreeSameDiagonalCells() {
        try {
            return areEqual(blocks[0][0], blocks[1][1], blocks[2][2]) ||
                    areEqual(blocks[0][2], blocks[1][1], blocks[2][0]);
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public boolean isBoardFull() {
        for (Block[] row : blocks)
            for (Block block : row)
                if (block == null || block.isEmpty())
                    return false;
        return true;
    }

    private boolean areEqual(Block... blocks) {
        if (blocks == null || blocks.length == 0)
            return false;

        for (Block block : blocks)
            if (block == null || isNullOrEmpty(block.user.value))
                return false;

        Block comparisonBase = blocks[0];
        for (int i = 1; i < blocks.length; i++)
            if (!comparisonBase.user.value.equals(blocks[i].user.value))
                return false;

        return true;
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }

    public void switchPlayer() {
        currentUser = currentUser == user1 ? user2 : user1;
    }

    public void reset() {
        user1 = null;
        user2 = null;
        currentUser = null;
        blocks = null;
    }

}
