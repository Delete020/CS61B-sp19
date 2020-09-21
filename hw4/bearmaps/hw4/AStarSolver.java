package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;
    private int numStatesExplored;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();

        ArrayHeapMinPQ<Vertex> fringe = new ArrayHeapMinPQ<>();
        Map<Vertex, Double> distTo = new HashMap<>();
        Map<Vertex, Vertex> edgeTo = new HashMap<>();
        solution = new LinkedList<>();

        distTo.put(start, 0.0);
        fringe.add(start, distTo.get(start));

        while (fringe.size() != 0) {
            if (timeSpent > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                solutionWeight = 0;
                solution.clear();
                return;
            }

            Vertex smallest = fringe.removeSmallest();
            solution.add(smallest);
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(smallest);

            if (smallest.equals(end)) {
                solutionWeight = distTo.get(smallest);
                outcome = SolverOutcome.SOLVED;
                timeSpent = sw.elapsedTime();
                return;
            }

            for (WeightedEdge<Vertex> e : neighborEdges) {
                Vertex p = e.from();
                Vertex q = e.to();
                double w = e.weight();

                if (!fringe.contains(q)) {
                    double INF = Double.POSITIVE_INFINITY;
                    distTo.put(q, INF);
                }

                if (distTo.get(p) + w < distTo.get(q)) {
                    distTo.put(q, distTo.get(p) + w);
                    edgeTo.put(q, p);

                    double estimatedDistanceToGoal = input.estimatedDistanceToGoal(q, end);
                    if (fringe.contains(q)) {
                        fringe.changePriority(q, distTo.get(q) + estimatedDistanceToGoal);
                    } else {
                        fringe.add(q, distTo.get(q) + estimatedDistanceToGoal);
                    }
                }
            }

            numStatesExplored++;
            timeSpent = sw.elapsedTime();
        }

        outcome = SolverOutcome.UNSOLVABLE;
        solution.clear();
        timeSpent = sw.elapsedTime();
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
