package de.thdeg.snake.gameField.gameObjects;

import de.thdeg.snake.gameField.fieldObjects.FieldTileBase;
import de.thdeg.snake.gameField.fieldObjects.fieldTiles.*;
import de.thdeg.snake.keyboardWrapper.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Class, which manages the snake and its tiles
 */
public class Snake {

    /** The tiles of the snake */
    private final List<DeathTile> snakeTiles = new ArrayList<>();

    /** The direction, in which the snake is currently facing */
    private Direction facing = Direction.right;


    /**
     * Initializes a new snake with the given tiles
     * @param snakeTiles tiles of the snake of which it is initialized (already needed to be converted in a DeathTile)
     */
    public Snake(List<DeathTile> snakeTiles){
        this.snakeTiles.addAll(snakeTiles);
    }

    /**
     * Snakes moves one tile
     * @param nextTile Tile the snake moves to (already needs to be converted in a DeathTile)
     * @return returns the tail death tile of the snake
     */
    public BackgroundTile move(DeathTile nextTile){
        snakeTiles.add(nextTile);
        DeathTile removed = snakeTiles.remove(0);
        return removed.changeToBackgroundTile();
    }

    /**
     * Lets the snake eat the given tile, which already needs to be converted in a DeathTile
     * @param nextTile Tile, which the snake eats (already needs to be converted in a DeathTile)
     */
    public void eat(DeathTile nextTile){
        snakeTiles.add(nextTile);
    }

    /**
     * Gets the facing of the snake
     * @return returns the facing of the snake
     */
    public Direction getFacing() {
        return facing;
    }

    /**
     * Gets the head of the snake
     * @return returns the head tile of the snake
     */
    public FieldTileBase getHead(){
        return snakeTiles.get(snakeTiles.size()-1);
    }

    /**
     * Sets the facing of the snake (does nothing, if the facing is set to the opposite direction)
     * @param facing facing the snake is set to
     */
    public void setFacing(Direction facing) {
        if(facing == null || this.facing == facing.oppositeDirection()){
            return;
        }
        this.facing = facing;
    }
}
