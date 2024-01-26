public class NotesDuplicates1 {
    public static boolean areDuplicates(double[] vals) {
        for (double val1 : vals) {
            int tempTimes = 0;
            for (double val2 : vals) {
                if (val1 == val2) {
                    tempTimes++;
                }
            }
            if (tempTimes >= 2) {
                return true;
            }
        }
        return false;
    }
}
