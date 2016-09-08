# Lab One

## Concept
Use queues to prioritize the inputs read from a file as jobs. The Job will either be named "a", "b", or "c". It will have a work load associated with the job. This work load is how long it takes for the job to complete. Once the job completes, you will print out the job via the `toString()` method, indicating it is complete.

You will have three queues, one for each job title. You will complete the highest priority queue first. If that queue empties, you may move one job from the 2nd highest priority queue into the highest queue.

Alternatively, if the 2nd highest priority queue is empty, you may move an item from the lowest priority queue into the 2nd highest priority queue. You will continue this until the queues are empty.

To complicate things, if an item has been sitting in the front of the queue too long, you may move that item into a higher level priority queue.

Finally, to keep it very interesting, jobs will not come all at once. As they do come in, you must prioritize them into the work queues.

## Rules
I will provide the file structure to run your program. You **must** follow that file structure. Your driver is already named, packaged, and imports the **only** custom made classes you may use. Of course, if you need to import Java SDK classes, you may, but they may not be from the JCF.

## Jobs Description
I provide a class named `Job`. This class has a signature of the following (see source below). You obviously do not need to write it. You do not even have to instantiate it. I will do that for you. This will be your job item that you use in your priority queue.

You will need to use the methods `getName()`, `doWork()`, and `getTimeLeft()` to decide where to put your job, to decrement its time left until done, and to find out how much time is left until it is done, respectively. Hint - you will use the `NodeQueue`'s `peek` method.

```java
package labs.one.util;

public class Job {
  public Job(String name, int timeToComplete);
  public String getName();
  public boolean isDone();
  public void doWork();
  public String toString();
}
```

## Queue Priorities
You will have 3 queues you have to manage, a *high*, *medium*, and *low* priority. Jobs with the name `a` will always come into the system and be placed into the highest priority queue. Jobs with the name `b` will come into the system and always initially be placed in the medium priority queue. And finally, jobs with the name `c` will come into the system and be initially placed into lowest priority queue.

**ONLY THE HIGHEST PRIORITY QUEUE MAY DO WORK**

### Promoting the job in the queue
* Highest priority jobs will never be promoted.
* Medium priority jobs will be promoted to high priority jobs after 3 ticks of the system or if the highest priority queue is empty.
* Lowest priority jobs will be promoted to medium priority jobs after 5 ticks of the system or if the medium priority queue is empty.

Note: This means if a job with the name `c` comes in and all 3 queues are empty and remains empty (excluding this job), it will take 1 tick to be promoted to medium, and then 1 tick to be promoted to high.

Note: If that same job came in and there was already a job in medium that is taking more than 5 ticks, that low priority job will remain in low priority until 5 ticks have completed and be moved into the medium priority queue on the sixth tick.

Finally, you **only** promote jobs from lower to higher priority queues **only after** the incoming jobs have been scheduled.

## Receiving Jobs
I provide a `JobReader` class to read the next ***GROUP*** of jobs. This may be 1 job, it may be 2 jobs, it may be 1 million jobs. It will come in the form of a queue holding type `Job` and you will have to shift out each of those jobs and put them in their appropriate queue. For simplicity sake, you will always have a group of jobs coming in a steady stream. This means that it will never give a group of jobs, then no jobs, then jobs again. Once the jobs stop, you can stop reading from the JobReader class.


```java
package labs.one.util;
public class JobReader
  public JobReader(String fileName) throws FileNotFoundException;
  public NodeQueue getNextGroupOfJobs() throws IOException;
}
```
Realize that this class will require to handle exceptions. The `JobReader` constructor will queue you up for reading from the file. The file is at `labs/one/data/Jobs.txt`. Do not change nor move that file. You may edit it as you see fit. The format of that file is explicit. It is in the form of `[job:time],[job:time],...,[job:time]`. Each row of that file is a new set of jobs coming in. Do not code for that file!!! I will test your code on a completely different set of jobs.

Use `getNextGroupOfJobs()` to read the next line. It will return a queue type `NodeQueue` with generic type `Job` as its data in each node.

## The driver class
The driver is named `BasicScheduler` and is packaged at `labs.one`. You **must** use this driver and **must** use this package.

## Tips for attacking the lab
* This obviously requires a loop to continue until no jobs are left in any three queues.
* Each tick will start with reading a new group of jobs.
* Once that new group of jobs has been prioritized, you will check to see if anyone needs to be promoted.
* Be declarative. You can (and should) write methods in your driver to help out.

## Grading
This will be out of 100 points and be graded with partial credit. There will be no late submissions. There will be no extra credit. You are expected to submit your own driver but work as a team.

Things that will get you a 0 with no exceptions:
  * Code that doesn't compile.
  * Code that doesn't follow the prescribed file structure.
  * A driver that isn't the one I provided.
  * Data structures you imported that I didn't provide in the import.
  * Changing the library of classes I provided.
  * Turning code in late.

Don't get a 0.

## Due Date
You will have almost 1 month to complete this lab. This means it is due on Oct 6, 2016 at 1pm. This is so we can review it in class.
