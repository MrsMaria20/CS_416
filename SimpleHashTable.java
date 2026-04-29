/**
 * SimpleHashTable class that implements a basic hash table using the midsquare algorithm.
 * @author Kristina Logan and CS 416
 * @version 4
 */
public class SimpleHashTable {

    private String[] array;
    private static final int SIZE = 10;

    /**
     * Constructor - initializes the hash table with size 10.
     */
    public SimpleHashTable() {
        array = new String[SIZE];
    }

    /**
     * Adds value to array using key.
     * Uses the midsquare hash function to determine the index.
     * @param key the integer key to use for hashing
     * @param value the String value to store
     */
    public void put(int key, String value) {
        // Calculate the index using the midsquare hash function
        int index = hash(key, SIZE, 2);
        // Store the value at the calculated index
        array[index] = value;
    }

    /**
     * Gets value from array using key.
     * Uses the midsquare hash function to determine the index.
     * @param key the integer key to use for hashing
     * @return the String value stored at that key, or null if not found
     */
    public String get(int key) {
        // Calculate the index using the midsquare hash function
        int index = hash(key, SIZE, 2);
        // Return the value at the calculated index
        return array[index];
    }

    /**
     * Prints a list of values stored in array with array index.
     * @return a string representation showing index and value pairs
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                sb.append("[").append(i).append("] ").append(array[i]).append("\n");
            }
        }
        
        // If no values stored, return empty string or message
        if (sb.length() == 0) {
            return "Hash table is empty";
        }
        
        return sb.toString();
    }

    /**
     * Hash using midsquare algorithm returns calculated array index.
     * 
     * Midsquare algorithm:
     * 1. Square the key
     * 2. Extract the middle r digits (by dropping first and last digits as needed)
     * 3. Take modulo with size to get index
     * 
     * @param key the integer key to hash
     * @param size the size of the hash table
     * @param r the number of middle digits to extract
     * @return the calculated array index
     */
    public static int hash(int key, int size, int r) {
        // Square the key
        long squared = (long) key * key;
        
        // Convert to string to extract middle digits
        String squaredStr = String.valueOf(squared);
        int length = squaredStr.length();
        
        // Calculate how many digits to drop from each end
        int toDrop = length - r;
        int dropFromRight = toDrop / 2;
        int dropFromLeft = toDrop - dropFromRight;
        
        // Extract the middle r digits
        String middleDigits = squaredStr.substring(dropFromLeft, length - dropFromRight);
        
        // Convert to integer
        int middleValue = Integer.parseInt(middleDigits);
        
        // Return modulo with size to get the final index
        return middleValue % size;
    }
}