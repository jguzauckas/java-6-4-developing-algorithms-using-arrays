public class NotesMinMax1 {
    public static double findMinimum(double[] arr) {
        double minimum = arr[0];
        for (double val : arr) {
            if (val < minimum) {
                minimum = val;
            }
        }
        return minimum;
    }
    
    public static double findMaximum(double[] vals) {
        double maximum = vals[0];
        for (double val : vals) {
            if (val > maximum) {
                maximum = val;
            }
        }
        return maximum;
    }
}
