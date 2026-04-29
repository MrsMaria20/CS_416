import java.util.Objects;

/**
 * Class to represent a map implemented using linked lists.
 * @author cs416
 * @version 2
 * @param <K> Key type
 * @param <V> Value type
 */

public class CSLinkedMap<K extends Comparable<K>, V> implements CSMap<K, V> {

    private final CSList<Entry> list;

    /**
     * Constructor.
     */
    public CSLinkedMap() {
        this.list = new CSLinkedList<>();
    }

    /**
     * Class to represent an entry in a map.
     * @author cs416
     * @version 2
     */
    private final class Entry {
        public final K key;
        public final V value;

        /**
         * Constructor.
         * @param key K Key
         * @param value V Value
         */
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Print a nice human readable map entry of form (key->value).
         * @return String
         */
        @Override
        public String toString() {
            return "(" + key + "->" + value + ")";
        }

        /**
         * Check if two Entries are equal.
         * We will say that two entries are equal if their keys are the same
         * @param o Object
         * @return boolean
         */
        @Override
        public boolean equals(Object o) {
            // Check if o is an instance of Entry
            if (!(o instanceof CSLinkedMap.Entry)) {
                return false;
            }

            // Cast o to Entry so we can access its key
            Entry other = (Entry) o;

            // Compare keys - they are Comparable, so use compareTo
            // Two entries are equal if their keys are equal (compareTo returns 0)
            return this.key.compareTo(other.key) == 0;
        }

        /**
         * Returns the hash code value for this entry.
         * Needed since we override equals().
         * @return int hash code
         */
        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     * If this map permits null values, then a return value of null does not necessarily indicate
     * that the map contains no mapping for the key;
     * it's also possible that the map explicitly maps the key to null.
     * The Map#containsKey operation may be used to distinguish these two cases.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key
     */
    @Override
    public V get(K key) {
        // Search through the list for an entry with matching key
        for (int i = 0; i < list.size(); i++) {
            Entry entry = list.get(i);
            
            // Check if this entry's key matches the search key
            if (entry.key.compareTo(key) == 0) {
                return entry.value;
            }
        }
        
        // Key not found, return null
        return null;
    }

    /**
     * Inserts the (K, V) pair into the map if it does not already exist and
     * returns true.
     * If the map already contains a mapping for the key, return false
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return true if the key, value are inserted, false otherwise.
     */
    @Override
    public boolean insert(K key, V value) {
        // Check if key already exists in the map
        if (containsKey(key)) {
            return false;  // Key already exists, don't insert
        }

        // Create a new Entry and add it to the list
        Entry newEntry = new Entry(key, value);
        list.add(newEntry);
        return true;  // Successfully inserted
    }

    /**
     * Print the LinkedMap in nice format.
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(list);
        return s.toString();
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @param key key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    @Override
    public boolean containsKey(K key) {
        // Search through the list for an entry with matching key
        for (int i = 0; i < list.size(); i++) {
            Entry entry = list.get(i);
            
            // Check if this entry's key matches the search key
            if (entry.key.compareTo(key) == 0) {
                return true;
            }
        }
        
        // Key not found
        return false;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     * @return true if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key key whose mapping is to be removed from the map
     * @return true if the key was removed from the Map, false otherwise
     */
    @Override
    public boolean remove(K key) {
        // Search through the list for an entry with matching key
        for (int i = 0; i < list.size(); i++) {
            Entry entry = list.get(i);
            
            // Check if this entry's key matches
            if (entry.key.compareTo(key) == 0) {
                // Found it! Remove this entry from the list
                // CSLinkedList.remove() uses equals(), which calls Entry.equals()
                // Entry.equals() compares keys, so this will find and remove the entry
                list.remove(entry);
                return true;
            }
        }
        
        // Key not found
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return list.size();
    }
}