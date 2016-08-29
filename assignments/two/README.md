# Assignment 2

## Improve our stack
* Right now we can only add and remove one element at a time.
* If I had a stack of newspapers and added more, I dont have to add them one at a time. I can add a bunch newspapers at once, so we should be able to do the same.
* Conversely, if I had a stack of newspapers I can grab as many as I can carry from the top. We shall do the same with our stack.


### Add *n* items
* We should overload `push` to accept an array of type `<T>`

### Pop *n* items
* We should overload `pop` to accept *n* of type `int` to then pop and return and array of *n* length

### Hints
* Be declarative!
* Be sure to not pop more than we have. If I go to grab 20 newspapers and there is only 19, it doesn't affect me. So be graceful when designing this.

## Pass/Fail
As usual, this is pass/fail. 25 points will be awarded when passed. 0 if it fails. To pass, you must have compilable code that runs correctly. Your unit tests will determine if they do. Unit tests are mandatory like they will be on all assignments.

## Due Date
The due date will be Friday September 9, 2016 at midnight. To be eligible for the assignment extra credit you must meet this deadline.

### Extra Credit
If you submit perfect code that doesn't need to be refactored and it runs correctly, you will automatically receive the 5pts refactoring extra credit. If you submit code that works properly and needs some refactoring, I will comment those parts, return it to you, and you will have another week (Friday at midnight) to complete the refactoring.

### Submitting Code
Use D2L to submit your source files. Please submit them as just java files, not zip files. If there is issue submitting, a Github commit to your repository or email the source to me is fine. Please notify me via email if there is issue.
