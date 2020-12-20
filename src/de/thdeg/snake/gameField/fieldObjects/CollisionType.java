package de.thdeg.snake.gameField.fieldObjects;

public enum CollisionType {
    death, food, nothing, countdown;

    public Color mapColorFromCollision(){
        return switch(this){
            //red
            case death -> new Color((short)200, (short)0, (short)0);
            //yellow
            case food -> new Color((short)255, (short)249, (short)84);
            //black
            case countdown -> new Color((short)0, (short)0, (short)0);
            //light gray
            default -> new Color();
        };
    }
}
