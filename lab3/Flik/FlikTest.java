import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlikTest {

    @Test
    public void tsetIsSameNumber() {
        int a = 5;
        int b = 5;
        assertTrue(Flik.isSameNumber(a, b));
        assertFalse(Flik.isSameNumber(129, 129));
    }

}
