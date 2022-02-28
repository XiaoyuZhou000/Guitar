// Xiaoyu Zhou
// 01/22/2021
// CSE 143
// Section: AP
// TA: Hitesh Boinpally
// Assignment #2
// This part of the program will model a vibrating guitar string of a given frequency.
import java.util.*;

public class Guitar37 implements Guitar {
    // The global String to store the keyboard layout
    public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout

    // The global constant int to store the number(size) of array of GuitarString
    public static final int NUMBER_OF_STRINGS = 37;
    // The global constant double to  store the standard frequency of guitar
    public static final double FREQUENCY = 440.0;

    // The variable in flied to store an array of GuitarString
    private GuitarString[] stringArray;
    // The variable in flied to store the number of time tic() called.
    private int time;

    // Post: This method would construct stringArray with size of NUMBER_OF_STRINGS
    public Guitar37() {
        stringArray = new GuitarString[NUMBER_OF_STRINGS];
        time = 0;
        for (int index = 0; index < NUMBER_OF_STRINGS; index++) {
            stringArray[index] = new GuitarString(getFrequency(index));
        }
    }

    // Pre: if the pitch given by users is not in the range of [-24,12], this method would not run.
    // Post: This method would pluck a guitar string in stringArray with the given pitch.
    // Parameter: 
    //      int pitch - the pitch of guitar given by users
    public void playNote(int pitch) {
        if (pitch >= -24 && pitch <= 12) {
            stringArray[pitch + 24].pluck();
        }
    }

    // Post: This method would return true if the given string is included in KEYBOARD, 
    //       otherwise false
    // Parameter: 
    //      char string - the char given by users and used to check wherther included in KEYBOARD
    public boolean hasString(char string) {
        return (KEYBOARD.indexOf(string) >= 0);
    }

    // Pre: If the given string char is not included in KEYBOARD, throw an IllegalArgumentException
    // Post: This method would pluck a string with the given char into stringArray
    // Parameter:
    //      char string - the char given by users and used to pluck into stringArray
    public void pluck(char string) {
        if (KEYBOARD.indexOf(string) < 0) {
            throw new IllegalArgumentException();
        }
        stringArray[KEYBOARD.indexOf(string)].pluck();
    }

    // Post: This method would return the sum of all the sample in stringArray
    public double sample() {
        double sum = 0.0;
        for (int index = 0; index < NUMBER_OF_STRINGS; index++) {
            sum += stringArray[index].sample();
        }
        return sum;
    }

    // Post: This method would tic all char in the KEYBOARD and increase time field by one
    public void tic() {
        for (int index = 0; index < NUMBER_OF_STRINGS; index++) {
            stringArray[index].tic();
        }
        time++;
    }

    // Post: This method would return the value of timme field
    public int time() {
        return time;
    }

    // Post: This method would calculate and return the specific frequency by using given index
    // Parameter:
    //      int index - the index given by users and used to calculate corresponding frequency
    public double getFrequency(int index) {
        return FREQUENCY * Math.pow(2.0, (index - 24) / 12.0);
    }

}
