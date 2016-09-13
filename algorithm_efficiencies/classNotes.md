# Algorithm Efficiency

## Concept
A piece of software can only run as fast as the algorithm will allow. This means a 99 core machine with 50TB of RAM will run as fast in scale as a 1 core machine with 256MB of RAM if the algorithm is inefficient. This is obviously important.

## Motivation
* Open your editors and write code to compute the sum of 1 to n

```java
public class BadAlgorithmEfficiency {
  public static void main(String[] args){

    // our low number to high number
    final int START = 1;
    final int END = 10;


    int sum = 0;

    // our start and end times
    long startTime, endTime;

    // start the clock
    startTime = System.nanoTime();

    for(int i = START; i <= END; i++){
      for(int j = 1; j <= i; j++){
        sum++;
      }
    }

    endTime = System.nanoTime();

    System.out.println("The sum of " + START + " to " + END + " is " + sum + " and took " + (endTime-startTime) + " nanoseconds");
  }
}

```

* What is the time in nanoseconds to complete?
  * Run again with END = 100 - record time in nanoseconds
  * Run again with END = 1,000 - record time in nanoseconds
  * Run again with END = 10,000 - record time in nanoseconds
  * Run again with END = 100,000 - record time in nanoseconds
  * Run again with END = 1,000,000 - record time in nano seconds
    * FYI - this takes a while. Give it a few minutes.


* Write a faster version!
  * Repeat the trials from above, but take to 10,000,000 this time.
  * First person to beat my average time of 250 nanoseconds for 10,000,000 and you get 5 extra credit points.

## Measuring an Algorithm's Efficiency
### Concept
* Efficiency matters!
* Space matters!

### What? Aren't efficiency and space diametrically opposed?
* YES!
* We sacrifice speed to save space. We sacrafice space to increase speed.
  * This is the ***most important concept*** to get from this class.
* If we have the room and can use the room, make highly efficient algorithms
* If we are limited on room, evaluate which algorithms can be sacrificed.
* We call this these two opposing forces
  * Space Efficiency
  * Time Efficiency
* This means the *best* algorithm might not be the fastest or the one that uses the least memory.
  * This reinforces **THE MOST IMPORTANT CONCEPT** of this class.

### Counting Basic Operations
* We will not go past this concept in this class.
* The basic operation is the *most significant* contributor to its total time requirement.
  * We do not care how long it takes, just how many times we have to do it

```java
for(int i = 0; i < TOTAL_TIMES; i++){ sum += i; }
```

* What is the *most significant* operation?
* How many times did we execute the *most significant* operation?

```java
for(int i = 1; i <= TOTAL_TIMES; i++){
  for(int j = 1; j <= i; j++){
    sum++;
  }
}
```

* What is the *most significant* operation?
* How many times did we execute the *most significant* operation?
  * Trickier this time?

### Best, Worst, Average Cases
* For the above example, the best case was `n * 1`
* For the above example, the average case was `n * (sumOfN/n)` - dont worry about `sumOfN/n`, just treat is as a real number.
* For the above example, the worst case was `n * n`

* Is this true? `n * 1 < n * (sumOfN) < n * n`
  * Using real numbers where `n = 10`,
    * `10 * 1 < 10 * (55/5) < 10 * 10`
    * `10 < 55 < 100`
  * So yeah, we are right on.

* So which do we care about?
  * All of them!
  * If we were able to look at the incoming distribution of a set, and we were to know 99% of the time we would get the Best case scenario, then we can argue best case is what we need.
  * Conversely, if we had no idea the incoming distribution of a set, we might want to go with Worst Case.
  * Example:

  ```
  I know from lots of experience that out of 100 students, 
  only 1 to 3 people will submit their assignment on time. 
  In this case, I could, but maybe shouldn't, care only about 
  the Best Case.

  I know from lots of experience that out of 100 students, 
  about 40 to 60 people will submit their assignment on time. 
  In this case, I can optimize for Average Case.

  I know from lots of experience that out of 100 students, 
  most people, maybe 90 to 95 people will submit their 
  assignment on time. In this case, I can ignore both 
  Best Case and Average Case and focus only on optimizing 
  the Worst Case.
  ```

* In this class, we will only concern ourselves with Worst Case!
  * We call the worst case scenario `Big Oh` or `Big O`.
    * I particularly like the second option.
  * But so you know, and you will be tested on which is which
    * Best Case is known as `Big Omega`
    * Average Case is known as `Big Theta`

### Getting the Big O
* So know you have probably guessed that loops have a lot to do with Big O evaluation.
  * This should be pretty obvious since it is *how many times* we need to do the most significant operation.
* But how do we write this notation?

```java
for(int i = 0; i < n; i++){ sum++; }
```

* Here we loop `n` times. Typically `n` is used as the variable in the math equation to solve these. We will not compute the math formally, but we can intuitively figure it out.
  * The sum would be incremented `n` times.
  * Our Big O notation would be `O(n)`
    * Realize the obvious, `O()` represents a function in mathematics which is pretty much the same concept as a method in Java.

* Lets make this a bit more complicated
  * Compute the number of times `sum++` executes

```java
// example 1
for(int i = 0; i < n; i++){
  for(int j = 0; j < m; j++){
    sum++;
  }
}
```

* Lets look at another example
  * Which is most significant?

```java
// example 2
for(int i = 0; i < n; i++){
  sum++;
  tick++;
}
```

* Now, more complicated

```java
// example 3
public void doIt(){
  for(int i = 0; i < n; i++) sum++;
}

for(int i = 0; i < n; i++) doIt();
```

* So for the above, our Big O notations would be:
  * Example 1: `O(nm)`
  * Example 2: `O(2n)` -> `O(n)`
    * In this class, we will not consider constants in computing complexity
  * Example 3: `O(n^2)`

### Growth Rates
**Big O**       **Effect on Time Requirement (Size of n)**
O(1)            Constant - None
O(ln)           Logarithmic - Negligible
O(n)            Linear - The time increases equally proportional to size of *n*
O(n log n)      "nLogN" - Slows noticeably as *n* increases
O(n^2)          Polynomial - Explodes on small increases of *n*
O(n^m)          Polynomial - Explodes faster on larger *m* with smaller *n*
O(2^n)          Exponential - Explodes immediately

* Do not use anything worse than polynomial!!!!!!! EVER!!!!!!!
  * Typically we will resort to AI techniques to solve large *m* polynomials or worse.

#### Our ADTs
* Bag
  * Insert - `O(1)`
  * Remove last inserted - `O(1)`
  * Remove given (search) - `O(n)`
* Stack
  * Insert - `O(1)`
  * Remove last - `O(1)`
* Queue
  * Insert - `O(1)`
  * Remove first - `O(1)`
* Dequeue
  * Insert - `O(1)`
  * Remove - `O(1)`
