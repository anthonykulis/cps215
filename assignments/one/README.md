# Assignment 1

## Improve our ArrayBag
The ArrayBag is limited to a default capacity of 25 or whatever the user passes in the constructor. We shall allow the bag to grow up to a limit of 1000 automatically. It also has room for improvement such that when we call `remove(T item)`, we remove that item setting the underlying array's cell to null. This means we can have an ArrayBag with 100 elements in it, remove all of them, then add another item starting at cell 100. This is not ideal. We need to improve that as well.

### Auto Increasing Size
We cannot let our bag just keep growing and growing because of the finite amount of room on the computer. Because of this we will limit our max size to 1000 elements. Further more, the user should never have to allocate space. This means they only should call one constructor and as the bag fills up we allocate more space automatically.

We will need a method `_allocateMoreSpace()` which will add the `DEFAULT_CAPACITY` more null elements to the array. We can do this by creating another array of the new size and performing an array copy from the smaller to the larger. The API for `System.arraycopy()` can be found on the Java 7 [API](http://docs.oracle.com/javase/7/docs/api/java/lang/System.html#arraycopy%28java.lang.Object,%20int,%20java.lang.Object,%20int,%20int%29).

### Remove the null on remove
When we remove an item requested by the user we leave a null cell in the array. We need to get rid of this. The easiest way to handle this to use `System.arraycopy()` twice. Once for the left side of the null and once for the right side of null.

### Pass/Fail
This will be pass/fail. 25 points if you pass. To pass, you must have compilable code that runs correctly. Your unit tests will determine if they do. Unit tests are mandatory like they will be on all assignments.

### Due Date
The due date will be Friday Sept 2, 2016 at midnight. To be eligible for the assignment extra credit you must meet this deadline.

### Extra Credit
If you submit perfect code that doesn't need to be refactored and it runs correctly, you will automatically receive the 5pts refactoring extra credit. If you submit code that works properly and needs some refactoring, I will comment those parts, return it to you, and you will have another week (Friday at midnight) to complete the refactoring.

### Submitting Code
Use D2L to submit your source files. Please submit them as just java files, not zip files. If there is issue submitting, a Github commit to your repository or email the source to me is fine. Please notify me via email if there is issue.
