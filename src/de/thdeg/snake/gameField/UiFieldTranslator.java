package de.thdeg.snake.gameField;

import de.thdeg.snake.gameField.fieldObjects.Color;
import de.thdeg.snake.gameField.fieldObjects.FieldTileBase;
import de.thdeg.snake.runtime.InternalLedGameThread;

import java.util.List;

/**
 * Translator, which converts a FieldTileBase[][] gameField in an displayable
 * short array and prints and prints it on the surface
 */
public class UiFieldTranslator {

    private short[] field = new short[24*48*3];
    /**
     * Converts a FieldTileBase[][] gameField in an displayable
     * short array and prints and prints it on the surface
     * @param gameField gameField to be converted and displayed
     */
    public void translateGameFieldToShortArray(FieldTileBase[][] gameField){
        int retCount = 0;
        for (FieldTileBase[] fieldTiles : gameField) {
            for (int j = 0; j < gameField[0].length; ++j) {
                translateTileColorToShortValues(retCount, fieldTiles[j].getColor());
                retCount += 3;
            }
        }
        synchronizeUi();
    }

    /**
     * Translates only the changes in the GameBoard on the field
     * @param changedTiles List of tiles which changed
     */
    public void translateChanges(List<FieldTileBase> changedTiles){
        for (FieldTileBase tile : changedTiles) {
            translateTileColorToShortValues(tile.getPosY()*48*3 + tile.getPosX()*3, tile.getColor());
        }
        synchronizeUi();
    }

    /**
     * Translates a the Color of a FieldTile in the short array
     * @param firstPositionInShortArray Position of the red color integer value in short array
     * @param tileColor color of the tile to set in the short array
     */
    private void translateTileColorToShortValues(int firstPositionInShortArray, Color tileColor){
        field[firstPositionInShortArray] = tileColor.getRed();
        field[firstPositionInShortArray+1] = tileColor.getGreen();
        field[firstPositionInShortArray+2] = tileColor.getBlue();
    }

    /** Synchronizes the UI with the short array */
    private void synchronizeUi() {
        InternalLedGameThread.showImage(field);
    }
}
