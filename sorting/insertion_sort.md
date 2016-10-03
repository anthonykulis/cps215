# Insertion Sort
Similar to [Selection Sort](selection_sort.md), a very intuitive algorithm.

## Basics
With position, `i = 0`, offset `m = 1`, and `n` equal to array length.
1. Starting at position `i`, compare `i + m`, for a smaller value.
2. Continue incrementing `m` until a smaller value is found.
3. Once found, remove the item at offset `m`, holding in a caching variable.
4. Iteratively, move every item starting at `m - 1` one position greater until you reach the position such that the cached value is larger than the next item to shift.
5. Insert the cached item into found position
6. Increment `i` and repeat with step 1 until sorted.

## Time Complexity
* In steps 1 and 2, you scan from `i + 1` until `n` looking for the first smaller value for a worst case of `n - i`, where in the first iteration, `i` is equal to 0, so, `n - 0 = n`.
* In step 4, you shift values one position greater until the insertion spot is found. This means in the worst case the last position is the first smaller value, and hence have to shift `n - 1` times.
* Because this is nested, you get `n * (n - 1)` or O(n<sup>2</sup>)

## Visual Example
||a[0]|a[1]|a[2]|a[3]|a[4]|
|:---|:--:|:--:|:--:|:--:|:--:|
| Initial Array  | **15** | **8**  | **10** | **2**  | **5**  |
| Step 1 - starting at a[0], scan for first lower value | 15 | -> 8 | 10 | 2 | 6
| Step 2 - cache value  | 15  |   | 10 | 2 | 5  |
| Step 3 - shift first value |  | 15 | 10 | 2 | 5 |
| Step 4 - insert cached value | 8 | 15 | 10 | 2 | 5 |
| Step 5 - starting at a[1], scan for first lower value | 8 | 15 | -> 10 | 2 | 5 |
| Step 6 - cache value | 8 | 15 | | 2 | 5 |
| Step 7 - shift second value | 8 | | 15 | 2 | 5 |
| Step 8 - insert cached value | 8 | 10 | 15 | 2 | 5 |
| Step 9 - starting at a[2], scan for first lower value | 8 | 10 | 15 | -> 2| 5 |
| Step 10 - cache value | 8 | 10 | 15 | | 5 |
| Step 11 - shift third value | 8 | 10 | | 15 | 5 |
| Step 12 - shift second value | 8 | | 10 | 15 | 5 |
| Step 13 - shift first value | | 8 | 10 | 15 | 5 |
| Step 14 - insert cached value | 2 | 8 | 10 | 15 | 5 |
| Step 15 - starting at a[3], scan for first lower value | 2 | 8 | 10 | 15 | -> 5 |
| Step 16 - cache value | 2 | 8 | 10 | 15 |  |
| Step 17 - shift fourth value | 2 | 8 | 10 | | 15 |
| Step 18 - shift third value | 2 | 8 | | 10 | 15 |
| Step 19 - shift second value | 2 | | 8 | 10 | 15 |
| Step 20 - insert cached value | 2 | 5 | 8 | 10 | 15 |
| Sorted Array | **2** | **5** | **8** | **10** | **15** |

## Iterative Example

```java
public static <T extends Comparable<?super T>> void insertionSort(T[] unsorted, int n){
  int found = -1;
  for(int i = 0; i < n; i++){
    found = findIndexOfNextSmallerItem(unsorted, i);
    if(found > i){
      insertInOrder(unsorted, found);
    }
  }
}

private static <T extends Comparable<? super T>> int findIndexOfNextSmallerItem(T[] a, int start){
  T comparable = a[start++];
  while(start < a.length && a[start].compareTo(comparable) > 0) start++;
  return start != a.length ? start : -1;
}

private static <T extends Comparable<? super T>> void insertInOrder(T[] a, int s){
  T cached = a[s--];
  while(s > -1 && a[s].compareTo(cached) > 0) a[s + 1] = a[s--];
  a[++s] = cached;
}

```
