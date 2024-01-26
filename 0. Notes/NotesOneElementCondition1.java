public class NotesOneElementCondition1 {
    public static boolean containsEven(int[] nums) {
        for (int num : nums) {
            if (num % 2 == 0) {
                return true;
            }
        }
        return false;
    }
}
