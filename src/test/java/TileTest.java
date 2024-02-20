import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {

    @Test
    public void testTileGetter(){
        Tile temp = new Tile(true, 0, 0);
        Assertions.assertEquals(0, temp.getCol(), "wrong column initialised");
        Assertions.assertEquals(0, temp.getRow(), "wrong row initialised");
        Assertions.assertTrue(temp.isMine(), "mine is not mine");
        temp.setMine(false);
        Assertions.assertFalse(temp.isMine(), "mine set to not mine");
        temp.setFlag(true);
        Assertions.assertTrue(temp.isFlag(), "flag is not flag");
        Assertions.assertEquals("⚑", temp.toString(), "wrong string returned");
    }


}
