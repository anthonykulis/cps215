# Assignment 1

## Improve our ArrayBag
The ArrayBag is limited to a default capacity of 25 or whatever the user passes in the constructor. We shall allow the bag to grow up to a limit of 1000 automatically. It also has room for improvement such that when we call `remove(T item)`, we remove that item setting the underlying array's cell to null. This means we can have an ArrayBag with 100 elements in it, remove all of them, then add another item starting at cell 100. This is not ideal. We need to improve that as well.

### Auto Increasing Size
We cannot let our bag just keep growing and growing because of the finite amount of room on the computer. Because of this we will limit our max size to 1000 elements. Further more, the user should never have to allocate space. This means they only should call one constructor and as the bag fills up we allocate more space automatically.

We will need a method `_allocateMoreSpace()` which will add the `DEFAULT_CAPACITY` more null elements to the array. We can do this by creating another array of the new size and performing an array copy from the smaller to the larger. The API for `System.arraycopy()` can be found on the Java 7 [API](http://docs.oracle.com/javase/7/docs/api/java/lang/System.html#arraycopy%28java.lang.Object,%20int,%20java.lang.Object,%20int,%20int%29).

### Remove the null on remove
When we remove an item requested by the user we leave a null cell in the array. We need to get rid of this. The easiest way to handle this to use `System.arraycopy()` twice. Once for the left side of the null and once for the right side of null.
