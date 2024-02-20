import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void testGameInit(){
        Game minesweeper = new Game(1);
        Assertions.assertEquals(9, minesweeper.getColSize(), "wrong column size");
        Assertions.assertEquals(9, minesweeper.getRowSize(), "wrong row size");

        minesweeper.initialiseBoard(3,3);
        Assertions.assertFalse(minesweeper.getTile(3,3).isMine(), "initial click cannot be mine");

        minesweeper = new Game(2);
        Assertions.assertEquals(16, minesweeper.getColSize(), "wrong column size");
        Assertions.assertEquals(16, minesweeper.getRowSize(), "wrong row size");

        minesweeper = new Game(3);
        Assertions.assertEquals(16, minesweeper.getColSize(), "wrong column size");
        Assertions.assertEquals(30, minesweeper.getRowSize(), "wrong row size");

        minesweeper = new Game(-1);
        Assertions.assertEquals(5, minesweeper.getColSize(), "wrong column size");
        Assertions.assertEquals(5, minesweeper.getRowSize(), "wrong row size");

        Assertions.assertFalse(minesweeper.isGameOver(), "gameOver should be false");
        Assertions.assertFalse(minesweeper.isLost(), "gameLost should be false");

    }
}
