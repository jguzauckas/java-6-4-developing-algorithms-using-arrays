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
public static int findAverage(int[] nums) {
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




---

## Assignment

Now that you have gone through the notes for this section, you can check out the `Try.md` and `Try.java` files to try a short assignment using this material.
