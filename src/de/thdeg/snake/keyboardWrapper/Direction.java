package de.thdeg.snake.keyboardWrapper;

public enum Direction {
    left, right, up, down;

    public Direction oppositeDirection(){
        return switch(this){
            case up -> Direction.down;
            case down -> Direction.up;
            case left -> Direction.right;
            case right -> Direction.left;
        };
    }
}
