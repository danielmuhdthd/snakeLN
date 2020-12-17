package de.thdeg.snake.gameField.gameObjects;

import de.thdeg.snake.gameField.fieldObjects.CollisionType;
import de.thdeg.snake.gameField.fieldObjects.FieldTile;
import de.thdeg.snake.keyboardWrapper.Direction;

import java.util.List;
import java.util.stream.IntStream;

public class Snake {

    private final List<FieldTile> snakeTiles;
    private Direction facing = Direction.right;

    public Snake(List<FieldTile> snakeTiles){
        this.snakeTiles = snakeTiles;
        //sets the snake on the surface
        IntStream.range(0, snakeTiles.size()).forEach(i -> snakeTiles.get(i).change(CollisionType.snake));
    }

    public void move(FieldTile nextTile){
        snakeifyTile(nextTile);
        FieldTile removed = snakeTiles.remove(0);
        removed.change(CollisionType.nothing);
    }

    public void eat(FieldTile foodTile){
        snakeifyTile(foodTile);
    }

    @SuppressWarnings("SpellCheckingInspection")
    private void snakeifyTile(FieldTile tile){
        tile.change(CollisionType.snake);
        snakeTiles.add(tile);
    }

    public Direction getFacing() {
        return facing;
    }

    public FieldTile getHead(){
        return snakeTiles.get(snakeTiles.size()-1);
    }

    public void setFacing(Direction facing) {
        if(this.facing == facing.oppositeDirection()){
            return;
        }
        this.facing = facing;
    }
}
