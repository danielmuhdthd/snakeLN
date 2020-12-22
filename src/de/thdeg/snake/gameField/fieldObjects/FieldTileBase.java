package de.thdeg.snake.gameField.fieldObjects;

import de.thdeg.snake.gameField.fieldObjects.fieldTiles.BackgroundTile;
import de.thdeg.snake.gameField.fieldObjects.fieldTiles.DeathTile;
import de.thdeg.snake.gameField.fieldObjects.fieldTiles.EatTile;

/** A tile on the gameField */
public abstract class FieldTileBase {

    /** Color of the tile */
    protected Color color;
    /** x and y position of the tile */
    private final byte posX, posY;

    /**
     * Initializes the FieldTile
     * @param posX x position of the tile
     * @param posY y position of the tile
     */
    public FieldTileBase(byte posX, byte posY){
        this.posX = posX;
        this.posY = posY;
    }

    /** Changes the tile to a death tile (quite ugly, wouldn't have done that with sub classes...) */
    public DeathTile changeToDeathTile(boolean isSnake){
        return new DeathTile(posX, posY, isSnake);
    }

    /** Changes the tile to an eat tile (quite ugly, wouldn't have done that with sub classes...) */
    public EatTile changeToEatTile(){
        return new EatTile(posX, posY);
    }

    /** Changes the tile to a background tile (quite ugly, wouldn't have done that with sub classes...) */
    public BackgroundTile changeToBackgroundTile(){
        return new BackgroundTile(posX, posY);
    }

    /** Get color of the tile */
    public abstract Color getColor();

    /** Sets the color of a tile */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the collision of a tile
     * @return returns the collision of the tile
     */
    public abstract CollisionType getCollision();

    /**
     * Gets the x position of a tile
     * @return returns the x position of a tile
     */
    public byte getPosX() {
        return posX;
    }

    /**
     * Gets the y position of a tile
     * @return returns the y position of a tile
     */
    public byte getPosY() {
        return posY;
    }
}
