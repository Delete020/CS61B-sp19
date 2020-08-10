package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus {


    @Test
    public void testBasics() {
        Clorus c = new Clorus(7);
        assertEquals(7, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(6.97, c.energy(), 0.01);
        c.move();
        assertEquals(6.94, c.energy(), 0.01);
        c.stay();
        assertEquals(6.93, c.energy(), 0.01);
        c.stay();
        assertEquals(6.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        // TODO
        Clorus c = new Clorus(2);
        assertNotEquals(c, c.replicate());
        assertEquals(1, c.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


        // Test attack.
        c = new Clorus(.99);
        HashMap<Direction, Occupant> allEmptys = new HashMap<Direction, Occupant>();
        allEmptys.put(Direction.TOP, new Plip());
        allEmptys.put(Direction.BOTTOM, new Empty());
        allEmptys.put(Direction.LEFT, new Empty());
        allEmptys.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmptys);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);

    }
}
