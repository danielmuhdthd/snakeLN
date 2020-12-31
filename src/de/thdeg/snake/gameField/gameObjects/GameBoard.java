package de.thdeg.snake.gameField.gameObjects;

import de.thdeg.snake.gameField.UiFieldTranslator;
import de.thdeg.snake.gameField.fieldObjects.CollisionType;
import de.thdeg.snake.gameField.fieldObjects.FieldTileBase;
import de.thdeg.snake.gameField.fieldObjects.fieldTiles.BackgroundTile;
import de.thdeg.snake.gameField.fieldObjects.fieldTiles.DeathTile;
import de.thdeg.snake.highScore.HighscoreIO;
import de.thdeg.snake.keyboardWrapper.Direction;
import de.thdeg.snake.runtime.InternalLedGameThread;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

/**
 * Game board on which the game is played
 */
public class GameBoard implements ActionListener {

    /** Handles the board, on which the game is played */
    private FieldTileBase[][] gameField;

    /** Handles a snake */
    private Snake snake;

    /** Timer, which handles the ticks */
    private final Timer timer;

    /** score of the current game */
    private int score = 0;
    /** Difficulties to play on */
    private final String[] difficulties = {"Easy", "Medium", "Hard", "Hardcore"};

    /** Handles and refreshes the highscore lists */
    private final HighscoreIO highScoreRefresher = new HighscoreIO();

    /** Used to translate a FieldTile[][] to an displayable short array and displays it */
    private final UiFieldTranslator translator = new UiFieldTranslator();

    /** Ugly solution to determine, if the timer runs for the first time */
    private boolean runningFirstTime = true;

    /** User chosen difficulty encoded as a number */
    private int userDifficulty = 1;

    /** Changed Tiles in the current tick */
    private final List<FieldTileBase> changedTiles = new ArrayList<>();

    /** Initializes a new GameBoard object */
    public GameBoard(){
        initializeField();
        timer = new Timer(10, this);
        initializeGame();
    }

    /** Sets difficulty, timer delay and initializes new countdown */
    private void initializeGame(){
        userDifficulty = JOptionPane.showOptionDialog(null, "Returns the position of your choice on the array",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, difficulties, difficulties[userDifficulty]);
        initializeSnake();
        timer.setDelay(mapMillisecondsToDifficulty());

        Countdown countdown = new Countdown(2, 2, gameField, translator, timer);
        countdown.doCountdown();
    }

    /** Initializes the gameField */
    private void initializeField() {
        gameField = new FieldTileBase[24][];
        for (byte i = 0; i < gameField.length; ++i) {
            gameField[i] = new FieldTileBase[48];
            for (byte j = 0; j < gameField[0].length; ++j) {
                if(i == 0 || j == 0 || i == gameField.length-1 || j == gameField[0].length-1){
                    gameField[i][j] = new DeathTile(j, i, false);
                }else{
                    gameField[i][j] = new BackgroundTile(j, i);
                }
            }
        }
    }

    /** Initializes the snake */
    private void initializeSnake() {
        List<DeathTile> snakeTiles = new ArrayList<>();
        for(int i = 5; i<10; ++i){
            DeathTile deathTile = gameField[10][i].changeToDeathTile(true);
            gameField[10][i] = deathTile;
            snakeTiles.add(deathTile);
        }
        snake = new Snake(snakeTiles);
        translator.translateGameFieldToShortArray(gameField);
    }

    /**
     * Method which is called periodically by the timer
     * @param e ignored
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        changedTiles.clear();
        if(runningFirstTime){
            placeFood();
            runningFirstTime = false;
        }
        snake.setFacing(Direction.mapFromKeyboard(InternalLedGameThread.getKeyboard()));

        switch (snake.getFacing()) {
            case up -> calculateNextTile((byte) -1, (byte) 0);
            case down -> calculateNextTile((byte) 1, (byte) 0);
            case left -> calculateNextTile((byte) 0, (byte) -1);
            case right -> calculateNextTile((byte) 0, (byte) 1);
        }
        translator.translateChanges(changedTiles);
    }


    /**
     * Position of  next tile is used to determine the status of the game:
     * CollisionType       |      status
     * --------------------------------
     * death               |      gameOver
     * food                |      eat
     * otherwise (nothing) |      move
     *
     * @param relY relative y position to the head of the next tile calculated
     * @param relX relative x position to the head of the next tile calculated
     */
    private void calculateNextTile(byte relY, byte relX){
        byte nextTilePosX = (byte)(snake.getHead().getPosX()+relX), nextTilePosY = (byte)(snake.getHead().getPosY()+relY);
        DeathTile newSnakeTile = gameField[nextTilePosY][nextTilePosX].changeToDeathTile(true);
        switch(gameField[nextTilePosY][nextTilePosX].getCollision()){
            case death -> {
                gameOver();
                return;
            }
            case food -> {
                snake.eat(newSnakeTile);
                placeFood();
                ++score;
                System.out.println(score);
            }
            default -> changeTile(snake.move(newSnakeTile));

        }
        changeTile(newSnakeTile);
    }

    /** Handles GameOver */
    private void gameOver(){
        timer.stop();
        System.out.println("-----------------------");
        JOptionPane.showMessageDialog(null, "Game Over");
        String prettyPrintedScores = highScoreRefresher.refreshHighscore(difficulties[userDifficulty],score);
        System.out.println(prettyPrintedScores);
        System.out.println("-----------------------");
        JOptionPane.showMessageDialog(null,prettyPrintedScores.replace("10: ", "10:"));
        int x = JOptionPane.showConfirmDialog(null, "Do you want to restart?",
                "",
                 JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            restart();
        } else if (x == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    /** Sets the Field up for a new game */
    private void restart(){
        initializeField();
        score = 0;
        runningFirstTime = true;
        initializeGame();
    }

    /** Places food by using random numbers */
    private void placeFood(){
        Random rand = new Random();
        byte xFood = (byte) (1+rand.nextInt( gameField[0].length-3));
        byte yFood = (byte) (1+rand.nextInt( gameField.length-3));
        while(gameField[yFood][xFood].getCollision() != CollisionType.nothing){
            xFood = (byte) (1+rand.nextInt( gameField[0].length-3));
            yFood = (byte) (1+rand.nextInt( gameField.length-3));
        }
        changeTile(gameField[yFood][xFood].changeToEatTile());
    }

    /**
     * Changes a tile into another tile
     * @param changeTo the tile, to which was changed (overwrites the tile on its x and y position)
     */
    private void changeTile(FieldTileBase changeTo){
        gameField[changeTo.getPosY()][changeTo.getPosX()] = changeTo;
        changedTiles.add(changeTo);
    }


    /**
     * Maps the game difficulties to an amount of milliseconds between ticks
     * @return returns the difficulty as a time interval as milliseconds between ticks
     */
    private int mapMillisecondsToDifficulty() {
        return switch(userDifficulty){
            case 0 -> 200;
            case 2 -> 100;
            case 3 -> 50;
            default -> 150;
        };
    }
}
