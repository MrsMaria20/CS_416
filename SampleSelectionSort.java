import java.io.IOException;

/**
 * Selection Sort class.
 * @author Kristina Logan and cs416
 * @version 2
 */
public class SampleSelectionSort extends SampleSort {

    /**
     * Selection Sort Constructor.
     * @param fileName The data file to open and sort.
     */
    public SampleSelectionSort(String fileName) throws IOException {
        super(fileName);
    }

    /**
     * sort function, performs a Selection Sort.
     * Repeatedly finds the minimum element from the unsorted portion
     * and moves it to the beginning of the sorted portion.
     */
    @Override
    public void sort() {
        int n = samples.size();
        
        // For each position in the array
        for (int i = 0; i < n - 1; i++) {
            // Assume the current position has the smallest element
            int minIndex = i;
            
            // Search through the rest of the array to find the actual smallest element
            for (int j = i + 1; j < n; j++) {
                // If we find a sample smaller than our current minimum, update minIndex
                if (samples.get(j).compareTo(samples.get(minIndex)) > 0) {
                    minIndex = j;
                }
            }
            
            // Only swap if we found a smaller element than what's at position i
            if (minIndex != i) {
                // Swap the smallest element we found with the current position
                Sample temp = samples.get(i);
                samples.set(i, samples.get(minIndex));
                samples.set(minIndex, temp);
            }
        }
    }

    /**
     * main function.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            SampleSort b = new SampleSelectionSort("data.csv");
            b.sort();
            System.out.println(b);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}