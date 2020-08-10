package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {

    /**
     * red color.
     */
    private final int r = 34;
    /**
     * green color.
     */
    private final int g = 0;
    /**
     * blue color.
     */
    private final int b = 231;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("Clorus");
        energy = e;
    }

    /**
     * Uses method from Occupant to return a color based on personal.
     * r, g, b values
     */
    public Color color() {
        return color(r, g, b);
    }


    /**
     * Clorus attacks another creature, gain that creature’s energy.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Clorus lose 0.03 units of energy when moving.
     */
    public void move() {
        energy -= 0.03;
    }


    /**
     * Clorus lose 0.01 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy -= 0.01;
    }

    /**
     * Clorus and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Clorus.
     */
    public Clorus replicate() {
        energy /= 2;
        return new Clorus(energy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.add(entry.getKey());
            } else if (entry.getValue().name().equals("plip")) {
                plipNeighbors.add(entry.getKey());
            }
        }

        if (emptyNeighbors.size() == 0 && plipNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (plipNeighbors.size() != 0) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        } else if (energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        } else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }
    }
}
