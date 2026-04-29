import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Doubly Linked List implementation of a CSList.
 * @author (Kristina Logan and cs416)
 * @version 3
 * @param <E> Type
 */
public class CSLinkedList<E> implements CSList<E> {

    private final class Node<D> {
        D data;
        Node<D> next;
        Node<D> prev;

        Node(D data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    /**
     * Appends the specified value to the end of this list.
     * This list does not allow duplicate values.
     *
     * @param e The value to add
     * @return boolean True if the value is inserted, false otherwise
     */
    @Override
    public boolean add(E e) {
        // don't allow dups
        if (contains(e)) {
            return false;
        }

        // create new node with data
        Node<E> newNode = new Node<>(e);

        // if list is empty, set head and tail to new node
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            // add to end - link old tail to new node
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
        return true;
    }

    /**
     * Removes all of the elements from this list.
     */
    @Override
    public void clear() {
        // reset to empty state
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns true if this list contains the specified element. More formally,
     * returns true if and only if this list contains at least one element e
     * such that Objects.equals(o, e).
     *
     * @param o element whose presence in this list is to be tested
     * @return boolean true if this list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        // traverse from head
        Node<E> current = head;
        while (current != null) {
            if (Objects.equals(current.data, o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * throws IndexOutOfBoundsException - if the index is out of range
     *
     * @param index The index at which to insert
     * @return E the element at the specified position in this list
     */
    @Override
    public E get(int index) {
        // check bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        // traverse to the node at index
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Gets the first element of this collection.
     *
     * throws NoSuchElementException - if this collection is empty
     *
     * @return E the retrieved element
     */
    @Override
    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }

    /**
     * Gets the last element of this collection.
     *
     * throws NoSuchElementException - if this collection is empty
     *
     * @return E the retrieved element
     */
    @Override
    public E getLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element.
     *
     * @param o element to search for
     * @return int the index of the first occurrence of the specified element
     *  in this list, or -1 if this list does not contain the element
     */
     @Override
    public int indexOf(Object o) {
        // traverse and count
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (Objects.equals(current.data, o)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * Returns true if this list contains no elements.
     * @return boolean true if this list contains no elements
     */
     @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     *  if it is present. If this list does not contain
     *  the element, it is unchanged.
     *
     * @param o - element to be removed from this list, if present
     * @return boolean true if this list contained the specified element
     */
     @Override
    public boolean remove(Object o) {
        // find node to remove
        Node<E> current = head;
        while (current != null) {
            if (Objects.equals(current.data, o)) {
                // found it - remove the node

                // if it's the head
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        // list is now empty
                        tail = null;
                    }
                } else if (current == tail) {
                    // if it's the tail
                    tail = current.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                } else {
                    // middle node
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the number of elements in this list.
     * @return int the number of elements in this list
     */
     @Override
    public int size() {
        return this.size;
    }

    /**
     * Compares the specified object with this list for equality. Returns true
     *  if and only if the specified object is also a list, both lists have the
     *  same size, and all corresponding pairs of elements in the two lists are
     *  equal. (Two elements e1 and e2 are equal if Objects.equals(e1, e2).)
     *  In other words, two lists are defined to be equal if they contain the
     *  same elements in the same order.
     *
     * @param o - the object to be compared for equality with this list
     * @return boolean true if the specified object is equal to this list
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CSLinkedList<?> that = (CSLinkedList<?>) o;

        if (this.size() != that.size()) {
            return false;
        }

        Node<?> thisRef = head;
        CSLinkedList<?>.Node<?> thatRef = that.head;

        // iterate through both lists and compare elements
        while (thisRef != null) {
            if (!Objects.equals(thisRef.data, thatRef.data)) {
                return false;
            }
            thisRef = thisRef.next;
            thatRef = thatRef.next;
        }

        return true;
    }



    /**
     * Returns a string representation of the object.
     *
     * If the List contains the Strings A, B, C, D this
     * method returns a String in the format "A <--> B <--> C <--> D"
     *
     * @return String a string representation of the object.
     */
    @Override
    public String toString() {
        // empty list
        if (head == null) {
            return "";
        }

        // build string with proper separators
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" <--> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    /**************************************************************************
     **************************************************************************
     * Do Not change any code below this point, needed to testing and for     *
     * complete implementation (hashCode needs to be overridden if equals is) *
     **************************************************************************
     **************************************************************************/

    /**
     * Returns the hash code value for this list.
     *
     * @return the hash code value for this list
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        Node<E> temp = head;

        while (temp != null) {
            hashCode = 31 * hashCode + (temp.data == null ? 0 : temp.data.hashCode());
            temp = temp.next;
        }

        return hashCode;
    }

    /**
    * A forward iterator that traverses using the "next" references.
    * @return ForwardIterator
    */
    public CSLinkedList<E>.ForwardIterator forwardIterator() {
        return new ForwardIterator();
    }

    /**
    * A reverse iterator that traverses using the "prev" references.
    * @return ReverseIterator
    */
    public CSLinkedList<E>.ReverseIterator reverseIterator() {
        return new ReverseIterator();
    }

    /**
    * Iterator to traverse the CSLinkedList "forward" using next references.
    */
    public class ForwardIterator implements Iterator<E> {

        private Node<E> cur;

        /**
        * Constructor.
        */
        public ForwardIterator() {
            cur = head;
        }

        /**
         * returns true if the Iterator has another value.
         * @return boolean
         */
         @Override
        public boolean hasNext() {
            return cur != null;
        }

        /**
         * Returns the next element of the Iterator.
         * @return E
         */
         @Override
        public E next() {

            if (cur == null) {
                throw new NoSuchElementException();
            }

            E val = cur.data;
            cur = cur.next;
            return val;
        }
    }

    /**
    * Iterator to traverse the CSLinkedList "backward" using next references.
    */
    public class ReverseIterator implements Iterator<E> {

        private Node<E> cur;

        /**
        * Consructor.
        */
        public ReverseIterator() {
            cur = tail;
        }

        /**
         * returns true if the Iterator has another value.
         * @return boolean
         */
         @Override
        public boolean hasNext() {
            return cur != null;
        }

        /**
         * Returns the next element of the Iterator.
         * @return E
         */
         @Override
        public E next() {

            if (cur == null) {
                throw new NoSuchElementException();
            }

            E val = cur.data;
            cur = cur.prev;
            return val;
        }
    }
}