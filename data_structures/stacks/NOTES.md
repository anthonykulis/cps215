# Stacks

## Concepts
* Common every day items are stacked
  * Books
  * Dishes
  * Boxes

* You can only add and remove from the top one at a time.
* **LAST-IN FIRST-OUT** aka **LIFO**
* Similar to *Bag* except we only work with the last element added (top of the stack).
* Yes, this is the same type of *stack* from the phrase *stack and heap*

### Strengths
* Add rapidly
* Remove rapidly
* "Focuses" on task at hand - only last pushed element is available to us

### Weaknesses
* Unlike bag, we cannot look at its contents, only last element
* Has limited size?

### When to use
* There are many instances of a stack in action.
  * Calculators
    * Postfix vs Infix
  * Depth First Search
  * Browsers *Back* and *Forward* buttons
  * In compilers
    * Balanced delimiters - example: `{{(){}(){()[]}(){}}}`

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

## Introduction of the `Node`

### Concept
* The `Node` class is a heap implementation!
* Leveraging the heap, we can create an object that points to another object.       
  * Alternatively - The array is implemented on stack.
    * This limits growth unless we allocate more memory
    * Allocation and reallocation of memory used in arrays is expensive.
* Each node points to the next Node instance
  * Access via `public Node next()`
* Each node points to one single generic item
  * Access via `public T getData()`
* Requires our implementation to reference one Node
  * Keep a reference to the top of the stack for `StackInterface`

### Node Class
```java

public class Node<T>{

  private T _data;
  private Node _next;

  public Node(){}
  public Node(T item, Node next){}

  public Node next(){}

  public void link(Node next){}

  public void unlink(){}

  public T getData(){}

  public void setData(T item){}

}
```

### Node Implementation

#### Constructors
```java

public class Node(){
  this._data = null;
  this._next = null;
}

public Node(T item, Node next){
  this.setData(item);
  this.link(next);
}
```
#### Getting the next node
```java
public Node next(){
  return this._next;
}
```

#### Linking the next Node
```java
public void link(Node next){
  this._next = next;
}
```

#### Unlink the next Node
```java
public void unlink(){
  this._next = null;
}
```

#### Getting the nodes data
```java
public T getData(){
  return this._data;
}
```

### Setting the nodes data
```java
public void setData(T data){
  this._data = data;
}
```




### Stack Implementation
```java
public class NodeStack<T> implements StackInterface<T> {
  private Node _top;

  public NodeStack(){}

  public void push(T item){}

  public T pop(){}

  public T peek(){}

  public boolean isEmpty(){}

  public void clear(){}   

}
```

#### Constructor
* Make life declarative, simply call clear
```java
public NodeStack(){
  this.clear();
}
```

#### Push
* We have two possibilities here
  1. Either the stack is empty
  2. Or the stack has data already
* We need to pay attention to our composite Node class
  * It only has two constructors
    * One takes no arguments
    * The overloaded one requires both a `T item` and a `Node next`
  * Since we dont have a `next` on first push, we have to do an extra step.
* Also, since we are encapsulating `Node`, the user will only push their actual data. This means we need to create a Node object to use.
```java
public void push(T item){
  Node<T> n;
  if(this.isEmpty()){
    n = new Node<>();
    n.setData(item);
  } else {
    n = new Node<>(item, this._top);
  }

  this._top = n;
}
```

#### Pop
* Since we are encapsulating `Node`, we need to perform the `getData()` method on it before we return the value.
```java
public T pop(){
  Node<T> n = this._top;
  this._top = n.next();
  return (T)n.getData();
}
```

#### Peek
* Simply return the data sitting on top of the stack
```java
public T peek(){
  return (T)this._top.getData();
}
```

#### isEmpty and clear
* We've seen this before. Its pretty much the same as all the others.

```java
public boolean isEmpty(){
  return this._top == null;
}

public void clear(){
  this._top = null;
}
```
