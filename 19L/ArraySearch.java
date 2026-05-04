import java.lang.reflect.Array;
 
/**
* ArraySearch class that implements the Search Interface.
* @author Kristina Logan and cs416
* @version 2
* @param <T> Type
*/
public class ArraySearch<T extends Comparable<T>> implements Search<T> {
 
    private final T[] data;
 
    /**
     * Constructor.
     * @param arr Array of T types
     */
    @SuppressWarnings("unchecked")
    public ArraySearch(T[] arr) {
        data = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
        System.arraycopy(arr, 0, this.data, 0, arr.length);
    }
 
    /**
    * isEmpty method.
    * @return boolean
    */
    @Override
    public boolean isEmpty() {
        // Check if the array has no elements by checking if length is 0
        return data.length == 0;
    }
 
    /**
    * size method.
    * @return int
    */
    @Override
    public int size() {
        // Return the length of the array
        return data.length;
    }
 
    /**
    * isSorted method.
    * @return boolean
    */
    @Override
    public boolean isSorted() {
        // Empty and single element arrays are considered sorted
        if (data.length <= 1) {
            return true;
        }
 
        // Check each element to see if it's less than or equal to the next one
        for (int i = 0; i < data.length - 1; i++) {
            // Use compareTo to compare adjacent elements
            // If compareTo returns positive number, current element is greater than next
            // which means array is not sorted
            if (data[i].compareTo(data[i + 1]) > 0) {
                return false;
            }
        }
 
        // If we get here, all elements are in sorted order
        return true;
    }
 
    /**
    * returns the index of the object if it exists or -1 if it does not exist.
    * @param obj The object to search for
    * @return int
    */
    @Override
    public int index(T obj) {
        // Search through the array for the object
        for (int i = 0; i < data.length; i++) {
            // Use compareTo to check if we found the object
            // compareTo returns 0 when objects are equal
            if (data[i].compareTo(obj) == 0) {
                return i;
            }
        }
 
        // Object was not found in the array
        return -1;
    }
}
 