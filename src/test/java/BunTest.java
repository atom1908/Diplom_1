import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.*;

public class BunTest {

    private Bun bun = new Bun("name", 10);
    @Test
    public void getName() {
        assertEquals("name", bun.getName());
    }

    @Test
    public void getPrice() {
        assertEquals(10, bun.getPrice(), 0);
    }
}