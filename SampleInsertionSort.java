import java.io.IOException;

/**
 * Insertion sort class.
 * @author Kristina Logan and cs416
 * @version 2
 */
public class SampleInsertionSort extends SampleSort {

    /**
     * Constructor.
     * @param fileName The data file to open and sort
     */
    public SampleInsertionSort(String fileName) throws IOException {
        super(fileName);
    }

    /**
     * sort function performs an InsertionSort.
     * Builds the sorted array one item at a time by inserting each element
     * into its correct position in the already-sorted portion.
     */
    @Override
    public void sort() {
        int n = samples.size();
        
        // Start from the second sample (index 1) since first sample is already "sorted"
        for (int i = 1; i < n; i++) {
            // Get the current sample we want to insert into the sorted portion
            Sample key = samples.get(i);
            // Start checking from the sample just before our current one
            int j = i - 1;
            
            // Keep moving backwards while the previous sample is bigger than our key
            // This shifts larger samples to the right to make room for key
            while (j >= 0 && samples.get(j).compareTo(key) < 0) {
                // Move the larger sample one position to the right
                samples.set(j + 1, samples.get(j));
                j--;
            }
            
            // Now we found the correct spot - insert key where it belongs
            samples.set(j + 1, key);
        }
    }

    /**
     * main function for simple testing.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            SampleSort b = new SampleInsertionSort("data.csv");
            b.sort();
            System.out.println(b);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}