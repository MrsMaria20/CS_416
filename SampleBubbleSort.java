import java.io.IOException;

/**
 * BubbleSort class.
 * @author Kristina Logan and cs416
 * @version 2
 */
public class SampleBubbleSort extends SampleSort {

    /**
     * Constructor.
     * @param fileName The data file to open and sort
     */
    public SampleBubbleSort(String fileName) throws IOException {
        super(fileName);
    }

    /**
     * The sort function.
     * Implements bubble sort algorithm, comparing adjacent elements
     * and swapping them if they are in the wrong order.
     */
    @Override
    public void sort() {
        int n = samples.size();
        
        // Keep making passes through the array until it's sorted
        for (int i = 0; i < n - 1; i++) {
            // On each pass, compare each sample with the next one
            for (int j = 0; j < n - i - 1; j++) {
                // Use compareTo to check if current sample is bigger than the next
                // If it returns > 0, current is bigger and needs to move right
                if (samples.get(j).compareTo(samples.get(j + 1)) < 0) {
                    // Swap the two samples so the smaller one comes first
                    Sample temp = samples.get(j);
                    samples.set(j, samples.get(j + 1));
                    samples.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * main function.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            SampleSort b = new SampleBubbleSort("data.csv");
            b.sort();
            System.out.println(b);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}