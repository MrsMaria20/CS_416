import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
* Abstract sort class.
* @author cs416
* @version 1
*/
public abstract class SampleSort {
    ArrayList<Sample> samples = new ArrayList<>();

    /**
    * Constuctor - opens the passed in filename parameter, parses into
    * Sample objects and adds to internal collection.
    * @param fileName The filename to open
    */
    public SampleSort(String fileName) throws IOException {
        Scanner s = new Scanner(new File(fileName));
        while (s.hasNext()) {
            samples.add(new Sample(s.nextInt(), s.nextDouble()));
        }
        s.close();
    }

    /**
    * toString - returns the string format of this Sort object.
    * @return String
    */
   @Override
    public String toString() {
        if (samples.size() == 0) {
            return "[]";
        }

        String retVal = "[";
        for (Sample s : samples) {
            retVal += s.toString() + ",";
        }
        return retVal.substring(0, retVal.length() - 1) + "]";
    }


    /**
    * Sort samples by default in descending order based on
    * measurement.
    */
    public abstract void sort();

}