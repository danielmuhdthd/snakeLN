package de.thdeg.snake.gameField.fieldObjects;

public enum CollisionType {
    snake, border, food, nothing;

    public Color mapColorFromCollision(){
        return switch(this){
            //red
            case border -> new Color((short)200, (short)0, (short)0);
            //green
            case snake -> new Color((short)0, (short)200, (short)0);
            //yellow
            case food -> new Color((short)255, (short)249, (short)84);
            //light gray
            default -> new Color();
        };
    }
}
