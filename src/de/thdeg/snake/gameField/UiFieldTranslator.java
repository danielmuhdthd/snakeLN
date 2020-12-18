package de.thdeg.snake.gameField;

import de.thdeg.snake.gameField.fieldObjects.FieldTile;
import de.thdeg.snake.runtime.InternalLedGameThread;

public class UiFieldTranslator {

    public void translateToShortArray(FieldTile[][] gameField){
        short[] field = new short[24*48*3];
        int retCount = 0;
        for (FieldTile[] fieldTiles : gameField) {
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
