# Selection Sort
This is a very easy algorithm to understand and hence is why students typically learn this one first.

## Basics
With position, `i = 0`, and `n` equal to array length

1. Starting as position `i`, scan for smallest value from `i + 1` to `n`.
2. Swap the position `i` with the position of the smallest value found.
3. Increment `i` by one
4. Repeat steps 1 to 3 until you reach `n - 1`

## Time Complexity
* In step 1, you scan the length for a complexity of `n`
* In step 3, you increment `i` until `n-1` for a complexity of `n-1`
* Because this is nested, you get `n * (n-1)` or O(n<sup>2</sup>)

## Visual Example
||a[0]|a[1]|a[2]|a[3]|a[4]|
|:---|:--:|:--:|:--:|:--:|:--:|
| Initial Array  | **15** | **8**  | **10** | **2**  | **5**  |
| Step 1 - starting at a[0], scan for low value | 15 | 8 | 10 | -> 2 | 6
| Step 2 - 15 swapped with 2  | 2  | 8  | 10 | 15 | 5  |
| Step 3 - starting at a[1], scan for low value | 2 | 8 | 10 | 15 | -> 5 |
| Step 3 - 8 swapped with 5 | 2 | 5 | 10 | 15 | 8 |
| Step 4 - Starting at a[2], scan for low value | 2 | 5 | 10 | 15 | -> 8 |
| Step 5 - 10 swapped with 8 | 2 | 5 | 8 | 15 | 10 |
| Step 6 - starting at a[3], scan for low value | 2 | 5 | 8 | 15 | -> 10 |
| Step 7 - 15 swapped with 10 | 2 | 5 | 8 | 10 | 15 |
| Sorted Array | **2** | **5** | **8** | **10** | **15** |

## Iterative Example

```java
public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int n){
  for(int current = 0; current < n; current++){
    int smallest = findIndexOfSmallest(unsorted, current, n - 1);
    swap(unsorted, current, smallest);
  }
}

private static <T extends Comparable<? super T>> int findIndexOfSmallest(T[] a, int start, int end){
  T min = a[start];
  int minIndex = start;
  for(int i = start + 1; i <= end; i++){
    if(a[i].compareTo(min) < 0){
      min = a[i];
      minIndex = i;
    }
  }
  return minIndex;
}

private static void swap(Object[] a, int i, int j){
  Object t = a[i];
  a[i] = a[j];
  a[j] = t;
}
```
