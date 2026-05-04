/**
 * Search interface.
 * @author cs416
 * @version 1
  * @param <T> Comparable Type
 */
public interface Search<T extends Comparable<T>> {

    /**
     *
     * @return true if there are no element in the array
     */
    boolean isEmpty();

    /**
     *
     * @return the number of elements in the array
     */
    int size();

    /**
     *
     * @return true if the array is monotonically increasing
     * For example: 1, 2, 3, 7, 20
     */
    boolean isSorted();

    /**
     *
     * @param obj object to be searched for
     * @return the location of the element in the list starting at 0 or return -1 if not present
     */
    int index(T obj);
}