import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {

    @Test
    public void testTileGetter(){
        Tile temp = new Tile(true, 0, 0);
        Assertions.assertEquals(0, temp.getCol(), "wrong column initialised");
        temp.setCol(3);
        Assertions.assertEquals(3, temp.getCol(), "wrong column upon reassignment");
        Assertions.assertEquals(0, temp.getRow(), "wrong row initialised");
        temp.setRow(3);
        Assertions.assertEquals(3, temp.getCol(), "wrong row upon reassignment");
        Assertions.assertTrue(temp.isMine(), "mine is not mine");
        Assertions.assertEquals("-", temp.toString(), "wrong string for not shown mine");
        temp.setShow(true);
        Assertions.assertEquals("!", temp.toString(), "wrong string for shown mine");
        temp.setMine(false);
        Assertions.assertFalse(temp.isMine(), "mine set to not mine");
        temp.setFlag(true);
        Assertions.assertTrue(temp.isFlag(), "flag is not flag");
        Assertions.assertEquals("⚑", temp.toString(), "wrong string returned");
        temp.setFlag(false);
        temp.setCount(5);
        Assertions.assertEquals(5, temp.getCount(), "wrong count initialised");
        temp.setShow(true);
        Assertions.assertTrue(temp.isShow(), "show is false");
        Assertions.assertEquals("5", temp.toString(), "wrong string returned");
        temp.setCount(0);
        Assertions.assertEquals(" ", temp.toString(), "wrong string returned");


    }


}
