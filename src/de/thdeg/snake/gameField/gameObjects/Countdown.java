package de.thdeg.snake.gameField.gameObjects;

import de.thdeg.snake.gameField.UiFieldTranslator;
import de.thdeg.snake.gameField.fieldObjects.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the countdown in the beginning of the game
 */
@SuppressWarnings("DuplicatedCode")
public class Countdown implements ActionListener {

    /**
     * The countdown can be set to a specific absolute screen position, although is always centered around "BEGIN IN..."
     */
    private final int absPosX, absPosY;
    /**
     * The gameField the countdown is printed on
     */
    private final FieldTileBase[][] gameField;
    /**
     * Used to translate a FieldTile[][] to an displayable short array and displays it
     */
    private final UiFieldTranslator translator;
    /**
     * Used to determine the stage of the countdown
     */
    private int stage = 0;
    /**
     * Timer, which times the countdown
     */
    private final Timer timer = new Timer(1000, this), gameBoardTimer;
    /**
     * Color of the countdown
     */
    private final Color countDownColor = new Color((short) 0, (short) 0, (short) 0);
    /**
     * Changed Tiles in the current tick
     */
    private final List<FieldTileBase> changedTiles = new ArrayList<>();

    /**
     * Initializes the countdown
     *
     * @param x              x position on which the countdown will be placed
     * @param y              x position on which the countdown will be placed
     * @param gameField      gameField on which the countdown will be placed
     * @param translator     Used to translate a FieldTile[][] to an displayable short array and displays it
     * @param gameBoardTimer Timer of the gameBoard, which is started at the end of the countdown
     */
    public Countdown(int x, int y, FieldTileBase[][] gameField, UiFieldTranslator translator, Timer gameBoardTimer) {
        this.gameBoardTimer = gameBoardTimer;
        this.translator = translator;
        absPosX = x;
        absPosY = y;
        this.gameField = gameField;
        firstStage(countDownColor);
        translator.translateChanges(changedTiles);
    }

    /**
     * Starts the countdown
     */
    public void doCountdown() {
        timer.start();
    }

    /**
     * Method which is called periodically by the timer
     *
     * @param e ignored
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        changedTiles.clear();
        switch (stage) {
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
        translator.translateChanges(changedTiles);
        if (stage != 5) {
            return;
        }
        timer.stop();
        gameBoardTimer.start();

    }


    /**
     * Prints "BEGIN IN..."
     *
     * @param color color of the displayed text
     */
    private void firstStage(Color color) {
        setColorToPosition(0, 0, color);
        setColorToPosition(0, 1, color);
        setColorToPosition(0, 4, color);
        setColorToPosition(0, 5, color);
        setColorToPosition(0, 6, color);
        setColorToPosition(0, 9, color);
        setColorToPosition(0, 10, color);
        setColorToPosition(0, 11, color);
        setColorToPosition(0, 13, color);
        setColorToPosition(0, 15, color);
        setColorToPosition(0, 18, color);
        setColorToPosition(0, 23, color);
        setColorToPosition(0, 25, color);
        setColorToPosition(0, 28, color);
        setColorToPosition(1, 0, color);
        setColorToPosition(1, 2, color);
        setColorToPosition(1, 4, color);
        setColorToPosition(1, 8, color);
        setColorToPosition(1, 13, color);
        setColorToPosition(1, 15, color);
        setColorToPosition(1, 16, color);
        setColorToPosition(1, 18, color);
        setColorToPosition(1, 23, color);
        setColorToPosition(1, 25, color);
        setColorToPosition(1, 26, color);
        setColorToPosition(1, 28, color);
        setColorToPosition(2, 0, color);
        setColorToPosition(2, 1, color);
        setColorToPosition(2, 4, color);
        setColorToPosition(2, 5, color);
        setColorToPosition(2, 8, color);
        setColorToPosition(2, 10, color);
        setColorToPosition(2, 11, color);
        setColorToPosition(2, 13, color);
        setColorToPosition(2, 15, color);
        setColorToPosition(2, 16, color);
        setColorToPosition(2, 17, color);
        setColorToPosition(2, 18, color);
        setColorToPosition(2, 23, color);
        setColorToPosition(2, 25, color);
        setColorToPosition(2, 26, color);
        setColorToPosition(2, 27, color);
        setColorToPosition(2, 28, color);
        setColorToPosition(3, 0, color);
        setColorToPosition(3, 2, color);
        setColorToPosition(3, 4, color);
        setColorToPosition(3, 8, color);
        setColorToPosition(3, 11, color);
        setColorToPosition(3, 13, color);
        setColorToPosition(3, 15, color);
        setColorToPosition(3, 17, color);
        setColorToPosition(3, 18, color);
        setColorToPosition(3, 23, color);
        setColorToPosition(3, 25, color);
        setColorToPosition(3, 27, color);
        setColorToPosition(3, 28, color);
        setColorToPosition(4, 0, color);
        setColorToPosition(4, 1, color);
        setColorToPosition(4, 4, color);
        setColorToPosition(4, 5, color);
        setColorToPosition(4, 6, color);
        setColorToPosition(4, 9, color);
        setColorToPosition(4, 10, color);
        setColorToPosition(4, 13, color);
        setColorToPosition(4, 15, color);
        setColorToPosition(4, 18, color);
        setColorToPosition(4, 23, color);
        setColorToPosition(4, 25, color);
        setColorToPosition(4, 28, color);
        setColorToPosition(4, 30, color);
        setColorToPosition(4, 32, color);
        setColorToPosition(4, 34, color);
    }

    /**
     * Prints "3..."
     *
     * @param color color of the displayed text
     */
    private void secondStage(Color color) {
        setColorToPosition(0, 13, color);
        setColorToPosition(0, 14, color);
        setColorToPosition(0, 15, color);
        setColorToPosition(1, 15, color);
        setColorToPosition(2, 13, color);
        setColorToPosition(2, 14, color);
        setColorToPosition(2, 15, color);
        setColorToPosition(3, 15, color);
        setColorToPosition(4, 13, color);
        setColorToPosition(4, 14, color);
        setColorToPosition(4, 15, color);
        setColorToPosition(4, 17, color);
        setColorToPosition(4, 19, color);
        setColorToPosition(4, 21, color);
    }

    /**
     * Prints "2..."
     *
     * @param color color of the displayed text
     */
    private void thirdStage(Color color) {
        setColorToPosition(0, 13, color);
        setColorToPosition(0, 14, color);
        setColorToPosition(0, 15, color);
        setColorToPosition(1, 15, color);
        setColorToPosition(2, 13, color);
        setColorToPosition(2, 14, color);
        setColorToPosition(2, 15, color);
        setColorToPosition(3, 13, color);
        setColorToPosition(4, 13, color);
        setColorToPosition(4, 14, color);
        setColorToPosition(4, 15, color);
        setColorToPosition(4, 17, color);
        setColorToPosition(4, 19, color);
        setColorToPosition(4, 21, color);
    }

    /**
     * Prints "1..."
     *
     * @param color color of the displayed text
     */
    private void forthStage(Color color) {
        setColorToPosition(0, 15, color);
        setColorToPosition(1, 14, color);
        setColorToPosition(1, 15, color);
        setColorToPosition(2, 15, color);
        setColorToPosition(3, 15, color);
        setColorToPosition(4, 15, color);
        setColorToPosition(4, 17, color);
        setColorToPosition(4, 19, color);
        setColorToPosition(4, 21, color);
    }

    /**
     * Prints "GO!"
     *
     * @param color color of the displayed text
     */
    private void fifthStage(Color color) {
        setColorToPosition(0, 13, color);
        setColorToPosition(0, 14, color);
        setColorToPosition(0, 15, color);
        setColorToPosition(0, 18, color);
        setColorToPosition(0, 19, color);
        setColorToPosition(0, 22, color);
        setColorToPosition(1, 12, color);
        setColorToPosition(1, 17, color);
        setColorToPosition(1, 20, color);
        setColorToPosition(1, 22, color);
        setColorToPosition(2, 12, color);
        setColorToPosition(2, 14, color);
        setColorToPosition(2, 15, color);
        setColorToPosition(2, 17, color);
        setColorToPosition(2, 20, color);
        setColorToPosition(2, 22, color);
        setColorToPosition(3, 12, color);
        setColorToPosition(3, 15, color);
        setColorToPosition(3, 17, color);
        setColorToPosition(3, 20, color);
        setColorToPosition(4, 13, color);
        setColorToPosition(4, 14, color);
        setColorToPosition(4, 18, color);
        setColorToPosition(4, 19, color);
        setColorToPosition(4, 22, color);
    }

    private void setColorToPosition(int y, int x, Color color) {
        FieldTileBase tile = gameField[absPosY + y][absPosX + x];
        changedTiles.add(tile);
        tile.setColor(color);
    }
}
