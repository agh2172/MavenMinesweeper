import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

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
        game1.placeFlag(0,0, true);
        Assertions.assertEquals(1, game1.expand(game1.getTile(0,0)), "expand should return 1 for flag");
        game1.setTile(0,0, true);
        game1.placeFlag(0,0, true);


        game1.updateBoardClick(0,1);
        Assertions.assertFalse(game1.isLost(), "lost should be false");
        Assertions.assertTrue(game1.isGameOver(), "game should be over");
    }


    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testPlay(){
        Game minesweeper = new Game(1);

        int[] coordinates = new int[2];
        provideInput("a\n0\na\n0\n");
        coordinates = Main.coordinates(minesweeper);
        Assertions.assertEquals(0, coordinates[0], "x should be 0");
        Assertions.assertEquals(0, coordinates[1], "y should be 0");

    }

    @Test
    public void testIntro(){
        int difficulty = 1;
        String diff = Main.printIntro(difficulty);
        Assertions.assertEquals("easy", diff, "should be easy");


        difficulty = 2;
        diff = Main.printIntro(difficulty);
        Assertions.assertEquals("intermediate", diff, "should be intermediate");

        difficulty = 3;
        diff = Main.printIntro(difficulty);
        Assertions.assertEquals("ULTIMATE MINESWEEPER", diff, "should be ULTIMATE MINESWEEPER");

        difficulty = -1;
        diff = Main.printIntro(difficulty);
        Assertions.assertEquals("testing mode - for devs only", diff, "should be testing mode");

        difficulty = 0;
        diff = Main.printIntro(difficulty);
        Assertions.assertEquals("easy", diff, "should be default");
    }

    @Test
    public void testEndGameState(){
        Game minesweeper = setup();
        //win game scenario
        minesweeper.updateBoardClick(0,0);
        Assertions.assertTrue(minesweeper.isGameOver(), "game should be over");
        //String expectedOutput = "[4;32mCONGRATULATIONS, you have cleared the minefield!";
        boolean won = Main.endGame(minesweeper);
        Assertions.assertTrue(won, "should be won");
        //Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim(), "should be the same output");
    }

/*    @Test
    public void testInput(){
        Game minesweeper = new Game(1);

        provideInput("f\n3\n3\n");
        Main.get_move(minesweeper);
        Assertions.assertTrue(minesweeper.getTile(3,3).isFlag(), "should be a flag");
    }*/

/*    @Test
    public void testOutput(){
        Game minesweeper = setup();
        String expectedOutput =
                "|    0    |    1    |    2    |    3    |    4    |    5    |    6    |    7    |    8    |\n" +
                "----+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n" +
                "  0 |        -|        -|        -|        -|        -|        -|        -|        -|        -|\n" +
                "----+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n" +
                "  1 |        -|        -|        -|        -|        -|        -|        -|        -|        -|\n" +
                "----+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n" +
                "  2 |        -|        -|        -|        -|        -|        -|        -|        -|        -|\n" +
                "----+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n" +
                "  3 |        -|        -|        -|        -|        -|        -|        -|        -|        -|\n" +
                "----+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n" +
                "  4 |        -|        -|        -|        -|        -|        -|        -|        -|        -|\n" +
                "----+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n" +
                "  5 |        -|        -|        -|        -|        -|        -|        -|        -|        -|\n" +
                "----+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n" +
                "  6 |        -|        -|        -|        -|        -|        -|        -|        -|        -|\n" +
                "----+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n" +
                "  7 |        -|        -|        -|        -|        -|        -|        -|        -|        -|\n" +
                "----+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n" +
                "  8 |        -|        -|        -|        -|        -|        -|        -|        -|        -|";

        minesweeper.printBoard();
        Assertions.assertEquals(outputStreamCaptor.toString().trim(), expectedOutput, "should be the same output");
    }*/
}
