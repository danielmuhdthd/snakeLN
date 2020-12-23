package de.thdeg.snake.gameField.fieldObjects;

public class Color {

    /** Short value to store the red portion of the color */
    private final short Red;

    /** Short value to store the green portion of the color */
    private final short Green;

    /** Short value to store the blue portion of the color */
    private final short Blue;

    /** Initializes light gray by default */
    public Color(){
        //light gray
        Red = 200;
        Green = 200;
        Blue = 200;
    }

    /** Initializes a color by RGB */
    public Color(short red, short green, short blue){
        Red = red;
        Green = green;
        Blue = blue;
    }

    /**
     * Gets the short value to store the red portion of the color
     * @return returns the short value to store the red portion of the color
     */
    public short getRed() {
        return Red;
    }

    /**
     * Gets the short value to store the green portion of the color
     * @return returns the short value to store the green portion of the color
     */
    public short getGreen() {
        return Green;
    }

    /**
     * Gets the short value to store the blue portion of the color
     * @return returns the short value to store the blue portion of the color
     */
    public short getBlue() {
        return Blue;
    }
}
