package de.thdeg.snake.gameField;

import de.thdeg.snake.gameField.fieldObjects.FieldTileBase;
import de.thdeg.snake.runtime.InternalLedGameThread;

/**
 * Translator, which converts a FieldTileBase[][] gameField in an displayable
 * short array and prints and prints it on the surface
 */
public class UiFieldTranslator {

    /**
     * Converts a FieldTileBase[][] gameField in an displayable
     * short array and prints and prints it on the surface
     * @param gameField gameField to be converted and displayed
     */
    public void translateToShortArray(FieldTileBase[][] gameField){
        short[] field = new short[24*48*3];
        int retCount = 0;
        for (FieldTileBase[] fieldTiles : gameField) {
            for (int j = 0; j < gameField[0].length; ++j) {
                field[retCount] = fieldTiles[j].getColor().getRed();
                field[retCount + 1] = fieldTiles[j].getColor().getGreen();
                field[retCount + 2] = fieldTiles[j].getColor().getBlue();
                retCount += 3;
            }
        }
        InternalLedGameThread.showImage(field);
    }
}
