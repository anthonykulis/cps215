# Bags

## Concepts

### Abstract Data Type (ADT)
* specification for a group of values and the operations on those values that is defined conceptually
* independent of a programming language

### Collection
* An object that groups other objects and provides functionality to add, remove, retrieve, and query those objects
* An array is a Collection

## The Bag

### What is a bag?
* No different than any physical bag you can think of, except it is implemented in code
* We can add to it rapidly
* There is no inherent ordering (e.g. alphabetical)
* There is no sorting (e.g. by size)
* We don't care if there are duplicate items (e.g. multiple references of the same instance)
* Slow to find items

### Responsibilities of the Bag
* Get the number of items in the bag
* See whether the bag is empty
* Add an object to the bag
* Remove an item from the bag (specified and unspecified)
* Remove all items from the bag
* Count the number of times a type shows up in the bag
  * Is there a problem with this? I think so
    * If we use generic types, unless we do some crazy lifting, we will need to depend on a `toString()` or similar type method to give us the type. If for some reason another engineer uses our class, they will have to know that the extra work in their bag item is required. This is no good. Not only does it *tightly couple* non-related classes, it also puts engineers into knowing special circumstances about our class. This is called *knowledge domain* and we want to avoid that. I'd much rather hire a new engineer and have them spend time learning the business logic of an application than spending time learning the idiosyncrasies of our libraries. Being said, we will not implement that feature and allow it to be extended by another engineer if the off chance it ever shows up.
* Look to see if a certain item type exists in the bag (e.g. a particular Dog instance)


### Bag Methods
* `public int getCurrentSize()`
  * Get the number of items in the bag
* `public boolean isEmpty()`
  * Is the bag empty?
* `public boolean add(<T> item)`
  * Add an generic type to the bag
  * More on generics later!
* `public <T> remove()`
  * Removes a item from the bag
* `public boolean remove(<T> item)`
  * Removes a given item from the bag if it can
* `public void clear()`
  * Empties the bag
* `public boolean contains(<T> item)`
  * Did we put this instance in the bag already?
* `public T[] toArray()`
  * Order our bag and return it as an array

### As an interface or class?
* Is it better to build this as an interface or an abstract class?
  * Remember the triangle issue of inheritance? It happens when I have a parent class, two sub classes, and a further sub-class than needs to parent the two other sub-classes.
  * What happens when I want a bag that uses arrays to hold our collection?
  * What happens when I want a bag that uses Hashes to hold our collection?
  * Remember, the idea behind data structures is how we attack our problem! Because this makes the Bag pretty abstract, so it sounds like an abstract class may suffice. But then we may face the triangle issue. So how about we make it an interface?

* As an interface:

```java
public interface BagInterface<T> {
  public int getCurrentSize();
  public boolean isEmpty();
  public boolean add(T item);
  public T remove();
  public boolean remove(T item);
  public void clear();
  public boolean contains(T item);
  public T[] toArray();
}
```

### Generics
* In the interface above, if we didn't use the generic syntax we would either have to specify a class type for *T*
  * What are the issues with specifying a type? Would we not be required to create a new Bag class for each type we wanted to bag? That would scale terribly.
  * What if we just went to the root *Object* instead? We could now add any type we wanted. Remember if I created a SodaBottle class, it will eventually inherit Object! Because of this, I could put any type I wanted in. But this causes an issue. What if I added a class instance of Integer and a class instance of SodaBottle? If you were expecting SodaBottle, we would have to do all kinds of type checking. This would be bloated and not scalable and possibly a security risk.
* To compromise the issues above, we can "truncate" all parent classes at the passed type. This means if we passed Bottle as the type, we can use SodaBottle or if we implemented it, BeerBottle. Alternatively, if we only wanted SodaBottles, we could pass it SodaBottles.
* Implementation of a [Generic Class and Generic Interface](../../examples/generics)

### ArrayBag
* We will implement the bag interface using an array an the underlying container

```java
public final class ArrayBag<T> implements BagInterface<T> {

  public ArrayBag(){}

  public ArrayBag(int capacity){}

  public int getCurrentSize(){}

  public boolean isEmpty(){}

  public boolean add(T item){}

  public T remove(){}

  public void clear(){}

  public boolean contains(T item){}

  public T[] toArray(){}

}
```

* Next we need to create some private member variables

```java
// the bag itself
private T[] _bag;

// the count of items in the bag
private int _countOfItems;

// the default capacity - for the overloaded constructor
private static final int DEFAULT_CAPACITY = 25;
```

* Create the constructors

```java
public ArrayBag(){
  this(DEFAULT_CAPACITY);
}

public ArrayBag(int capacity){

  // since the array will have null entries
  @SuppressWarnings("unchecked");

  T[] bag = (T[])new Object[capacity];

  this._bag = bag;

  this._countOfItems = 0;
}
```
* Implement `getCurrentSize()`

```java
public int getCurrentSize(){
  return this._countOfItems;
}
```

* Implement `isEmpty()`

```java
public int isEmpty(){
  return this._countOfItems == 0;
}
```

* Implement `add()`

```java
public boolean add(T item){

  if(!this._canIAddMore()){
    return false;
  }

  this._bag[this._countOfItems] = item;
  this._countOfItems++;
  return true;
}
```

* Implement `remove()`
  * Question: why cant we just decrement the counter and ignore setting the last element to null?

```java
public T remove(){

  if(this.isEmpty()){
    return null;
  }

  T last = this._bag[this._countOfItems];
  this._bag[this._countOfItems] = null;
  this._countOfItems--;

  return last;
}
```


* Implement `clear()`

```java
public void clear(){
  // DUPLICATE???? Make declarative then!
  T[] bag = (T[])new Object[capacity];
  this._bag = bag;
  this._countOfItems = 0;
}
```

* Implement `contains(T item)`

```java
public int contains(T item){

  if(this.isEmpty()){
    return -1;
  }

  int index = 0;
  while (index < _count && !item.equals(this._bag[index])){
    index++;
  }

  // if the index reached count, that means we never found the item
  if(index == _count){
    return -1;
  }
  return index;
}
}
```

* Implement `toArray()`
  * Question: What is better to return reference or clone? And why?

```java
public T[] toArray(){
  return this._bag.clone();
}
```
