# Iterators

## Concepts
Allows the user to *traverse* a collection.

## Methods
* `public boolean hasNext()`
  * Returns TRUE | FALSE if the collection has more items to traverse
* `public T next()`
  * Returns the next item in the list
    * Throws an exception otherwise
* `public int index()`
  * Returns the index of the iterators current position, which is the value of the index *after* `next()` is called

## List Iterator Interface

```java
public interface ListIteratorInterface<T>{
  public boolean hasNext();
  public T next();
  public int index();
}
```
