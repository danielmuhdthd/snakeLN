package de.thdeg.snake;

import de.thdeg.snake.gameField.gameObjects.GameBoard;
import de.thdeg.snake.runtime.InternalLedGameThread;

public class SnakeMain {

    static public void main(String[] passedArgs) throws InterruptedException {
        InternalLedGameThread.run();
        Thread.sleep(1000);
        new GameBoard();
    }
}
