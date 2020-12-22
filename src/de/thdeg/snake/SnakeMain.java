package de.thdeg.snake;

import de.thdeg.snake.gameField.gameObjects.GameBoard;
import de.thdeg.snake.runtime.InternalLedGameThread;

/**
 * Main class, which starts the program
 */
public class SnakeMain {
    /**
     * Main method, which runs the program
     * @param passedArgs Parameter is ignored further on
     * @throws InterruptedException Program may throw exception, if sleep is interrupted
     */
    static public void main(String[] passedArgs) throws InterruptedException {
        InternalLedGameThread.run();
        Thread.sleep(3000);
        new GameBoard();
    }
}
