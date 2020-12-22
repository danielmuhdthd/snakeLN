package de.thdeg.snake.gameField.fieldObjects;

public enum CollisionType {
    /** Snake dies, if touched */
    death,
    /** Snake eats, if touched */
    food,
    /** Snake moves, if touched */
    nothing
}
