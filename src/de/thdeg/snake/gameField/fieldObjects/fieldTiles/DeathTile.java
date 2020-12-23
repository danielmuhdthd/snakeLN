package de.thdeg.snake.gameField.fieldObjects.fieldTiles;

import de.thdeg.snake.gameField.fieldObjects.CollisionType;
import de.thdeg.snake.gameField.fieldObjects.Color;
import de.thdeg.snake.gameField.fieldObjects.FieldTileBase;

/** Special tile, on which the snake will die */
public class DeathTile extends FieldTileBase {

    /** Used to determine, if the obstacle is the snake itself */
    private final boolean isSnake;

    /**
     * Initializes a new EatTile on position x, y
     * @param posX x position of the tile
     * @param posY y position of the tile
     * @param isSnake Used to store, if the deathTile is the snake itself
     */
    public DeathTile(byte posX, byte posY, boolean isSnake) {
        super(posX,posY);
        this.isSnake = isSnake;
    }

    /**
     * Gets the color of the tile (red, if not explicitly set before and green, if it's the snake)
     * @return returns the color of the tile (red, if not explicitly set before and green, if it's the snake)
     */
    @Override
    public Color getColor() {
        if(color == null) {
            return isSnake ? new Color((short) 0, (short) 200, (short) 0)
                    : new Color((short) 200, (short) 0, (short) 0);
        }
        return color;
    }

    /**
     * Gets the collision of the tile
     * @return  Always CollisionType.death
     */
    @Override
    public CollisionType getCollision() {
        return CollisionType.death;
    }
}
