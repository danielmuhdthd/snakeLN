package de.thdeg.snake.gameField.fieldObjects;

public class FieldTile {

    private CollisionType collision;
    private Color color;
    private final byte posX, posY;

    public FieldTile(CollisionType collision, byte posX, byte posY){
        change(collision);
        this.posX = posX;
        this.posY = posY;
    }

    public void change(CollisionType collision){
        this.collision = collision;
        this.color = collision.mapColorFromCollision();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public CollisionType getCollision() {
        return collision;
    }

    public byte getPosX() {
        return posX;
    }

    public byte getPosY() {
        return posY;
    }
}
