package de.thdeg.snake.gameField.fieldObjects;

public class Color {
    private final short Red;
    private final short Green;
    private final short Blue;

    public Color(){
        //light gray
        Red = 200;
        Green = 200;
        Blue = 200;
    }

    public Color(short red, short green, short blue){
        Red = red;
        Green = green;
        Blue = blue;
    }

    public short getRed() {
        return Red;
    }

    public short getGreen() {
        return Green;
    }

    public short getBlue() {
        return Blue;
    }
}
