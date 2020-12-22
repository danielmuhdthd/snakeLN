package de.thdeg.snake.gameField.gameObjects;

import de.thdeg.snake.gameField.UiFieldTranslator;
import de.thdeg.snake.gameField.fieldObjects.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Manages the countdown in the beginning of the game
 */
public class Countdown implements ActionListener {

    /** The countdown can be set to a specific absolute screen position, although is always centered around "BEGIN IN..." */
    private final int absPosX, absPosY;
    /** The gameField the countdown is printed on */
    private final FieldTileBase[][] gameField;
    /** Used to translate a FieldTile[][] to an displayable short array and displays it */
    private final UiFieldTranslator translator;
    /** Used to determine the stage of the countdown */
    private int stage = 0;
    /** Timer, which times the countdown */
    private final Timer timer = new Timer(1000, this), gameBoardTimer;
    /** Color of the countdown */
    private final Color countDownColor = new Color((short)0, (short)0, (short)0);

    /**
     * Initializes the countdown
     * @param x x position on which the countdown will be placed
     * @param y x position on which the countdown will be placed
     * @param gameField gameField on which the countdown will be placed
     * @param translator Used to translate a FieldTile[][] to an displayable short array and displays it
     * @param gameBoardTimer Timer of the gameBoard, which is started at the end of the countdown
     */
    public Countdown(int x, int y, FieldTileBase[][] gameField, UiFieldTranslator translator, Timer gameBoardTimer){
        this.gameBoardTimer = gameBoardTimer;
        this.translator = translator;
        absPosX = x;
        absPosY = y;
        this.gameField=gameField;
        firstStage(countDownColor);
    }

    /** Starts the countdown */
    public void doCountdown() {
        timer.start();
    }

    /**
     * Method which is called periodically by the timer
     * @param e ignored
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(stage){
            case 0 -> {
                firstStage(new Color());
                secondStage(countDownColor);
            }
            case 1 -> {
                secondStage(new Color());
                thirdStage(countDownColor);
            }
            case 2 -> {
                thirdStage(new Color());
                forthStage(countDownColor);
            }
            case 3 -> {
                forthStage(new Color());
                fifthStage(countDownColor);
            }
            case 4 -> fifthStage(new Color());

        }
        ++stage;
        if(stage == 5){
            timer.stop();
            gameBoardTimer.start();
        }
    }


    /**
     * Prints "BEGIN IN..."
     * @param color color of the displayed text
     */
    private void firstStage(Color color){
        gameField[absPosY+0][absPosX+0].setColor(color);
        gameField[absPosY+0][absPosX+1].setColor(color);
        gameField[absPosY+0][absPosX+4].setColor(color);
        gameField[absPosY+0][absPosX+5].setColor(color);
        gameField[absPosY+0][absPosX+6].setColor(color);
        gameField[absPosY+0][absPosX+9].setColor(color);
        gameField[absPosY+0][absPosX+10].setColor(color);
        gameField[absPosY+0][absPosX+11].setColor(color);
        gameField[absPosY+0][absPosX+13].setColor(color);
        gameField[absPosY+0][absPosX+15].setColor(color);
        gameField[absPosY+0][absPosX+18].setColor(color);
        gameField[absPosY+0][absPosX+23].setColor(color);
        gameField[absPosY+0][absPosX+25].setColor(color);
        gameField[absPosY+0][absPosX+28].setColor(color);
        gameField[absPosY+1][absPosX+0].setColor(color);
        gameField[absPosY+1][absPosX+2].setColor(color);
        gameField[absPosY+1][absPosX+4].setColor(color);
        gameField[absPosY+1][absPosX+8].setColor(color);
        gameField[absPosY+1][absPosX+13].setColor(color);
        gameField[absPosY+1][absPosX+15].setColor(color);
        gameField[absPosY+1][absPosX+16].setColor(color);
        gameField[absPosY+1][absPosX+18].setColor(color);
        gameField[absPosY+1][absPosX+23].setColor(color);
        gameField[absPosY+1][absPosX+25].setColor(color);
        gameField[absPosY+1][absPosX+26].setColor(color);
        gameField[absPosY+1][absPosX+28].setColor(color);
        gameField[absPosY+2][absPosX+0].setColor(color);
        gameField[absPosY+2][absPosX+1].setColor(color);
        gameField[absPosY+2][absPosX+4].setColor(color);
        gameField[absPosY+2][absPosX+5].setColor(color);
        gameField[absPosY+2][absPosX+8].setColor(color);
        gameField[absPosY+2][absPosX+10].setColor(color);
        gameField[absPosY+2][absPosX+11].setColor(color);
        gameField[absPosY+2][absPosX+13].setColor(color);
        gameField[absPosY+2][absPosX+15].setColor(color);
        gameField[absPosY+2][absPosX+16].setColor(color);
        gameField[absPosY+2][absPosX+17].setColor(color);
        gameField[absPosY+2][absPosX+18].setColor(color);
        gameField[absPosY+2][absPosX+23].setColor(color);
        gameField[absPosY+2][absPosX+25].setColor(color);
        gameField[absPosY+2][absPosX+26].setColor(color);
        gameField[absPosY+2][absPosX+27].setColor(color);
        gameField[absPosY+2][absPosX+28].setColor(color);
        gameField[absPosY+3][absPosX+0].setColor(color);
        gameField[absPosY+3][absPosX+2].setColor(color);
        gameField[absPosY+3][absPosX+4].setColor(color);
        gameField[absPosY+3][absPosX+8].setColor(color);
        gameField[absPosY+3][absPosX+11].setColor(color);
        gameField[absPosY+3][absPosX+13].setColor(color);
        gameField[absPosY+3][absPosX+15].setColor(color);
        gameField[absPosY+3][absPosX+17].setColor(color);
        gameField[absPosY+3][absPosX+18].setColor(color);
        gameField[absPosY+3][absPosX+23].setColor(color);
        gameField[absPosY+3][absPosX+25].setColor(color);
        gameField[absPosY+3][absPosX+27].setColor(color);
        gameField[absPosY+3][absPosX+28].setColor(color);
        gameField[absPosY+4][absPosX+0].setColor(color);
        gameField[absPosY+4][absPosX+1].setColor(color);
        gameField[absPosY+4][absPosX+4].setColor(color);
        gameField[absPosY+4][absPosX+5].setColor(color);
        gameField[absPosY+4][absPosX+6].setColor(color);
        gameField[absPosY+4][absPosX+9].setColor(color);
        gameField[absPosY+4][absPosX+10].setColor(color);
        gameField[absPosY+4][absPosX+13].setColor(color);
        gameField[absPosY+4][absPosX+15].setColor(color);
        gameField[absPosY+4][absPosX+18].setColor(color);
        gameField[absPosY+4][absPosX+23].setColor(color);
        gameField[absPosY+4][absPosX+25].setColor(color);
        gameField[absPosY+4][absPosX+28].setColor(color);
        gameField[absPosY+4][absPosX+30].setColor(color);
        gameField[absPosY+4][absPosX+32].setColor(color);
        gameField[absPosY+4][absPosX+34].setColor(color);
        translator.translateToShortArray(gameField);
    }

    /**
     * Prints "3..."
     * @param color color of the displayed text
     */
    private void secondStage(Color color){
        gameField[absPosY+0][absPosX+13].setColor(color);
        gameField[absPosY+0][absPosX+14].setColor(color);
        gameField[absPosY+0][absPosX+15].setColor(color);
        gameField[absPosY+1][absPosX+15].setColor(color);
        gameField[absPosY+2][absPosX+13].setColor(color);
        gameField[absPosY+2][absPosX+14].setColor(color);
        gameField[absPosY+2][absPosX+15].setColor(color);
        gameField[absPosY+3][absPosX+15].setColor(color);
        gameField[absPosY+4][absPosX+13].setColor(color);
        gameField[absPosY+4][absPosX+14].setColor(color);
        gameField[absPosY+4][absPosX+15].setColor(color);
        gameField[absPosY+4][absPosX+17].setColor(color);
        gameField[absPosY+4][absPosX+19].setColor(color);
        gameField[absPosY+4][absPosX+21].setColor(color);
        translator.translateToShortArray(gameField);
    }

    /**
     * Prints "2..."
     * @param color color of the displayed text
     */
    private void thirdStage(Color color){
        gameField[absPosY+0][absPosX+13].setColor(color);
        gameField[absPosY+0][absPosX+14].setColor(color);
        gameField[absPosY+0][absPosX+15].setColor(color);
        gameField[absPosY+1][absPosX+15].setColor(color);
        gameField[absPosY+2][absPosX+13].setColor(color);
        gameField[absPosY+2][absPosX+14].setColor(color);
        gameField[absPosY+2][absPosX+15].setColor(color);
        gameField[absPosY+3][absPosX+13].setColor(color);
        gameField[absPosY+4][absPosX+13].setColor(color);
        gameField[absPosY+4][absPosX+14].setColor(color);
        gameField[absPosY+4][absPosX+15].setColor(color);
        gameField[absPosY+4][absPosX+17].setColor(color);
        gameField[absPosY+4][absPosX+19].setColor(color);
        gameField[absPosY+4][absPosX+21].setColor(color);
        translator.translateToShortArray(gameField);
    }

    /**
     * Prints "1..."
     * @param color color of the displayed text
     */
    private void forthStage(Color color){
        gameField[absPosY+0][absPosX+15].setColor(color);
        gameField[absPosY+1][absPosX+14].setColor(color);
        gameField[absPosY+1][absPosX+15].setColor(color);
        gameField[absPosY+2][absPosX+15].setColor(color);
        gameField[absPosY+3][absPosX+15].setColor(color);
        gameField[absPosY+4][absPosX+15].setColor(color);
        gameField[absPosY+4][absPosX+17].setColor(color);
        gameField[absPosY+4][absPosX+19].setColor(color);
        gameField[absPosY+4][absPosX+21].setColor(color);
        translator.translateToShortArray(gameField);
    }

    /**
     * Prints "GO!"
     * @param color color of the displayed text
     */
    private void fifthStage(Color color){
        gameField[absPosY+0][absPosX+13].setColor(color);
        gameField[absPosY+0][absPosX+14].setColor(color);
        gameField[absPosY+0][absPosX+15].setColor(color);
        gameField[absPosY+0][absPosX+18].setColor(color);
        gameField[absPosY+0][absPosX+19].setColor(color);
        gameField[absPosY+0][absPosX+22].setColor(color);
        gameField[absPosY+1][absPosX+12].setColor(color);
        gameField[absPosY+1][absPosX+17].setColor(color);
        gameField[absPosY+1][absPosX+20].setColor(color);
        gameField[absPosY+1][absPosX+22].setColor(color);
        gameField[absPosY+2][absPosX+12].setColor(color);
        gameField[absPosY+2][absPosX+14].setColor(color);
        gameField[absPosY+2][absPosX+15].setColor(color);
        gameField[absPosY+2][absPosX+17].setColor(color);
        gameField[absPosY+2][absPosX+20].setColor(color);
        gameField[absPosY+2][absPosX+22].setColor(color);
        gameField[absPosY+3][absPosX+12].setColor(color);
        gameField[absPosY+3][absPosX+15].setColor(color);
        gameField[absPosY+3][absPosX+17].setColor(color);
        gameField[absPosY+3][absPosX+20].setColor(color);
        gameField[absPosY+4][absPosX+13].setColor(color);
        gameField[absPosY+4][absPosX+14].setColor(color);
        gameField[absPosY+4][absPosX+18].setColor(color);
        gameField[absPosY+4][absPosX+19].setColor(color);
        gameField[absPosY+4][absPosX+22].setColor(color);
        translator.translateToShortArray(gameField);
    }
}
