public class NotesAllElementCondition1 {
    public static boolean allNegative(int[] nums) {
        for (int num : nums) {
            if (num >= 0) {
                return false;
            }
        }
        return true;
    }
}
