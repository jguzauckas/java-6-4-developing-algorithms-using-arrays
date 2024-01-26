public class NotesMode1 {
    public static double findMode(double[] vals) {
        double mode = vals[0];
        int numTimes = 1;
        for (double val1 : vals) {
            int tempTimes = 0;
            for (double val2 : vals) {
                if (val1 == val2) {
                    tempTimes++;
                }
            }
            if (tempTimes > numTimes) {
                mode = val1;
                numTimes = tempTimes;
            }
        }
        return numTimes;
    }
}
