# LinkedList

## Concept
A list is a ADT that allows the user to collect items that can be iterated and grow indefinitely. The two primaries lists are `LinkedList` and `DoublyLinkedList`. We will cover `LinkedList` here.

We will use `Node` to link data.

### Strengths
* Indefinitely grows
* Can insert an item where ever needed
* Can remove items from where ever they exist.

### Weaknesses
* `O(n)` to get
  * can be reduced to `O(1)` with hashes and mapping or using arrays with indexes
* `O(n)` to search
  * can be reduced to `O(log n)` with an ordered list and binary search
* `O(n)` to insert
  * difficult to reduce, but can be done using `Dictionaries`
* `O(n)` to remove
  * difficult to reduce, but can be done using `Dictionaries`



## When to use
* When you need to keep track of many items whose FIFO/FILO type order doesn't matter.
  * We can over come all the weaknesses with hybridization.


## List Methods
* Unlike previous collections, I will only provide the core methods. Hybridization will add this dramatically.

* `public void add(T item)`
  * Adds an item to the end of the list
* `public void add(T item, int index)`
  * Adds an item to a specific position in list
* `public T remove()`
  * Removes the first item of the list
* `public T remove(int index)`
  * Removes an item at a given index

* `public int size()`
  * Returns the size of the list
* `public void clear()`
  * Clears the list
* `public boolean isEmpty()`
  * Returns `true` if empty, `false` otherwise
* `public Iterator<T> iterator()`
  * Returns an *anonymous* iterator for the list


## List Interface

```java
public interface ListInterface<T> {
  public void add(T item);
  public void add(T item, int index);
  public T remove();
  public T remove(int index);
  public int size();
  public void clear();
  public boolean isEmpty();
  public ListIterator<T> iterator();
}
```
