# Counting Sort
Counting sort is very easy and not often used, but I intend to show you this algorithm because it gives a clear example of ***space vs speed*** in algorithm design.

## Basics
Counting sort uses space to overcome O(n<super>2</super>) efficiency found in most other sorting algorithms. To accomplish this, we are limited to sorting any types that represent whole numbers. We can also expand this into type `char` with slight modifications. I am sure we could even extend this to other custom data types if the logic follows how we accomplish this with the logic applied to whole numbers.

Steps to Counting Sort
1. Scan for largest number, `m`
2. Create array of size `m` of type int, named `counts`
3. Loop the unsorted array, and for each value found, increment a counter in the `counts` array for the value, `i`, found in the unsorted array.
4. Iterate the `counts` array, and for each discrete value in `counts` greater than 0, add the cell value of `i` to the unsorted array until the `counts` cell value if met, eg. `counts[0] = 4` and you will add `0,0,0,0` starting at cell 0 of the unsorted array.

## Time Complexity
* In step 1, you scan to length `n` - O(n)
* In step 3, you loop the unsorted array - O(n)
* In step 4, you loop the `count` array, but because you do no operations on cells with a count of 0, you will do operations on `n-p` cells, where `p` is the number of cells where the unique value has multiple instances in the unsorted array. The total sum of `p` across all cells will sum to a number such that in all you will do `n` operations. - O(n)

Because steps 1 and 2 are not nested, you have O(2n). While step 4 seems nested because you will iterate the `counts` array, you will only do `n` steps in the nested part, hence the top level iteration will be `n-p`, and the nested part will be `p`, so `n-p+p = n`, and hence O(n).

Finally, because step 4 is not nested with steps 1 or 3, you end up with O(3n) or just O(n).


## Visual Example

||a[0]|a[1]|a[2]|a[3]|a[4]
|:---|:--:|:--:|:--:|:--:|:--:|:--:|
| Unsorted Array  | **6** | **3**  | **4** | **2**  | **6**  |

Scan for largest
  * Note that if we decided to go smaller than 0, we need to also scan for smallest

Create Counting Array

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|a[6]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
| Initial Array  | 0 | 0  | 0 | 0 | 0 | 0 | 0 |

Now count each unsorted discrete value

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|a[6]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
| Initial Array  | 0 | 0  | 0 | 0 | 0 | 0 | 0 |
| Count a[0] | 0 | 0  | 0 | 0 | 0 | 0 | 1 |
| Count a[1] | 0 | 0  | 0 | 1 | 0 | 0 | 1 |
| Count a[2] | 0 | 0  | 0 | 1 | 1 | 0 | 1 |
| Count a[3] | 0 | 0  | 1 | 1 | 1 | 0 | 1 |
| Count a[4] | 0 | 0  | 1 | 1 | 1 | 0 | 2 |

Final Counting Array

||a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|a[6]|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
| Count a[4] | 0 | 0  | 1 | 1 | 1 | 0 | 2 |

Now loop counting array, inserting in order

||a[0]|a[1]|a[2]|a[3]|a[4]|OP|
|:---|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
| Unsorted Array  | **6** | **3**  | **4** | **2**  | **6**  | - |
| counts[0] | **6** | **3**  | **4** | **2**  | **6**  | No Op |
| counts[1] | **6** | **3**  | **4** | **2**  | **6**  | No Op |
| counts[2] | 2 | **3**  | **4** | **2**  | **6**  | Insert |
| counts[3] | 2 | 3  | **4** | **2**  | **6**  | Insert |
| counts[4] | 2 | 3  | 4 | **2**  | **6**  | Insert |
| counts[5] | 2 | 3  | 4 | **2**  | **6**  | No Op |
| counts[6] | 2 | 3  | 4 | 6  | **6**  | Insert |
| counts[6] | 2 | 3  | 4 | 6  | 6  | Insert |

## Iterative Example
Typically I would give you an iterative example to turn into recursive, but since this is just O(n) and trivial, you will now implement Counting Sort.
