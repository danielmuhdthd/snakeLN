package de.thdeg.snake.keyboardWrapper;

public class KeyboardConvert {
    public Direction mapKey(int key){
        return switch (key) {
            case 0 -> Direction.up;
            case 1 -> Direction.down;
            case 2 -> Direction.left;
            case 3 -> Direction.right;
            default -> null;
        };
    }
}
