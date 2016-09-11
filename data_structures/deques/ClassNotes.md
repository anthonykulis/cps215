# DeQues
* Pronounced *Decks*

## Concepts
* A hybrid Stack-Queue
* Allows us to work from both ends


### Stengths
  * Add rapidly
  * Remove rapidly
  * Keeps order in the sense that it is a FIFO and LIFO if we so choose.
  * Allows for *loose* programming giving us more power.

### Weaknesses
* Similar to the stack, we cannot look at its contents
* Requires attention to detail

### When to use
* When we need both a queue and a stack on the same data set

## The DoublyLinkedNode
* Introducing the DoublyLinkedNode
  * Provides links to both next and previous
  * Used in the Deque, and will also be used later in Lists
  * Not required for this upcoming test, but will be required on future tests
* Similar to Node providing the additional previous orientated methods (extended from Node)
  * Caveat - in generics, inheritance can become tricky.

### DoublyLinkedNode Class

```java
package data_structures.nodes;

public class DoublyLinkedNode<T> extends Node<T>{

  private DoublyLinkedNode _previous;

  public DoublyLinkedNode(){}

  public DoublyLinkedNode(T item, DoublyLinkedNode next, DoublyLinkedNode previous){}

  // why is this method here if we are extending Node?
  public DoublyLinkedNode next(){}

  public DoublyLinkedNode previous(){}

  public void unlinkPrevious(){}

  public void linkPrevious(DoublyLinkedNode previous){}
}
```

### DoublyLinkedNode Implementation

#### Constructors

```java
public DoublyLinkedNode(){
  super();
  this._previous = null;
}

public DoublyLinkedNode(T item, DoublyLinkedNode next, DoublyLinkedNode previous){
  super(item, next);
  this.linkPrevious(previous);
}
```

#### Next
```java
public DoublyLinkedNode next(){
  return (DoublyLinkedNode)super.next();
}
```

#### Previous Accessors/Mutators

```java
public DoublyLinkedNode previous(){
  return this._previous;
}

public void unlinkPrevious(){
  this._previous = null;
}

public void linkPrevious(DoublyLinkedNode previous){
  this._previous = previous;
}
```


## Deque Methods
* `public void push(T item)`
  * Same as stack push
* `public T pop()`
  * Same as stack pop
* `public void unshift(T item)`
  * Similar to `push`, except we push to the front
* `public T shift()`
  * Same as queue shift
* `public T peekLast()`
  * Same as stack peek, just renamed
* `public T peekFirst()`
  * Same as queue peek, just renamed
* `public booelan isEmpty()`
  * Is the queue empty?
* `public void clear()`
  * Clears the queue

### Deque interface

```java
public interface QueueInterface<T> {
  public void push(T item);
  public T pop();
  public void unshift(T item);
  public T shift();
  public T peekFirst();
  public T peekLast();
  public boolean isEmpty();
  public void clear();
}
```

### Deque Implementation

#### Members

```java
private DoublyLinkedNode _front, _back;
```

#### Constructors

```java
public NodeDeque(){
  this.clear();
}
```

#### Push

```java
/*
  two possibilities:
    1) deque is empty, so no linking
    2) deque is not empty, so need to create a new back side
       and link the previous back to the new back BOTH directions
*/
public void push(T item){

  // create our node
  DoublyLinkedNode<T> n = new DoublyLinkedNode<>();

  // set data as usual
  n.setData(item);

  // first condition - empty
  if(this.isEmpty()){

    // back is also front under this condition
    this._front = n;

  } else {

    // link old back to new back as in stack
    this._back.link(n);

    // also link new back to old back for deque
    n.linkPrevious(this._back);
  }

  // always set the back to new node
  this._back = n;
}
```

#### Pop

```java
/*
  When popping, need to unlink both nodes in BOTH directions
  But we could have only one in the queue, so be careful to check
*/
public T pop(){

  if(this.isEmpty()){ return null; }

  // need last node and previous node
  DoublyLinkedNode<T> n = this._back;
  DoublyLinkedNode<T> back = n.previous();

  if(back != null){
    // unlink the new back from the current back
    back.unlink();

    // unlink the current back from the new back
    n.unlinkPrevious();
  }

  // set the new back - includes null
  this._back = back;

  // if back is null, then queue empty. cant use isEmpty bc we are
  // updating this._front.. have to be imperative
  if(this._back == null){
    this._front = null;
  }

  T data = (T)n.getData();
  n.clearData();

  // return the data
  return data;
}
```

#### Unshift

```java
/*
  Two possibilities:
    1. Deque is empty
    2. Deque is not empty

  Handle both
*/
public void unshift(T item){

  // create our new node and add its data - nothing new
  DoublyLinkedNode<T> n = new DoublyLinkedNode<>();
  n.setData(item);

  // similar to push, apply our two conditions
  if(this.isEmpty()){

    // empty? no linking required
    this._back = n;
  } else {

    // link the new front to old front
    n.link(this._front);

    // link old front to new front
    this._front.linkPrevious(n);
  }

  // set out new front
  this._front = n;
}
```

#### Shift

```java
/*
  When shifting, as with popping, we need to unlink the two nodes,
  BOTH directions
*/
public T shift(){
  if(this.isEmpty()){ return null; }

  // grab the first two. will need both for data and linking
  DoublyLinkedNode<T> n = this._front;
  DoublyLinkedNode<T> front = n.next();

  // if n is not the only node!
  if(front != null){

    // forget about the new front node
    n.unlink();

    // forget about the old front node
    front.unlinkPrevious();

  }

  // update front
  this._front = front;

  // update back if the shift was on the only element known
  if(this._front == null){
    this._back = null;
  }

  // return our data
  T data = (T)n.getData();
  n.clearData();
  return data;
}
```

#### Peeks

```java
public T peekFirst(){
  if(this.isEmpty()){ return null; }
  return (T)this._front.getData();
}

public T peekLast(){
  if(this.isEmpty()){ return null; }
  return (T)this._back.getData();
}
```

#### Utility Methods

```java
public boolean isEmpty(){ return this._front == null; }

public void clear(){ this._front = this._back = null; }
```
