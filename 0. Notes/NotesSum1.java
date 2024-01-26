public class NotesSum1 {
    public static int sumAges(Person[] people) {
        int sum = 0;
        for (Person person : people) {
            sum += person.getAge();
        }
        return sum;
    }
}
