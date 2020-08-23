import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    private final PriorityQueue<Flight> flightQueue;

    public FlightSolver(ArrayList<Flight> flights) {
        Comparator<Flight> flightComparator = Comparator.comparingInt(Flight::startTime);
        flightQueue = new PriorityQueue<>(flightComparator);
        flightQueue.addAll(flights);
    }

    public int solve() {
        int max = 0;
        while (!flightQueue.isEmpty()) {
            Flight peek = flightQueue.poll();
            int count = peek.passengers();
            for (Flight flight : flightQueue) {
                if (peek.endTime() >= flight.startTime()) {
                    count += flight.passengers;
                } else {
                    break;
                }
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

}
