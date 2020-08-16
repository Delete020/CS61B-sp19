import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BubbleGridTest {

    @Test
    public void testBasic() {

        int[][] grid = {{1, 0, 0, 0},
                {1, 1, 1, 0}};
        int[][] darts = {{1, 0}};
        int[] expected = {2};

        validate(grid, darts, expected);


        int[][] grid1 = {{1, 1, 0},
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 1}};
        int[][] darts1 = {{2, 2}, {2, 0}};
        int[] expected1 = {0, 4};

        validate(grid1, darts1, expected1);
    }

    private void validate(int[][] grid, int[][] darts, int[] expected) {
        BubbleGrid sol = new BubbleGrid(grid);
        assertArrayEquals(expected, sol.popBubbles(darts));
    }
}
