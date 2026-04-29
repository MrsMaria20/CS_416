/**
 * Specification for a Map ADT.
 * @author cs416
 * @version 2
 * @param <K> Key type
 * @param <V> Value type
 */
public interface CSMap<K, V> {
    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    void clear();

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @param key key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    boolean containsKey(K key);

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
    V get(K key);


    /**
     * Inserts the (K, V) pair into the map if it does not already exist and
     * returns true.
     * If the map already contains a mapping for the key, return false
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return true if the key, value are inserted, false otherwise.
     */
    boolean insert(K key, V value);

    /**
     * Returns true if this map contains no key-value mappings.
     * @return true if this map contains no key-value mappings
     */
    boolean isEmpty();

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key key whose mapping is to be removed from the map
     * @return true if the key was removed from the Map, false otherwise
     */
    boolean remove(K key);

    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map
     */
    int size();
}