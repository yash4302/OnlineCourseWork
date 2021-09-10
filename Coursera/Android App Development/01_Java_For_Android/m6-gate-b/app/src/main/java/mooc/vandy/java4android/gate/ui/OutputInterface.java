package mooc.vandy.java4android.gate.ui;

import mooc.vandy.java4android.gate.logic.ClassToTest;

/**
 * Interface defines the methods that the User Interface
 * [MainActivity] will implement.
 */
public interface OutputInterface {
    /**
     * Return the enumeration literal for the class to test.
     */
    ClassToTest getClassToTest();

    /**
     * Reset the on-screen output (EditText box).
     */
    void resetText();

    /**
     * Prints the string representation of the passed Java Object or primitive
     * type followed by a new line.
     *
     * @param obj a String, int, double, float, boolean or any Java Object.
     */
    default void println(Object obj) {
        print(obj);
        println();
    }

    /**
     * Print a newline.
     */
    default void println() {
        print('\n');
    }

    /**
     * Prints the string representation of the passed Java Object or primitive type.
     *
     * @param obj a String, int, double, float, boolean or any Java Object.
     */
    void print(Object obj);
}
