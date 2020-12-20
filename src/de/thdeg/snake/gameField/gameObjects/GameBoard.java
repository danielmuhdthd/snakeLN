package de.thdeg.snake.gameField.gameObjects;

import de.thdeg.snake.gameField.UiFieldTranslator;
import de.thdeg.snake.gameField.fieldObjects.CollisionType;
import de.thdeg.snake.gameField.fieldObjects.FieldTile;
import de.thdeg.snake.highScore.HighscoreIO;
import de.thdeg.snake.keyboardWrapper.Direction;
import de.thdeg.snake.runtime.InternalLedGameThread;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

public class GameBoard implements ActionListener {

    private FieldTile[][] gameField;
    private Snake snake;
    private final Timer timer;
    private int score = 0;
    private final String[] difficulties = {"Easy", "Medium", "Hard", "Hardcore"};
    private final HighscoreIO highScoreRefresher = new HighscoreIO();
    private final UiFieldTranslator translator = new UiFieldTranslator();
    private boolean runningFirstTime = true;

    private int userDifficulty = 1;

    public GameBoard(){
        initializeField();
        timer = new Timer(10, this);
        initializeGame();
    }

    private void initializeGame(){
        userDifficulty = JOptionPane.showOptionDialog(null, "Returns the position of your choice on the array",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, difficulties, difficulties[userDifficulty]);
        initializeGameObjects();
        timer.setDelay(mapMillisecondsToDifficulty());

        Countdown countdown = new Countdown(2, 2, gameField, translator, timer);
        translator.translateToShortArray(gameField);
        countdown.doCountdown();
    }

    private void initializeField() {
        gameField = new FieldTile[24][];
        for (byte i = 0; i < gameField.length; ++i) {
            gameField[i] = new FieldTile[48];
            for (byte j = 0; j < gameField[0].length; ++j) {
                if(i == 0 || j == 0 || i == gameField.length-1 || j == gameField[0].length-1){
                    gameField[i][j] = new FieldTile(CollisionType.death, j, i);
                }else{
                    gameField[i][j] = new FieldTile(CollisionType.nothing, j, i);
                }
            }
        }
    }

    private void initializeGameObjects() {
        snake = new Snake(new ArrayList<>(Arrays.asList(gameField[10]).subList(5, 10)));
        translator.translateToShortArray(gameField);
    }

    private int mapMillisecondsToDifficulty() {
        return switch(userDifficulty){
            case 0 -> 200;
            case 2 -> 100;
            case 3 -> 50;
            default -> 150;
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(runningFirstTime){
            placeFood();
            runningFirstTime = false;
        }
        processKeyboardInput();
        switch (snake.getFacing()) {
            case up -> calculateNextTile((byte) -1, (byte) 0);
            case down -> calculateNextTile((byte) 1, (byte) 0);
            case left -> calculateNextTile((byte) 0, (byte) -1);
            case right -> calculateNextTile((byte) 0, (byte) 1);
        }
        translator.translateToShortArray(gameField);
    }

    private void processKeyboardInput() {
        switch(InternalLedGameThread.getKeyboard()){
            case 0 -> snake.setFacing(Direction.up);
            case 1 -> snake.setFacing(Direction.down);
            case 2 -> snake.setFacing(Direction.left);
            case 3 -> snake.setFacing(Direction.right);
        }
    }

    private void calculateNextTile(byte relY, byte relX){
        byte nextTilePosX = (byte)(snake.getHead().getPosX()+relX), nextTilePosY = (byte)(snake.getHead().getPosY()+relY);
        FieldTile nextTile = gameField[nextTilePosY][nextTilePosX];
        switch(nextTile.getCollision()){
            case death -> gameOver();
            case food -> {
                snake.eat(nextTile);
                placeFood();
                ++score;
                System.out.println(score);
            }
            default -> snake.move(nextTile);
        }
    }

    private void gameOver(){
        timer.stop();
        System.out.println("-----------------------");
        JOptionPane.showMessageDialog(null, "Game Over");
        JOptionPane.showMessageDialog(null,highScoreRefresher.refreshHighscore(difficulties[userDifficulty],score));
        int x = JOptionPane.showConfirmDialog(null, "Do you want to restart?",
                "",
                 JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            restart();
        } else if (x == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    private void restart(){
        initializeField();
        score = 0;
        runningFirstTime = true;
        initializeGame();
    }

    private void placeFood(){
        Random rand = new Random();
        int xFood = 1+rand.nextInt( gameField[0].length-3);
        int yFood = 1+rand.nextInt( gameField.length-3);
        while(gameField[yFood][xFood].getCollision() != CollisionType.nothing){
            xFood = 1+rand.nextInt( gameField[0].length-3);
            yFood = 1+rand.nextInt( gameField.length-3);
        }
        gameField[yFood][xFood].change(CollisionType.food);
    }
}
