package de.thdeg.snake.gameField.fieldObjects.fieldTiles;

import de.thdeg.snake.gameField.fieldObjects.CollisionType;
import de.thdeg.snake.gameField.fieldObjects.Color;
import de.thdeg.snake.gameField.fieldObjects.FieldTileBase;

/** Special tile, on which the snake will move */
public class BackgroundTile extends FieldTileBase {

    /**
     * Initializes a new BackgroundTile on position x, y
     * @param posX x position of the tile
     * @param posY y position of the tile
     */
    public BackgroundTile(byte posX, byte posY) {
        super(posX,posY);
    }

    /**
     * Gets the color of the tile (light gray, if not explicitly set before)
     * @return returns the color of the tile (light gray, if not explicitly set before)
     */
    @Override
    public Color getColor() {
        return color == null? new Color(): color;
    }

    /**
     * Gets the collision of the tile
     * @return  Always CollisionType.nothing
     */
    @Override
    public CollisionType getCollision() {
        return CollisionType.nothing;
    }
}
