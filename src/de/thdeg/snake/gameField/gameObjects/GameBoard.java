package de.thdeg.snake.gameField.gameObjects;

import de.thdeg.snake.gameField.fieldObjects.CollisionType;
import de.thdeg.snake.gameField.fieldObjects.FieldTile;
import de.thdeg.snake.keyboardWrapper.Direction;
import de.thdeg.snake.runtime.InternalLedGameThread;

import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

public class GameBoard implements ActionListener {

    private FieldTile[][] gameField;
    private Snake snake;
    private final Timer timer;

    public GameBoard(){
        initializeField();
        timer = new Timer(500, this);
        timer.start();
    }

    private void initializeField() {
        gameField = new FieldTile[24][];
        for (byte i = 0; i < gameField.length; ++i) {
            gameField[i] = new FieldTile[48];
            for (byte j = 0; j < gameField[0].length; ++j) {
                if(i == 0 || j == 0 || i == gameField.length-1 || j == gameField[0].length-1){
                    gameField[i][j] = new FieldTile(CollisionType.border, j, i);
                }else{
                    gameField[i][j] = new FieldTile(CollisionType.nothing, j, i);
                }
            }
        }
        snake = new Snake(new ArrayList<>(Arrays.asList(gameField[10]).subList(5, 10)));
        placeFood();
        InternalLedGameThread.showImage(translateToShortArray());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        processKeyboardInput();
        switch (snake.getFacing()) {
            case up -> calculateNextTile((byte) -1, (byte) 0);
            case down -> calculateNextTile((byte) 1, (byte) 0);
            case left -> calculateNextTile((byte) 0, (byte) -1);
            case right -> calculateNextTile((byte) 0, (byte) 1);
        }
        InternalLedGameThread.showImage(translateToShortArray());
    }

    private void processKeyboardInput() {
        switch(InternalLedGameThread.getKeyboard()){
            case 0 -> snake.setFacing(Direction.up);
            case 1 -> snake.setFacing(Direction.down);
            case 2 -> snake.setFacing(Direction.left);
            case 3 -> snake.setFacing(Direction.right);
        }
    }

    public void calculateNextTile(byte relY, byte relX){
        byte nextTilePosX = (byte)(snake.getHead().getPosX()+relX), nextTilePosY = (byte)(snake.getHead().getPosY()+relY);
        FieldTile nextTile = gameField[nextTilePosY][nextTilePosX];
        switch(nextTile.getCollision()){
            case snake, border -> gameOver();
            case food -> {
                snake.eat(nextTile);
                placeFood();
            }
            case nothing -> snake.move(nextTile);
        }
    }

    public void gameOver(){
        //timer.stop();
    }

    public void restart(){

    }

    public void placeFood(){
        Random rand = new Random();
        int xFood = 1+rand.nextInt( gameField[0].length-3);
        int yFood = 1+rand.nextInt( gameField.length-3);
        while(gameField[yFood][xFood].getCollision() != CollisionType.nothing){
            xFood = 1+rand.nextInt( gameField[0].length-3);
            yFood = 1+rand.nextInt( gameField.length-3);
        }
        gameField[yFood][xFood].change(CollisionType.food);
    }

    public short[] translateToShortArray(){
        short[] ret = new short[24*48*3];
        int retCount = 0;
        for (FieldTile[] fieldTiles : gameField) {
            for (int j = 0; j < gameField[0].length; ++j) {
                ret[retCount] = fieldTiles[j].getColor().getRed();
                ret[retCount + 1] = fieldTiles[j].getColor().getGreen();
                ret[retCount + 2] = fieldTiles[j].getColor().getBlue();
                retCount += 3;
            }
        }
        return ret;
    }
}
