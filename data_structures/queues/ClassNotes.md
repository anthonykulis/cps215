# Queues

## Concepts
* Common everyday items are a queue
  * Lines at grocery store
  * Video Streams (concept only)
  * CPU Scheduler (Resources availability)

* You can only add to the back and remove from the front
* **FIRST_IN FIRST-OUT** aka **FIFO**
* Very similar to a stack except where we remove/peek from.

### Stengths
  * Add rapidly
  * Remove rapidly
  * Keeps order in the sense that it is a FIFO

### Weaknesses
* Similar to the stack, we cannot look at its contents

### When to use
* When arrival order matters. Imagine a CPU that was scheduling resources and it schedule a read from a disk buffer before you opened the disk buffer.

* Use a queue when the order of arrival matters, or when there is a reason that event *b* should come after event *a*

## Queue Methods
* `public void push(T item)`
  * Push a new item onto the queue
  * I am mimicking the stack push signature, would we rather have a different return type? If so, when?
* `public T shift()`
  * This differs in name dramatically then you may see else where, but when we implement a `Deque`, you will understand why.
* `public T peek()`
  * Returns, but doesn't remove, the front of the queue
* `public booelan isEmpty()`
  * Is the queue empty?
* `public void clear()`
  * Clears the queue

### Queue interface

```java
public interface QueueInterface<T> {
  public void push(T item);
  public T shift();
  public T peek();
  public boolean isEmpty();
  public void clear();
}
```

### Queue Implementation

```java
public class NodeQueue<T> implements QueueInterface<T> {

  private Node<T> _front, _back;

  public NodeQueue(){}

  public void push(T item){}

  public T shift(){}

  public T peek(){}

  public boolean isEmpty(){}

  public void clear(){}
}
```
#### Constructor
```java
public NodeQueue(){
  this.clear();
}
```

#### Push
* Realize we have 2 sides now, front and back, and we need to handle that accordingly

```java
public void push(T item){
  Node<T> back = new Node<>();
  back.setData(item);

  if(this.isEmpty()){
    this._front = back;
  } else {
    this._back.link(back);
  }

  this._back = back;
}
```

#### Shift
* Again, we are encapsulating `Node`, so only return the data passed

```java
public T shift(){
  if(this._front == null){ return null; }

  Node<T> front = this._front;
  this._front = this._front.next();
  return (T)front.getData();
}
```

#### Peek

```java
public T peek(){
  if(this._front == null){
    return null;
  }

  return this._front.getData();
}
```

#### isEmpty and clear

```java
public boolean isEmpty(){
  return this._front == null;
}

public void clear(){
  this._front = null;
  this._back = null;
}
```
