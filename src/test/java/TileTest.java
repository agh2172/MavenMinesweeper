import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {

    @Test
    public void testTileGetter(){
        Tile temp = new Tile(true, 0, 0);
        Assertions.assertEquals(0, temp.getCol(), "wrong column initialised");
    }


}
