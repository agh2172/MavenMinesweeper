import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    //setup a predefined board
    public Game setup(){
        //sets all up a new game where all tiles are not bombs
        Game minesweeper = new Game(1);
        for(int i=0; i<minesweeper.getRowSize(); i++){
            for(int j=0; j< minesweeper.getColSize(); j++){
                minesweeper.setTile(i, j, false);
            }
        }
        return minesweeper;
    }

    @Test
    public void testGameInit(){
        Game minesweeper = new Game(1);
        Assertions.assertEquals(9, minesweeper.getColSize(), "wrong column size");
        Assertions.assertEquals(9, minesweeper.getRowSize(), "wrong row size");

        minesweeper = new Game(2);
        Assertions.assertEquals(16, minesweeper.getColSize(), "wrong column size");
        Assertions.assertEquals(16, minesweeper.getRowSize(), "wrong row size");

        minesweeper.initialiseBoard(3,3);
        Assertions.assertFalse(minesweeper.getTile(3,3).isMine(), "initial click cannot be mine");

        boolean flagPlaced = minesweeper.placeFlag(15,15, true);
        Assertions.assertTrue(flagPlaced, "flag not placed successfully");
        Assertions.assertTrue(minesweeper.getTile(15,15).isFlag(), "should be a flag");
        boolean badFlagPlaced = minesweeper.placeFlag(3,3, true);
        Assertions.assertFalse(badFlagPlaced, "flag place here should fail");
        Assertions.assertFalse(minesweeper.getTile(3,3).isFlag(), "should not be a flag");

        minesweeper = new Game(3);
        Assertions.assertEquals(16, minesweeper.getColSize(), "wrong column size");
        Assertions.assertEquals(30, minesweeper.getRowSize(), "wrong row size");

        minesweeper = new Game(-1);
        Assertions.assertEquals(5, minesweeper.getColSize(), "wrong column size");
        Assertions.assertEquals(5, minesweeper.getRowSize(), "wrong row size");

        Assertions.assertFalse(minesweeper.isGameOver(), "gameOver should be false");
        Assertions.assertFalse(minesweeper.isLost(), "gameLost should be false");
    }

    @Test
    public void testGameOver(){
        Game minesweeper = setup();
        minesweeper.updateBoardClick(0,0);
        Assertions.assertTrue(minesweeper.isGameOver(), "game should be over");

        minesweeper = setup();
        minesweeper.setTile(0,0, true);
        Assertions.assertEquals(-1, minesweeper.updateBoardClick(0,0), "should return -1 when bomb is clicked");
        Assertions.assertTrue(minesweeper.isGameOver(), "gameOver should be true");
        Assertions.assertTrue(minesweeper.isLost(), "lost should be true");

        Game game1 = setup();
        game1.setTile(0,0, true);
        game1.placeFlag(0,0, true);
        Assertions.assertEquals(-1, game1.updateBoardClick(0,0), "should not be able to click on a bomb");
        game1.updateBoardClick(0,1);
        Assertions.assertFalse(game1.isLost(), "lost should be false");
        Assertions.assertTrue(game1.isGameOver(), "game should be over");


    }
}
