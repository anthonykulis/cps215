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

### Queue interface

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
