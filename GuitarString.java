// Xiaoyu Zhou
// 01/22/2021
// CSE 143
// Section: AP
// TA: Hitesh Boinpally
// Assignment #2
// This part of the program will model a vibrating guitar string of a given frequency.
import java.util.*;

public class GuitarString {
    // The global constant double to store the factor of energy decay
    public static final double ENERGY_DECAY_FACTOR = 0.996;

    // The field of variable for this class.
    private Queue<Double> ringBuffer;
    private double size;
    private Random r;

    // Pre: If the frequency is less than or equal to 0 or if the size of
    //      the ring buffer is less than 2, throws an IllegalArgumentException.
    // Post: This method will construct a guitar string of the given frequency.
    // Parameter:
    //      double frequency - the frequency given by users.
    public GuitarString(double frequency) {
        ringBuffer = new LinkedList<Double>();
        r = new Random();
        size = 0.0;
        check(frequency);
        
        for (int repeat = 0; repeat < getSizeN(frequency); repeat++) {
            ringBuffer.add(0.0);
        }
    }

    // Pre: If the array has fewer than two elements, throws an IllegalArgumentException.
    // Post: This method will Constructs a GuitarString and initializes 
    //       the contents of the ring buffer to the values in the array.
    // Parameter: 
    //      double[] init - the initial array given by users
    public GuitarString(double[] init) {
        r = new Random();
        size = 0.0;
        ringBuffer = new LinkedList<Double>();
        if (init.length < 2) {
            throw new IllegalArgumentException();
        }
        for (int index = 0; index < init.length; index++) {
            ringBuffer.add(init[index]);
        }
    }

    // Post: This method should replace the N elements in the ring buffer with N random 
    //       values between -0.5 inclusive and +0.5 exclusive.
    public void pluck() {
        for (int index = 0; index < ringBuffer.size(); index++) {
            ringBuffer.remove();
            ringBuffer.add(r.nextDouble() - 0.5);
        }
    }

    // Post: This method should apply the Karplus-Strong update once (performing one step). 
    //       It should delete the sample at the front of the ring buffer and add to the end 
    //       of the ring buffer the average of the first two samples, multiplied by the 
    //       energy decay factor (ENERGY_DECAY_FACTOR).
    public void tic() {
        double num = ringBuffer.remove();
        num += ringBuffer.peek();
        num *= 0.5 * ENERGY_DECAY_FACTOR;
        ringBuffer.add(num);
    }

    // Post: This method should return the current sample 
    //       (the value at the front of the ring buffer).
    public double sample() {
        return ringBuffer.peek();
    }

    // Post: This method should return the size of N by doing calculation with given frequency.
    // Parameter: 
    //      double frequency - the frequency given by users
    public double getSizeN(double frequency) {
        return (Math.round(StdAudio.SAMPLE_RATE / frequency));
    }

    // Pre: if the frequency < 0 or getSizeN of frequency < 2, throw an IllegalArgumentException
    // Parameter: 
    //      double frequency - the frequency give by users
    private void check(double frequency) {
        if (getSizeN(frequency) < 2 || frequency <= 0) {
            throw new IllegalArgumentException();
        }
    }
}
