# Sorting

## Objectives
After this chapter the student is responsible for the skills to:

  1. Sort an array in ascending order using either selection sort, insertion sort, counting sort
  2. Sort a chain of linked nodes into ascending order using insertion sort
  3. Assess the efficiency of a sort

## Basics
Sorting allows us to order objects or primitives in some order. This order can be anything relevant. Examples include a persons height, age, name; music by title, artist, release date, or popularity.

Comparing two objects depends on the nature of the objects. Sorting should be natural unless otherwise specified. Natural sorting refers to a logical sorting that a human can consume. Although rare, there may be circumstances where sorting may not be natural. An example may be sorting by address references, time in queue, etc.

All items you will sort should belong to some sort of collection. There stands an argument that sorting an array is easier than sorting objects referenced by nodes. What is important irregardless of the argument, is that if you are tasked with building a hybrid data-structure, that you use the proper tools for the job. In some sorting algorithms, using nodes can be an efficiency killer, so it is imperative to understand the steps to each sorting algorithm before you tackle the data set to be sorted.

## Organizing Java Methods That Sort an Array
Since many of these algorithms re-use various steps, placing our sorting algorithms into a single class and calling static methods for the sort type is logical.

Example:
`public static <T> void exampleSort(T[] a, int n)`

For the above `exampleSort`, the array `T[] a` contains our items to sort, and `int n` is the amount we wan to sort.

If an array of objects is to be sortable, we must further define our method with comparable.

Example:
`public static <T extends Comparable<T>> void exampleSort(T[] a, int n)`

To provide a lower bound, we will do the following.

Example
`public static <T extends Comparable<? super T>> void exampleSort(T[], int a)`

So from here on out, we will work in one class for sorting arrays and it will resemble the following signature. Of course, its name and parameters should fit accordingly.

```java
public class ArraySorter {
  public static <T extends Comparable<? super T>> void exampleSort(T[], int a){}
}
```

Finally, for our algorithms, we will use the array passed for sorting. While it may provide an optimal speed solution for sorting, we will ignore this case for the purpose of learning.
