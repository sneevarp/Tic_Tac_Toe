package com.project.android.tic_tac_toe;

public class Block {
    public User user;


    public Block(User user) {
        this.user = user;
    }

    public boolean isEmpty() {
        return user == null || isNullOrEmpty(user.value);
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
