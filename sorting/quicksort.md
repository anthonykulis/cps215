# Quick Sort
Quick sort is another divide an conquer strategy. It divides the array into two pieces, but these two pieces may not be equal halves. Quick sort chooses one entry in the array as the `pivot` and sorts the entries so that -
  * The pivot is in the position that it will occupy in the sorted array
  * Entries in the array before the pivot are less than or equal to the pivot
  * Entries in the array after the pivot are greater than or equal to the pivot

This arrangement is called a `partition` of the array.

## Basics

Creating the partition divides the array into two pieces, which we can call *smaller* and *larger*. If the pivot is in the correct position, then we can divide the work onto smaller and larger, repeating the process.

```java
Algorithm quickSort(a, first, last){
  if(first < last){
    // choose a pivot
    // partition the array around the pivot
    quickSort(a, first, pivotIndex - 1);
    quickSort(a, pivotIndex + 1, last);
  }
}
```

## Time Complexity
* Creating the partition accounts for most of Quick Sorts work and occurs before any recursive calls occur. We shall see that partitioning will require no more than O(n) calls.
* The issue is that choosing a pivot is not always ideal, but if it was, it would choose a position that evenly divides the arrays. If this idea situation occurs, we have effectively created merge sort without the need for extra space and hence we would be at O(n log n).
  * The worst case is that the partition has one empty side. If that occurs, we will not divide our work but rather have to perform *n* operations on one side and 0 operations on the other, making quick sort O(n<superscript>2</superscript>).

## Creating the partition
Various strategies exist for creating a partition. In class, we will only cover a `random` strategy. You will be required for homework to research and implement a better strategy.


For this in-class work, I will provide the random pivot strategy in the `ArraySorter.java` class. It will return a random int within the bounds, exclusively. This means it will avoid the worst case, but it is naive and many better solutions exist.

```java
// provides a random index within the bounds, exclusively
private static int getRandomIndex(int from, int to){
  return (int)(Math.random() * (to - 1) + from + 1);
}
```

## Visual Example

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|
| Initial Array  | **15** | **8**  | **10** | **2**  | **5**  | **1** |


Select a random pivot (bold)

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
| Pivot chosen  | 15 | **8** | 10 | 2  | 5 | 1 |


Swap it with the last entry

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
| Pivot chosen  | 15 | 1 | 10 | 2  | 5 | **8** |

Starting at the beginning of the array, look for the first entry that is greater or equal to the pivot.

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|
| Find first >= to pivot  | -> 15 | 1 | 10 | 2  | 5 | **8** |

Now that we found it, reverse the logic, starting at the next to last entry in the array (left of the pivot), look for the first entry less than or equal to the pivot.

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|
| Find last <= pivot  | -> 15 | 1 | 10 | 2  | 5 <- | **8** |

If the `fromLeft` index is less than the `fromRight` index, swap the two entries.

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|
| Swap  | 5 <- | 1 | 10 | 2  | -> 15 | **8** |

Continue with the next set finding greater than or equal to pivot

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|
| Find next >= pivot | 5  | 1 | -> 10 | 2  | 15 | **8** |

Continue with the next set finding less than or equal to pivot

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|
| Find next <= pivot | 5  | 1 | -> 10 | 2 <- | 15 | **8** |

Since `fromLeft` index is less than the `fromRight` index, swap the two entries

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
| Swap | 5  | 1 | 2 <- | -> 10  | 15 | **8** |

Continue to end of array partition. We find in our example there are no more swaps. We encounter an entries `fromLeft` that is past the pivot position. So we stop and wait. We continue `fromRight` until we find one. If we did find one, we could not swap and would exit the partition loop. In our case we ran out of array, so we would stop.

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|
| Find next >= pivot | 5  | 1 | 2  | -> 10  | 15  | **8**|
| Find next <= pivot | <- 5  | 1 | 2  | -> 10  | 15  | **8**|

Now that we cannot continue because of lack of array, or if we found a greater than index whose `fromLeft` value is greater than `fromRight`, swap the pivot and the entry in `a[fromLeft]`


||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|
| Swap pivot with a[fromLeft] | 5  | 1 | 2  | **8** | 15  | 10 |

Now we would divide and repeat. For brevity, I will not detail every step.

||a[0]||a[1]||a[2]|a[3]|a[4]|a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:---|:--:|:--:|:--:|:--:|:--:|
| Split among the pivot index | 5  | 1 | 2  || 8 || 15  | 10 |
| Randomly select left pivot | 5  | **1** | 2  || 8 || 15  | 10 |
| Randomly select right pivot |  5  | **1** | 2  || 8 || 15  | **10** |
| Swap pivots to end | 5  | 2 | **1**  || 8 || 15  | **10** |
| For left, stop at first >= | ->5  | 2 | **1**  || 8 || 15  | **10** |
| For left, stop at first <= |<- ->5  | 2 | **1**  || 8 || 15  | **10** |
| For left, cannot continue, swap | 1  | 2 | 5  || 8 || 15  | **10** |
| For right, stop at first >=, swap | 1  | 2 | 5  || 8 || -> 15  | **10** |
| For right, stop at first <=, swap | 1  | 2 | 5  || 8 ||<- -> 15  | **10** |
| For right, cannot continue, swap | 1  | 2 | 5  || 8 || 10 | 15 |

...

|||a[0]||a[1]||a[2]||a[3]||a[4]||a[5]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:---|:--:|:--:|:--:|:--:|:--:|
| Partition again || 1  || 2 || 5  || 8 || 10 || 15 |

## Iterative Solution
The iterative solution would only consist of swapping values among the pivot point, otherwise the recursion divide would be exhausting.

## Recursive Solution
The recursive solution should cover the division, hence, this algorithm, like merge sort, should be hybrid recursion and iterative.

## Caveat
You do not want to quick sort below a minimum size. When implementing quick sort, check to see if the distance between `last` and `first` in quickSort is less than a constant MIN_SIZE. If it is, call insertionSort, otherwise use quickSort. The minimum size will be determined by your partition strategy, but since insertionSort is excellent for arrays under the size of 10, set your MIN_SIZE accordingly.

This means your quickSort method will be -

```java
Algorithm quickSort(a, first, last){
  if(last - first + 1 < MIN_SIZE){
    // do insertion sort
  else {
    // choose a pivot
    // partition the array around the pivot
    quickSort(a, first, pivotIndex - 1);
    quickSort(a, pivotIndex + 1, last);
  }
}
```
