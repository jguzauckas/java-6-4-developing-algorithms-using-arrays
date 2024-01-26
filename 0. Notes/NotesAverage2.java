public class NotesAverage2 {
    public static int findSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
    
    public static double findAverage(int[] nums) {
        return (double) findSum(nums) / nums.length;
    }
}
