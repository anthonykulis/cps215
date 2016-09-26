# Recursion
* Have you had enough?
* What is recursion?
  * See the above header *Recursion*

## Really, What is Recursion
* Recursion is a problem solving process that breaks a problem into identical but smaller problems.
  * It is a lot like iteration except when we get into complicated problems, it becomes much more succinct and easier to read and code.
  * In fact, programming languages give 2 methods of looping, iteration and recursion.

* In this example we will ask each step in the recursion to increase the sum until we have nothing left to add. Once its summed up (pun intended), we then stop the recursion and return the sum.

```java
public class SimpleRecursionProblem {
  public long sum1to(long n){
    long sum = 1;
    if(n > 1) sum = this.sum1to(n - 1) + n;
    return sum;
  }

  public static void main(String[] args){
    SimpleRecursionProblem srp = new SimpleRecursionProblem();
    long end = 1;
    if(args.length > 0) end = Long.valueOf(args[0]);
    System.out.println("Sum of 1 to " + end + " is " + srp.sum1to(end));
  }
}
```

* *Recursion* in lay terms is a method that calls itself.
  * Lets have fun with the main method!

```java
public class RecursiveMain {
  public static void main(String[] args){

    if(args.length != 0){

      // print our first arg
      System.out.print(args[0] + " ");

      // reduce our args - our stoping condition
      String[] temp = new String[args.length - 1];
      System.arraycopy(args, 1, temp, 0, args.length - 1);

      // start over
      RecursiveMain.main(temp);

    } else {
      System.out.println();
    }
  }
}
```

## Tracing a recursive method
* Lets trace `RecursiveMain`.
  * Note it is a bit difficult to write what the stack looks like, so we will look at this as "scoped" if you will.

```java
public static void main(String[] args){
  if(args.length != 0){
    ...
    ...
    ...
    public static void main(String[] args){
      if(args.length != 0){
        ...
        ...
        ...
        public static void main(String[] args){
          ...
          ...
          ...
          if(args.length != 0){
            ...
            ...
            ...
            public static void main(String[] args){}
          }
        }
      }
    }
  }
}
```

* Do you notice a potential issue?
  * We know if we have an infinite loop we may run forever, but this is not the case with recursion.

* Try running this *broken* version
  * Find the `StackOverFlowError`
    * What does this mean? Hint: *The stack is _____ in size*

```java
public class BrokenRecursiveMain {
  public static void main(String[] args){

    int num = 0;
    if(args.length > 0){
      num = Integer.valueOf(args[0]);
    }
    System.out.println(num);
    num++;
    String[] temp = {num+""};

    // start over
    BrokenRecursiveMain.main(temp);

  }
}
```

* Every time we call a method, we push that method onto the stack. As we call another method in the method, we push that method onto the stack inside the current method. If we have no stopping condition, this goes on until we run out of stack. **THIS IS VERY IMPORTANT TO UNDERSTAND**
  * [Tail Recursion Optimization](http://www.drdobbs.com/jvm/tail-call-optimization-and-java/240167044)

# Tail Recursion
* When the last method call in the method is calling itself
  * Does not include methods that do "extra" like add the recursive sum

```java
public void tailCall(){
  // do something
  // do something else
  tailCall();
}
```

# Indirect Recursion
* When method *A* calls method *B* which calls method *A*

```java
public void first(){
  second();
}

public void second(){
  first();
}
```
