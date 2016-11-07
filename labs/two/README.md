# Lab Two
**OrderedHashList**

This lab will exercise your understanding of sorting, linked lists, and the ArrayList class from java container framework.

## Concept
You will create a list that is ordered for searching and has constant time look up. It would be wise to simply extend `LinkedList` into your new class.

## Rules

1. You may only use data structures and algorithms you created. This means the only class you may import to use data structures is `sorting.ArraySorter` or an extension of `sorting.ArraySorter` if you so choose. You may import any other class as needed as long as it not a data structure provided by java. Sorting must be done via your `ArraySorter` class. There is **one** exception to this import rule, you are allowed to use `java.util.ArrayList` to manage your hash keys. You  are only allowed to use this for managing your keys and only allowed to use the `add` and `remove` methods. Any other use will result in a 0 grade for the lab.


2. I should be able add at `O(1)` efficiency with a key of my choosing restricted to an upper bound of type `String` but it should map to any type of value. The user will not be able to add at index or add to the back of the list. You will need to extend `LinkedList` and overwrite those methods to throw exceptions if called. Any exception is acceptable for this lab.

  * Note: since you will be collecting the keys in an ArrayList the ArrayList may have reached it's current maximum size. For the purpose of this labs requirements, that resize will not count against the `O(1)` rule.
  * Tip: using `ArrayList::add(T item)` will not allow you to access it later at `O(1)`.

3. I should be able to remove an item by key or remov from the front of the list at speeds of `O(1)`. Follow the logic in rule #2 for handling removing by index.
  * Note: removing at `O(1)` also includes removing from the collection of keys. This means you will not be able to use `remove(T item)` method and get full credit. You will have to access the ArrayList item logically. You should assume that `ArrayList::remove(T item)` does not operator at `O(1)` time.

4. I should be able to request the set of keys that map **every** item. This method should return an array of **ordered** keys. You will need to use your `ArraySorter` class to handle this. This should cost no more then `O(n log n)` in the worst case on large arrays and `O(n<sup>2</sup>)` on small arrays.

5. I should be able to request an iterator that iterates in sorted ascending order. Follow the logic in #4 for this iterator.

6. The search space for keys will be restricted to a size of 2048. This means you will have collisions and they have to be handled. To handle these, if not already handled before, you will add the passed item to a collision list indexed by the hash value. The "item" value of a new node will be a helper class. You will need a helper class to store the actual key the user supplied and the item passed, this way when a collision does occur, you can find the actual item passed. This also means that every item in our collision list is the helper class and the user passed `item` value is a member variable inside that helper class. This is not the optimal way, but for this class it exercises our usage of data structures. Furthermore, this helper class must be immutable! This means it has no setters/mutators, only getters/accessors. To set the values you will use a constructor. The signature for the constructor should be `public CollisionItem(String key, T item);`. Of course `CollisionItem` will need to be a generic class. You can then call your collision list as `new LinkedList<CollisionItem<T>>()`.


## Tips for attacking the problem.
### Generally Overall
What we are doing is combining the HashMap and the LinkedList. The purpose is to overcome the `O(n)` search time in the list while bringing order to a HashMap who has no order but has `O(1)` access time. We must keep both of those benefits.

Since, for this lab only, we are restricting the search space to 2048 keys, our set of hash keys will never grow more than 2048 in size. So you can create a constant that sets the size of the keys structure to 2048.

This does not mean we can only have 2048 items though, there is no size restriction to the user. The collision lists can grow to as big as the computer can handle.  Those collision lists will be of type `CollisionItem<T>`. So hopefully it is obvious we are taking up extra space for the benefits of fast adding and fast recovery, yet maintaining an order to our hashmap.

### Adding
On add, you will compute the hash value of the key, access the keys structure, create a `CollisionItem` properly, and add to the list.

Since we will never add a set of items at once, you can take advantage of this by accumulating keys as they add. Since we need to return an ordered set of keys, this also means the item's key we add **must** have an upper bound of `Comparable`, which is covered since we restrict an upper bound of type `String` that already has an upper bound of `Comparable`.

If you accumulate the keys on add, then when returning keys you can call sort on the array of keys. Keep in mind though, if you accumulate keys on add, you will have to remove that key on the remove method.

### Removing
On remove, you will still to maintain a `O(1)` remove. Since you will have to purge the array of keys of the removed key, you will have to think about how to handle this. See rules #2 and #3 for details.

## Submission Requirements
Submit a package I can run without modifying. Also submit a README in this package that provides a command to run your code. I should be able to change directory into your unzipped package and execute that code, eg `java YourProgramName`. I will not consider code that does not run for grade. This means your submission should be zipped at the root and any other classes supporting your code needs to be included properly.

I purposely have not put up full submissions of working code of previous assignments. This is because many people have not submitted their previous assignments. When you submit your supporting code, if you have not already submitted it prior to this, I will include your supporting code as gradable for previously missing work. So if your missing an assignment in say `ArraySorter`, and that is in your submission code, I will be graded as the missing assignment.

## Grading
This will be out of 100 points and be graded with partial credit. There will be no late submissions. There will be no extra credit. You are expected to submit your own driver but work as a team.

Things that will get you a 0 with no exceptions:
  * Code that doesn't compile.
  * Code that doesn't follow the prescribed rules.
  * Turning code in late.

Don't get a 0.

As stated in the submission requirements, any missing previous assignments included in your supporting code will be used as submissions for those previous assignments.

## Due Date
This will be due the last day of classes, December 9, 2016, at midnight. There will be no late sumbissions.
