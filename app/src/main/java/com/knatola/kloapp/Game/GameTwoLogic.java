package com.knatola.kloapp.Game;

import com.knatola.kloapp.Symbol.Symbol;

/**
 * Created by knatola on 28.11.2017.
 */

public class GameTwoLogic {

    private static GameTwoLogic instance;
    private int questionCount = 0;
    private int scoreCount = 0;

    private GameTwoLogic(){}

    public static GameTwoLogic getInstance(){
        if(instance == null){
            instance = new GameTwoLogic();
        }
        return instance;
    }


}
