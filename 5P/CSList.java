/**
 * Specification for a List ADT.
 * @author cs416
 * @version 2
 * @param <E> Type
 */
public interface CSList<E> {

    /**
     * Appends the specified value to the end of this list.
     * This list does not allow duplicate values.
     *
     * @param e The value to add
     * @return boolean True if the value is inserted, false otherwise
     */
    boolean add(E e);

    /**
     * Removes all of the elements from this list.
     */
    void clear();

    /**
     * Returns true if this list contains the specified element. More formally,
     * returns true if and only if this list contains at least one element e
     * such that Objects.equals(o, e).
     *
     * @param o element whose presence in this list is to be tested
     * @return boolean true if this list contains the specified element
     */
    boolean contains(Object o);

    /**
     * Returns the element at the specified position in this list.
     *
     * throws IndexOutOfBoundsException - if the index is out of range
     *
     * @param index The index at which to insert
     * @return E the element at the specified position in this list
     */
    E get(int index);

    /**
     * Gets the first element of this collection.
     *
     * throws NoSuchElementException - if this collection is empty
     *
     * @return E the retrieved element
     */
    E getFirst();

    /**
     * Gets the last element of this collection.
     *
     * throws NoSuchElementException - if this collection is empty
     *
     * @return E the retrieved element
     */
    E getLast();

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element.
     *
     * @param o element to search for
     * @return int the index of the first occurrence of the specified element
     *  in this list, or -1 if this list does not contain the element
     */
    int indexOf(Object o);

    /**
     * Returns true if this list contains no elements.
     * @return boolean true if this list contains no elements
     */
    boolean isEmpty();

    /**
     * Removes the first occurrence of the specified element from this list,
     *  if it is present. If this list does not contain
     *  the element, it is unchanged.
     *
     * @param o - element to be removed from this list, if present
     * @return boolean true if this list contained the specified element
     */
    boolean remove(Object o);

    /**
     * Returns the number of elements in this list.
     * @return int the number of elements in this list
     */
    int size();
}