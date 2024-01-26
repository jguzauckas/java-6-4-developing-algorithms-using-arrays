# Developing Algorithms Using Arrays

Just like with loops, there are some standard algorithms that its important everyone can understand and utilize with arrays (and most of them use loops).

---

## Two Types of Problems

There are two primary types of problems we are going to look at algorithms for in this section:
- Algorithms that answer a question based on the information in an array, which we'll refer to as **Question-Answer Problems**.
- Algorithms that make modifications to an array, which we'll refer to as **Modification Problems**.

---

## Question-Answer Problems

### Minimum/Maximum Value

When dealing with arrays that involve numbers (either directly storing numbers, or numbers related to objects being stored, like a Person's age), we will often want to calculate the **minimum value** (the smallest value in the array), and/or the **maximum value** (the largest value in the array).

For either situation, we are going to want our algorithm to follow these steps:
- Store a starting value for the minimum/maximum.
- Iterate over the array:
    - Check if the current array value is less than the stored minimum/more than the stored maximum.
    - If it is, save that array value to the stored minimum/maximum as the new value.
    - If it is not, skip it.
- After iterating, return your answer.

An important question to answer when writing this algorithm is to choose a starting value for the minimum/maximum. The danger here would be picking a value that is too small or too large, and so is never overruled by the algorithm, meaning you never find a minimum or maximum from the array.

If we are familiar with the data, we might utilize knowledge about the typical upper or lower bounds for the information. We would use this counterintuitively though, since if we want a minimum, we would start with the upper bound. We would do this because we don't know if our data actually reaches the lower bound (hence the need to find the minimum), and upper bound is most likely to be overruled later in our algorithm to find the true minimum. Similarly, for maximum we would want to start with the lower bound.

Even if we are familiar with the data, the above strategy for starting value requires some guesswork. A much more consistent strategy, that will work regardless of what we know about the data would be making our starting value one of the values in the array. This works really well, because best case, our starting value is the actual minimum/maximum in the array, and worst case, it gets overruled by the rest of the algorithm to find the real minimum/maximum.

Here is what these algorithms could look like with a `double` array from the `NotesMinMax1.java` file:

```java
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
```

Important notes:
- We assign our starting value with the first element of the array. From the reasoning above, we are guaranteed to be able to find an accurate minimum or maximum now. Note that the loop will check the first value anyway since it is an enhanced `for` loop, but it works anyways.
- For minimum we use `val < minimum` because we are looking for new values that should replace our current minimum, and so they would be less than what we have so far. Same reasoning for maximum.

### Sum

When dealing with arrays that involve numbers (either directly storing numbers, or numbers related to objects being stored, like a Person's age), we will often want to add up all of the values, or **sum** the values, for varying purposes.

We have looked at ways to do this in previous sections, but here is the basic steps of the algorithm:
- Make a variable to track the total, we will want it to start at `0`.
- Iterate over the array:
    - Add the current array value to the total.
- After iterating, `return` the total.

Here is what this algorithm could look like with a `Person` array from the `NotesSum1.java` file:

```java
public static int sumAges(Person[] people) {
    int sum = 0;
    for (Person person : people) {
        sum += person.getAge();
    }
    return sum;
}
```

Important note:
- Since `Person` objects themselves aren't numbers, we had to use an accessor method `getAge()` to get the value to add to the total

### Average

When dealing with arrays that involve numbers (either directly storing numbers, or numbers related to objects being stored, like a Person's age), we will often want to **average** all of the values for varying purposes.

An average is the total of all values divided by the number of values. This means that the first part of this algorithm is our use of the **sum** algorithm we just created. Then afterwards, we do a division and we are done.

Here are the basic steps of the algorithm:
- Make a variable to track the total, we will want it to start at `0`.
- Iterate over the array:
    - Add the current array value to the total.
- After iterating, divide the total by the number of values that had been added up. This is most easily done by using the length of the array.
- `return` the average.

Here is what this algorithm could look like with an `int` array from the `NotesAverage1.java` file:

```java
public static double findAverage(int[] nums) {
    int sum = 0;
    for (int num : nums) {
        sum += num;
    }
    return (double) sum / nums.length;
}
```

The nice part about working on algorithms already, is that we can use algorithms that we write to help with others. Let's split this problem into the two algorithms that make it up: the sum, then the average, and use them together in the `NotesAverage2.java` file:

```java
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
```

Important notes:
- In both demonstrations, we combined the last two steps of the algorithm: division and returning into a single line. This works since operator precedence has `return` go after division, allowing the calculationg to happen.
- Since our summed values were `int` values, we had to cast the division to a `double` in order to get a decimal answer. Some algorithms might ask for an `int` average answer for `int` values, so this might not always be needed.
- In the second demonstration, we can just call `findSum(nums)` in place of `sum` from the original demonstration without a variable in between!

### Mode

When dealing with arrays that involve numbers (either directly storing numbers, or numbers related to objects being stored, like a Person's age), we will often want to determine the most common value in an array, or its **mode**, for varying purposes.

As a general process for mode, we are going to count up how many times each value appears, and consistently keep track of a current "winner" (that is the number that while we are working, has been found the most times), and how many times it has been found.

Here are the basic steps of this algorithm:
- Create two variables to keep track of the actual mode value (the number) and the number of times it appeared. The mode value will be the same type as the array elements, and the number of times it appeared is an `int`. Set the actual mode value to the first element of the array and the number of times it appeared to `1`.
- Iterate over the array:
    - Create a temporary counting variable to keep track of how many copies of the value we find. Initialize it to `0`.
    - Iterate over the array:
        - Check if the outer loop current array element is the same as the inner loop current array element.
        - If it is, increment the temporary counting variable by 1.
        - If it is not, skip it.
    - Check if the temporary counting variable is bigger than the official number of times variable.
    - If it is
        - Replace the official number of times value with the value of the temporary counting variable.
        - Replace the actual mode value with the value of the current array element.
    - If it is not, skip it.
- `return` the actual mode value.

Here is what this algorithm could look like with a `double` array from the `NotesMode1.java` file:

```java
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
```

Important Notes:
- `numTimes` started at `1` because its guaranteed for the first element of the array to appear at least `1` time. `tempTimes` started at `0` because the inner loop will include the array element the outer loop is currently working on. If it had started at `1` like `numTimes`, we would be double-counting an element.
- We need to keep track of both the mode itself and the number of times it appeared in order to effectively determine when to replace with a new mode (based on number of appearances), but still `return` the mode itself.
- We don't have to reset `tempTimes` because the outer loop does that by iterating.

### An Element with a Property

With arrays of any type, we might ask questions about **whether or not at least one element meets a given condition/has a certain property**. Unlike the algorithms above, there are an infinite number of ways to ask questions like this, so our example won't cover every way this can look.

The important thing about a question like this is that it isn't asking which element meets a criteria, or how many elements meet a criteria, just whether at least one does. This means that our algorithm can answer the question with "yes" the moment it finds a good element, or wait until the very end and say "no" after it has not found any.

Here are the basic steps of this algorithm:
- Iterate over the array:
    - Check if the current array element meets the criteria of the problem.
    - If it does, `return` `true`.
    - If it does not, skip it.
- Since none of the elements met the criteria, `return` `false`.

Here is what this algorithm could look like with an `int` array with the goal of determining if it contains any even numbers from the `NotesOneElementCondition1.java` file:

```java
public static boolean containsEven(int[] nums) {
    for (int num : nums) {
        if (num % 2 == 0) {
            return true;
        }
    }
    return false;
}
```

Important Notes:
- The condition/criteria/property could be anything, whether any numbers are positive or negative, whether any `String` objects contain a certain character, etc.
- `return false` happens after the loop completes. This is because if the `if` statement in the loop ever works, it will `return true` and the function is over. If that never happens, it means every `int` value was not even, and so exiting the loop means that the loop does not have at least one even number, and we `return false`.

### All Elements with a Property

With arrays of any type, we might ask questions about **whether or not all elements meet a given condition/have a certain property**. Unlike the algorithms above, there are an infinite number of ways to ask questions like this, so our example won't cover every way this can look.

This is strikingly similar to the "at least one element" question we just covered, and these similarities result in them being very similar problems logically. Now, instead of needing to find one element to succeed, we need to find one element to fail.

Here are the basic steps of this algorithm:
- Iterate over the array:
    - Check if the current array element meets the criteria of the problem.
    - If it does, skip it.
    - If it does not, `return` `false`.
- Since all of the elements met the criteria, `return` `true`.

Here is what this algorithm could look like with an `int` array with the goal of determining if it contains all negative numbers from the `NotesAllElementsCondition1.java` file:

```java
public static boolean allNegative(int[] nums) {
    for (int num : nums) {
        if (num >= 0) {
            return false;
        }
    }
    return true;
}
```

Important Notes:
- The structure of this algorithm is extremely similar to the "at least one element" example. It really just appears like `true` and `false` have swapped places.
- The most important difference is the `if` condition, which is the opposite of the condition the problem is evaluating. The problem wants to know if all the values are negative, but our condition checks if the value is positive. This is because we are trying to find individuals that break the criteria, as that ends the problem whereas before finding anything that met the criteria ended the problem.

### Access All Consecutive Pairs of Elements

With arrays of any type, we might ask questions about relating to **consecutive pairs of elements**. These types of questions essentially want to look at all the pairs of lockers that are next to each, whether that's to make a comparison, or do some other operation. Unlike the algorithms above, there are an infinite number of ways to ask questions like this, so our example won't cover every way this can look.

A great subtle example of a question like this would be to ask if an array of numbers is in increasing order. The key to evaluating if the values of an array are in increasing order would to go one at a time and make sure each element is greater than the one before it. Important to note that my phrasing there indicated that we need a value and the value before it, which is a consecutive pair of values from the array.

With the need for two elements at once, related by their indices being next to each, this is situation where a regular `for` loop is much more appropriate than an enhanced `for` loop.

Here are the basic steps of this algorithm:
- Iterate over the array starting at the second index:
    - Check if the element at the current index is greater than or equal to the element at the index before it.
    - If it is, skip it.
    - If it is not, `return` `false`.
- Since all elements were in order, `return` `true`.

Here is what this algorithm could look like with an `int` array with the goal of determining if it is in increasing order from the `NotesConsecutivePairs1.java` file:

```java
public static boolean isIncreasing(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] < nums[i - 1]) {
            return false;
        }
    }
    return true;
}
```

Important Notes:
- We started `i` at `1` because with `i - 1` later on, we need to be careful not to get `-1` as an index if `i` was `0`.
- `i` and `i - 1` are consecutive indices, so they represent elements that are next to each other. You can get more elements next to each other with `i + 1`, `i - 2`, etc. Whenever you do this, you just have to adjust your loop bounds to make sure they don't result in an `ArrayIndexOutOfBoundsError`.
- We reversed the `boolean` condition because we wanted to take action when it failed (so reversing it makes failing it `true`).

### Determine the Presence or Absence of Duplicate Elements

With arrays of any type, we might ask the question of **whether or not any duplicate elements exist**.

This algorithm will take a lot from our mode algorithm from earlier, as we will need to use nested loops: once to select each element, another to check for duplicates of the selected element.

Here are the basic steps of this algorithm:
- Iterate over the array:
    - Create a temporary counting variable to keep track of how many copies of the value we find. Initialize it to `0`.
    - Iterate over the array:
        - Check if the outer loop current array element is the same as the inner loop current array element.
        - If it is, increment the temporary counting variable by `1`.
        - If it is not, skip it.
    - Check if the temporary counting variable is bigger than `2`.
    - If it is, `return` `true`.
    - If it is not, skip it.
- Since we did not find any duplicates, `return` `false`.

Here is what this algorithm could look like with a `double` array from the `NotesDuplicates1.java` file:

```java
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
```

Important Notes:
- `tempTimes` started at `0` because the inner loop will include the array element the outer loop is currently working on. If it had started at `1` like `numTimes`, we would be double-counting an element, guaranteeing a duplicate even if there isn't one.
- We check if `tempTimes` is greater than or equal to `2`, because finding `2` or more of an element would mean there were duplicates, whereas just finding `1` would mean that it's unique.
- We don't have to reset `tempTimes` because the outer loop does that by iterating.

### Determine the Number of Elements Meeting Criteria

Just like we asked whether or not any elements meet a criteria or whether or not all element meet a criteria, we might have a question that is a middle ground: **how many elements meet a criteria**.

This algorithm will need to go through each element, decides if it meets criteria, and keep track of how many do meet the criteria.

Here are the basic steps of this algorithm:
- Create a counting variable to keep track of how many elements meet the criteria. Initialize it to `0`.
- Iterate over the array:
    - Check if the current array element meets the criteria.
    - If it does, increment the counting variable.
    - If it does not, skip it.
- `return` the value of the counting variable

Here is what this algorithm could look like with a `double` array from the `NotesNumberCriteria1.java` file:

```java
public static int howManyOldPeople(Person[] people) {
    int count = 0;
    for (Person person : people) {
        if (person.getAge() > 60) {
            count++;
        }
    }
    return count;
}
```

---

## Modification Problems

### Shift Elements Left/Right

Given an array, shifting elements left or right would mean every elements index shifts either up or down. Here is a visual example:

```
Index:          0   1   2   3   4   5   6
Elements:       3   6   9   12  15  18  21
Shift Left:     6   9   12  15  18  21  3
Shift Right:    21  3   6   9   12  15  18
```

Notice that we have this **wrapping around** behavior, where if an element would be pushed off the end of the array in either direction, it instead wraps around to the opposite side of the array. Doing this ensures we don't lose any elements.

Our algorithm to do this is going to have go one element at a time, store it temporarily, and overwrite it with whatever has to move there As an example with the shift left example, here is what the steps would make the array look like:

```
Index:      0   1   2   3   4   5   6
Elements:   3   6   9   12  15  18  21
Step 1:     6   6   9   12  15  18  21
Step 2:     6   9   9   12  15  18  21
Step 3:     6   9   12  12  15  18  21
Step 4:     6   9   12  15  15  18  21
Step 5:     6   9   12  15  18  18  21
Step 6:     6   9   12  15  18  21  21
Step 7:     6   9   12  15  18  21  3
```

Notice that as we go, we seem to always have a duplicate element until the end, when it finally gets replaced with the wrap-around element. This means that we will have to keep track of the wrap-around element the whole time, and replace it at the end. We couldn't shift it over earlier without overwriting the 21 too early (in which case we would have to keep track of the 21 and continue on).

Here are the basic steps of this algorithm:
- Create a temporary variable and store the first element of the array in it.
- Iterate over the array, ending one element early:
    - Overwrite the current value of the array with the next value in the array by index.
- Overwrite the last value of the array with the original first element saved in the temporary variable.

Here is what this algorithm could look like from the `NotesShift1.java` file:

```java
public static void shiftLeft(int[] nums) {
    int first = nums[0];
    for (int i = 0; i < nums.length - 1; i++) {
        nums[i] = nums[i + 1];
    }
    nums[nums.length - 1] = first;
}
```

Important Notes:
- Our loop uses the `boolean` condition `i < nums.length - 1` because when we use the index `i + 1` in the loop, we have to end the loop one iteration early, otherwise we would have an ArrayIndexOutOfBoundsException.
- To access the last index after the loop we use the length minus 1, written as `nums.length - 1`.
- This method doesn't need to return because when an array is passed as a parameter, it is a reference, so we are editing the original array!

### Reverse the Order of Elements

Just as we can shift elements left or right, we can reverse the order of an array as well. Here is a visual example:

```
Index:      0   1   2   3   4   5   6
Elements:   3   6   9   12  15  18  21
Reverse:    21  18  15  12  9   6   3
```

Notice that the middle value of the array did not change, which is because the array has a length of `7`, which is odd. An even array would have everything changed:

```
Index:      0   1   2   3   4   5
Elements:   3   6   9   12  15  18
Reverse:    18  15  12  9   6   3
```

An algorithm that reverses can be thought of a repeated set of swaps. We could start by swapping the first and last elements, then move in one on each side and swap the second and second to last elements, etc. until we arrive at the middle. The steps could look like this:

```
Index:      0   1   2   3   4   5
Elements:   3   6   9   12  15  18
Step 1:     18  6   9   12  15  3
Step 2:     18  15  9   12  6   3
Step 3:     18  15  12  9   6   3
```

Interestingly, this takes half as many steps as our shifting, because we are moving towards the middle from both ends, as opposed to going from one end to the other. Interestingly, this idea still works with an odd-length array if you think of the last step as swapping the middle with itself:

```
Index:      0   1   2   3   4   5   6
Elements:   3   6   9   12  15  18  21
Step 1:     21  6   9   12  15  18  3
Step 2:     21  18  9   12  15  6   3
Step 3:     21  18  15  12  9   6   3
Step 4:     21  18  15  12  9   6   3
```

The lack of change from step 3 to step 4 was our "swap in-place" of our middle element. So a length of `6` took `3` steps, and a length of `7` took `4` steps. These are both close to half, but unfortunately `7 / 2` is equal to `3` in Java with `int` division. Fortunately though, the length of `7` was done after `3` steps, since the final step didn't actually change anything significant. This means that the number of steps for even or odd can just be calculated as the `length / 2` with `int` division!

It is important to remember that if we want to swap the values of two variables (and therefore two elements of an array), we are going to need a third temporary variable so we don't permanently lose one of the values while moving things around.

The only question left is, how do we have a loop that simultaneously keeps track of two indices? The weird answer is that we don't, we just track one index and use math to determine the other every time. For example, if the first swap needs to be between indices `0` and `6` (this is the length of `7`). If we are keeping track of the index `0`, we can use the length and the current index to do the math to get the other index of `6`, which would look something like `length - index - 1`. Here that would be `7 - 0 - 1`, which works. The next step should be `1` and `5`, and the calculation would be `7 - 1 - 1` so `5` is calculated correctly. This works for the even version to, with `0` and `5` calculating `6 - 0 - 1` to be `5`, and with `1` and `4` calculating `6 - 1 - 1` to be `4`.

Here are the basic steps of this algorithm:
- Iterate over the array from the beginning to the middle:
    - Make a temporary variable to store the current array element.
    - Set the current array element to the opposing array element using the `length - index - 1` formula.
    - Set the opposing array element to the temporary variable value.

Here is what this algorithm could look like from the `NotesReverse1.java` file:

```java
public static void reverse(int[] nums) {
    for (int i = 0; i < nums.length / 2; i++) {
        int temp = nums[i];
        nums[i] = nums[nums.length - i - 1];
        nums[nums.length - i - 1] = temp;
    }
}
```

Important notes:
- A loop from `0` to `length / 2` will do exactly how many steps we need, and go through the right indices at the same time.
- This method doesn't need to return because when an array is passed as a parameter, it is a reference, so we are editing the original array!

---

## Assignment

Now that you have gone through the notes for this section, you can check out the `Try.md` and `Try.java` files to try a short assignment using this material.
