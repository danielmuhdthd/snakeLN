package de.thdeg.snake.keyboardWrapper;

/**
 * Wraps the integer passed by keyboard in an enum
 */
public enum Direction {

    /** left direction of the snake (keyboard value 2) */
    left,

    /** right direction of the snake (keyboard value 3) */
    right,

    /** right direction of the snake (keyboard value 0) */
    up,

    /** right direction of the snake (keyboard value 1) */
    down;

    /**
     * Maps the integer provided by the keyboard in an enum value
     * 0    -   up
     * 1    -   down
     * 2    -   left
     * 3    -   right
     * @param input integer provided by keyboard
     * @return returns the direction as an enum value
     */
    public static Direction mapFromKeyboard(int input){
        return switch(input){
            case 0 -> up;
            case 1 -> down;
            case 2 -> left;
            case 3 -> right;
            default -> null;
        };
    }

    /**
     * Returns the opposite direction of the current enum value
     * @return returns the opposite direction of the current direction as an enum value
     */
    public Direction oppositeDirection(){
        return switch(this){
            case up -> Direction.down;
            case down -> Direction.up;
            case left -> Direction.right;
            case right -> Direction.left;
        };
    }
}
