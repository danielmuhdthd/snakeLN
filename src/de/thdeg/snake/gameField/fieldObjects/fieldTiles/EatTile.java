package de.thdeg.snake.gameField.fieldObjects.fieldTiles;

import de.thdeg.snake.gameField.fieldObjects.CollisionType;
import de.thdeg.snake.gameField.fieldObjects.Color;
import de.thdeg.snake.gameField.fieldObjects.FieldTileBase;

/** Special tile, which will be eaten by the snake */
public class EatTile extends FieldTileBase {

    /**
     * Initializes a new EatTile on position x, y
     * @param posX x position of the tile
     * @param posY y position of the tile
     */
    public EatTile(byte posX, byte posY) {
        super(posX,posY);
    }

    /**
     * Gets the color of the tile (yellow, if not explicitly set before)
     * @return returns the color of the tile (yellow, if not explicitly set before)
     */
    @Override
    public Color getColor() {
        return color == null? new Color((short)255, (short)249, (short)84): color;
    }

    /**
     * Gets the collision of the tile
     * @return  Always CollisionType.food
     */
    @Override
    public CollisionType getCollision() {
        return CollisionType.food;
    }
}
