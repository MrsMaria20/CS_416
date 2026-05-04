/**
 * Class to represent a Map as a binary search tree.
 * @author Kristina Logan and cs416
 * @version 2
 * @param <K> Type - must be Comparable
 * @param <V> Type
 */
public class CSTreeMap<K extends Comparable<K>, V> implements CSMap<K, V> {

    /**
     * Node class for the binary search tree.
     * Each node stores a key-value pair and references to left and right children.
     */
    class Node {
        K key;
        V value;
        Node left;
        Node right;

        /**
         * Constructor for a node.
         * @param key the key to store
         * @param value the value to store
         */
        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    /**
     * Constructor - initializes an empty tree map.
     */
    public CSTreeMap() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the value associated with a key.
     * @param key the key to search for
     * @return the value associated with the key, or null if not found
     */
    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    /**
     * Helper method for get() using recursion.
     * Searches the tree for the given key.
     * @param node the current node being checked
     * @param key the key to search for
     * @return the value if found, null otherwise
     */
    private V getHelper(Node node, K key) {
        // Base case: empty subtree
        if (node == null) {
            return null;
        }

        // Compare the key with the current node's key
        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            // Key is less than current node, search left subtree
            return getHelper(node.left, key);
        } else if (cmp > 0) {
            // Key is greater than current node, search right subtree
            return getHelper(node.right, key);
        } else {
            // Key matches, return the value
            return node.value;
        }
    }

    /**
     * Inserts a key-value pair into the map.
     * @param key the key to insert
     * @param value the value to insert
     * @return true if inserted successfully, false if key already exists
     */
    @Override
    public boolean insert(K key, V value) {
        // Check if key already exists
        if (containsKey(key)) {
            return false;
        }

        // Insert the new key-value pair
        root = insertHelper(root, key, value);
        return true;
    }

    /**
     * Helper method for insert() using recursion.
     * Recursively finds the correct position and inserts the new node.
     * @param node the current node
     * @param key the key to insert
     * @param value the value to insert
     * @return the node (possibly new)
     */
    private Node insertHelper(Node node, K key, V value) {
        // Base case: empty subtree, create new node
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        // Compare the key with the current node's key
        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            // Key is less than current node, insert in left subtree
            node.left = insertHelper(node.left, key, value);
        } else if (cmp > 0) {
            // Key is greater than current node, insert in right subtree
            node.right = insertHelper(node.right, key, value);
        }
        // If cmp == 0, key already exists (handled by containsKey check)

        return node;
    }

    /**
     * Checks if the map contains a given key.
     * @param key the key to search for
     * @return true if the key exists, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    /**
     * Helper method for containsKey() using recursion.
     * @param node the current node being checked
     * @param key the key to search for
     * @return true if the key is found, false otherwise
     */
    private boolean containsKeyHelper(Node node, K key) {
        // Base case: empty subtree
        if (node == null) {
            return false;
        }

        // Compare the key with the current node's key
        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            // Key is less than current node, search left subtree
            return containsKeyHelper(node.left, key);
        } else if (cmp > 0) {
            // Key is greater than current node, search right subtree
            return containsKeyHelper(node.right, key);
        } else {
            // Key matches
            return true;
        }
    }

    /**
     * Removes a key-value pair from the map.
     * @param key the key to remove
     * @return true if removed successfully, false if key not found
     */
    @Override
    public boolean remove(K key) {
        // Check if the key exists
        if (!containsKey(key)) {
            return false;
        }

        // Remove the node
        root = removeHelper(root, key);
        return true;
    }

    /**
     * Helper method for remove() using recursion.
     * Handles three cases: node with no children, one child, or two children.
     * @param node the current node
     * @param key the key to remove
     * @return the modified subtree
     */
    private Node removeHelper(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            // Key is in left subtree
            node.left = removeHelper(node.left, key);
        } else if (cmp > 0) {
            // Key is in right subtree
            node.right = removeHelper(node.right, key);
        } else {
            // Found the node to remove
            size--;

            // Case 1: Node has no children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: Node has only a right child
            if (node.left == null) {
                return node.right;
            }

            // Case 3: Node has only a left child
            if (node.right == null) {
                return node.left;
            }

            // Case 4: Node has both children
            // Find the minimum key in the right subtree (in-order successor)
            Node successor = findMin(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = removeHelper(node.right, successor.key);
        }

        return node;
    }

    /**
     * Finds the node with the minimum key in a subtree.
     * @param node the root of the subtree
     * @return the node with the minimum key
     */
    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    /**
     * Checks if the map is empty.
     * @return true if the map contains no entries, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears all entries from the map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the number of key-value pairs in the map.
     * @return the number of entries
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the map.
     * Entries are displayed in sorted order (in-order traversal).
     * Format: (key1->value1) <--> (key2->value2) <--> ...
     * @return string representation of the map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);

        if (sb.length() == 0) {
            return "";
        } else {
            // Remove the last " <--> " separator
            return sb.substring(0, sb.length() - 6);
        }
    }

    /**
     * Helper method for toString().
     * Performs an in-order traversal of the tree to display entries in sorted order.
     * @param node the current node
     * @param sb the StringBuilder to append to
     */
    private void buildString(Node node, StringBuilder sb) {
        if (node != null) {
            // In-order traversal: left, current, right
            buildString(node.left, sb);
            sb.append("(");
            sb.append(node.key);
            sb.append("->");
            sb.append(node.value);
            sb.append(") <--> ");
            buildString(node.right, sb);
        }
    }
}