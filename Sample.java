/**
 * Sample class.
 * @author cs416
 * @version 1
 */
public class Sample implements Comparable<Sample> {
    private int id;
    private double measurement;

    /**
    * Constructor.
    * @param id The identifier for the Sample
    * @param measurement The value of the measurement for this id
    */
    public Sample(int id, double measurement) {
        this.id = id;
        this.measurement = measurement;
    }

    /**
    * Returns the String reprensentation of this Sample.
    * @return String
    */
   @Override
    public String toString() {
        return String.format("<%d,%.2f>", id, measurement);
    }

    /**
     * Compares this Sample with the specified Sample for order.
     * Returns a negative integer, zero, or a positive integer as this Sample
     * is less than, equal to, or greater than the specified Sample.
     * Note, uses the Sample's measurement for comparison
     * @param other Sample to be compared
     * @return int
     */
    @Override
    public int compareTo(Sample other) {
        return Double.compare(measurement, other.measurement);
    }
}