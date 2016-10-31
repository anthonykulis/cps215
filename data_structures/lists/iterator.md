# Iterators

## Concepts
Allows the user to *traverse* a collection.

## Methods
* `public boolean hasNext()`
  * Returns TRUE | FALSE if the collection has more items to traverse
* `public T next()`
  * Returns the next item in the list
    * Throws an exception otherwise

## Iterator Interface

```java
public interface IteratorInterface<T>{
  public boolean hasNext();
  public T next();
}
```
