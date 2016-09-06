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
* `public T unshift()`
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
  public T unshift();
  public T peek();
  public boolean isEmpty();
  public void clear();
}
```
