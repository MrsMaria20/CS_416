import java.util.Scanner;

/**
 * Interactive Test Driver for CSTreeMap Assignment.
 * @author cs416
 * @version 1
 */
public class TestDriver {

    /**
     * Main method.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CSMap<String, Integer> map = new CSTreeMap<>();

        String input = sc.nextLine();
        while (!input.equals("x")) {
            String[] tokens = input.split(" ");
            if (tokens[0].equals("i")) { // insert(K key, V value)
                String key = tokens[1];
                Integer value = Integer.parseInt(tokens[2]);
                map.insert(key, value);
                System.out.println(map);
            } else if (tokens[0].equals("c")) { // containsKey(K key)
                String key = tokens[1];
                System.out.println(map.containsKey(key));
            } else if (tokens[0].equals("C")) { // clear()
                map.clear();
                System.out.println(map);
            } else if (tokens[0].equals("r")) { // remove(K key)
                String key = tokens[1];
                map.remove(key);
                System.out.println(map);
            } else if (tokens[0].equals("e")) { // isEmpty()
                System.out.println(map.isEmpty());
            } else if (tokens[0].equals("s")) { // size()
                System.out.println(map.size());
            } else if (tokens[0].equals("g")) { // get(K key)
                String key = tokens[1];
                System.out.println(map.get(key));
            }

            System.out.println("------------------");
            input = sc.nextLine();
        }

        sc.close();
    }
}