import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeGold {

    @Test
    public void test() {

        /* Test addFirst method. */
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i += 1) {
            int random = StdRandom.uniform(0, 100);
            sad1.addFirst(random);
            ads1.addFirst(random);
        }
        for (int i = 0; i < 10; i += 1) {
            int actual = sad1.get(i);
            int expected = ads1.get(i);
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        /* Test removeFirst. */
        for (int i = 0; i < 10; i += 1) {
            sad1.removeFirst();
            ads1.removeFirst();
        }
        for (int i = 0; i < 10; i += 1) {
            boolean actual = sad1.isEmpty();
            boolean expected = ads1.isEmpty();
            assertEquals("Oh noooo!\nThis is bad:\n  " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }
    }
}
