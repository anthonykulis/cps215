# More About Generics
So far you have seen the generic type <T> used in classes and methods that return the generic type. Not all classes need to be generic, but may require a method to be generic. This will cover creating those generic methods, use the interface `Comparable`, bounded type parameters for said generic methods, and introduce wild cards and bounded wild cards.


## Comparable interface
* `Comparable` has one method to implement - `compareTo`
* The return type is an `int`
* The only parameter is an object of type `T`

```java
package java.lang;

public interface Comparable<T> {
  public int compareTo(T o);
}
```
This is a *natural* comparison.
  * While it might not be obvious, do not compare an object to `null`, you should throw a `NullPointerException`

```java
x.compareTo(y);
```

`compareTo` returns values of:
  * `negative int` when `x` < `y`
  * `0` when `x` == `y`
  * `positive int` when `x` > `y`

Note: if `x` and `y` have different types, throw a `ClassCastException`
 * We will work in a sandbox, so inside this class you do not need to worry about this, but in a enterprise setting, this will need to be handled.

```java
public class Circle implements Comparable<Circle> {
  double radius;

  public int compareTo(Circle other){
    if(this.equals(other)) result = 0;
    else if (radius < other.radius) return -1;
    else return 1;
  }
}
```  
Because we don't care how negative (or how positive) the return value is, we can simply modify the above `compareTo` method to:

```java
public int compareTo(Circle other){
  return radius - other.radius;
}
```

Let's look at enumerations as a caveat

```java
enum Coin { PENNY, NICKLE, DIME, QUARTER, DOLLAR };
```

* In this previous example, the `compareTo` method will return the *position* in the enumeration compared to the other enumeration value's position.

## Generic methods
As already mentioned, generics can be limited to a method. This means you do not need to declare a class generic.

Try this example in your editor.

```java
public class Example {
  public <T> void displayArray(T[] someArray){
    for(T item : someArray){
      System.out.print(item + " ");
    }
    System.out.println();
  }

  public static void main(String[] args){
    Example e = new Example();

    String[] things = {"apple", "banana", "carrot", "dandelion"};
    e.displayArray(things);

    Character[] letters = {'a', 'b', 'c', 'd'};
    e.displayArray(letters);
  }
}
```

## Bounded Type Parameters
Imagine the following:

```java
public class Square<T>{
  private T _side;
  public Square(T side){
    this._side = side;
  }

  // broken - wont compile
  public double getArea(){
    double s = this._side.doubleValue();
    return s * s;
  }
}
```

```java
Square<Integer> s = new Square<>(25);
```

In the above, the compiler will fail even though we are using type `Integer` which has the `doubleValue` method. That is because `T extends Object`, not `Number`

* An obvious example of this is `Square<String> s = new Square<>("Dog");`

So let's limit this with a `bound`
  * In this case, `Number` is the **upper bound**.
  * Side note, since all objects extend `Object`, when we do not declare the bound, `<T>`, it is assume we are a immediate child of `Object`, and hence, `Object` will be the upper bound even if not defined as `<T extends Object>`.

```java
public class Square<T extends Number>{
  private T _side;
  public Square(T side){
    this._side = side;
  }
  public double getArea(){
    double s = this._side.doubleValue();
    return s * s;
  }
}
```

### Lower bounds
We have seen that `? extends SomeClass` has an upper bound of `SomeClass`,
but `? super SomeClass` means `?` can be any class that is the super class of `SomeClass`, so hence, `SomeClass` is the **lower bound**.

### Bounded Methods
The previous example was for a class, but we can do the same with methods.
  * Here, we limit our items that can be compared to find the minimum as long as they have the interface `Comparable` implemented. Because `Comparable` is generic with type `<T>`, we must include that in the extension signature just like any other time.

```java
public Find {
  public static <T extends Comparable<T>> T arrayMinimum(T[] array){
    T min = array[0];
    for(T item : array){
      if(item.compareTo(min)) < 0) min = item;
    }
    return min;
  }
}
```

## Wildcards
The `?` is used to represent an unknown class type and is referred to as a **wildcard**.

Instead of showing a basic example, which in my opinion is confusing, let's jump right into a bounded wildcard. From this, we can reverse the logic to an unbounded example.

```java
// a better version of the previous example
public class Find{
  public static <T extends Comparable<? super T>> void arrayMinimum(T[] array){
    ...
  }
}
```

With this example, while it may look confusing, what we have effectively done is allow any super class of our type `T` that implements `Comparable` to be compared. We have said inside the `Comparable<>` is to allow any type whose child is type `T`.
  * Imagine we had a class `Gadget` that extends `Widget` in which `Widget implements Comparable<T>`, we can now compare `Gadget` with `compareTo`.
    * Since we are extending `Widget` into `Gadget`, we can also now compare `Widget` and `Gadget` instances assuming we use the parents `compareTo`.
  * To use `Comparable` with arbitrary types, write `Comparable<? super T>` instead of `Comparable<T>`

### Unbounded Wildcards
Now that you have seen the bounded wildcard limit types to anything that extends the upper bound, you can also see `?` without a bound will represent any class.
