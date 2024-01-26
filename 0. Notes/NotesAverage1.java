public class NotesAverage1 {
    public static double findAverage(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return (double) sum / nums.length;
    }
}
