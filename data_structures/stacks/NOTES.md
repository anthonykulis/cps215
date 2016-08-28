# Stacks

## Concepts
* Common every day items are stacked
  * Books
  * Dishes
  * Boxes

* You can only add and remove from the top one at a time.
* **LAST-IN FIRST-OUT** aka *LIFO*
* Similar to *Bag* except we only work with the last element added (top of the stack).
* Yes, this is the same type of *stack* from the phrase *stack and heap*

### Strengths
* Add rapidly
* Remove rapidly
* "Focuses" on task at hand - only last pushed element is available to us

### Weaknesses
* Unlike bag, we cannot look at its contents, only last element
* Has limited size

### When to use
* There are many instances of a stack in action.
  * Calculators
  * Depth First Search
  * Browsers *Back* and *Forward* buttons
  * In compilers

* Use a stack when the order matters and the when the retrieval might be interrupted by some other actions (e.g. push items back on stack). Remember, if the solution to a problem calls for First In Last Out, you might want a stack.

### Responsibilities of the stack
* Push new items onto the stack
* Pop the last item off the stack
* Peek at the top of the stack

## Stack Methods
* `public void push(T item)`
  * Push a new item onto the stack
  * The book suggests a `void` return type. Is this logical?
* `public T pop()`
  * Removes last item pushed
* `public T peek()`
  * Returns, but does not remove, the last item pushed
* `public boolean isEmpty()`
  * Is the stack empty?
* `public void clear()`
  * Clear the stack

### Our interface
```java
public interface StackInterface<T>{
  public void push(T item);
  public T pop();
  public T peek();
  public boolean isEmpty();
  public void clear();
}
```

## Not Enough!
* Our stack is limited. We can only take from the top.
* Many programming languages offer a way to take from the bottom.
  * `public T shift()` - identical to `pop` but works on the front
  * `public void unshift(T item)` - identical to `push` but works from the front

* We will create a second interface `StackShiftUnshiftInterface` and add these methods.

### StackShiftUnshiftInterface
```java
public interface StackShiftUnshiftInterface<T>{
  public void unshift(T item);
  public T shift();
}
```

## Introduction of the Node

### Concept
* The `Node` class is a heap implementation!
* Leveraging the heap, we can create an object that points to another object.       
  * The array is implemented on stack.
    * This limits growth unless we allocate more memory
    * Allocation is expensive.
* Each node points to the next Node instance
  * Access via `public Node next()`
* Each node points to one single generic item
  * Access via `public T getItem()`
* Requires our implementation to reference one|two Nodes depending on interfaces used
  * Keep a reference to the top of the stack for `StackInterface`
  * Keep a reference to the bottom of the stack for `StackShiftUnshiftInterface`

### Node Class
```java

public class Node<T>{

  private T _data;
  private Node _next;

  public Node(T item, Node next){}

  public Node next(){}

  public void link(Node next){}

  public Node unlink(){}

  public T getData(){}

  public void setData(T item){}

}
```


### Stack Implementation using both interfaces
```java
public class Node<T> implements StackInterface, StackShiftUnshiftInterface{
  private T _top, _bottom;

  public Node(){}

  public void push(T item){}

  public T pop(){}

  public void unshift(T item){}

  public T shift(){}

  public T peek(){}

  public boolean isEmpty(){}

  public void clear(){}   

}
```
