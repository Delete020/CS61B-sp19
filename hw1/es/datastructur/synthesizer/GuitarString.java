package es.datastructur.synthesizer;

import java.util.Random;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /**
     * Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime.
     */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private final BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer((int) Math.round(SR / frequency));
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        double[] doubles = new Random().doubles(-0.5, 0.5).
                distinct().limit(buffer.capacity()).toArray();
        for (double v : doubles) {
            buffer.dequeue();
            buffer.enqueue(v);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double ptr = buffer.dequeue();
        buffer.enqueue(DECAY * 0.5 * (ptr + buffer.peek()));
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
