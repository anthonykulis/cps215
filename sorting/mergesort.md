# Merge Sort
Merge Sort is the go to sorting algorithm, but not always the best. It's "downside" is the additional space it requires, and while it is O(n log n) at worst, modern computational costs prohibit it the from being the best over Quick Sort which is faster in most cases, but O(n<superscript>2</superscript>) in worst cases. In fact, I am 99% sure Java uses Merge Sort for it's default sort, while others use quick sort.

Divide and Conquer is what drives this algorithm. While I a mentioned divide and conquer when introducing recursion, this is the first algorithm you will see that truly leverages it.

## Basics
As we recurse downwards, we pass smaller and smaller slices of the array until we get to an array of size 1. On the "upward" path, we recombine the arrays using merges to sort.

### Merge
First, let us imagine the merge portion on 2 sorted arrays. Imagine these 2 arrays.

|a[0]|a[1]|a[2]| |b[0]|b[1]|b[2]|
|:----:|:----:|:---:|:---:|:---:|:---:|:--:|
|2|3|7||1|5|6|

We will need a second array to merge into

|c[0]|c[1]|c[2]|c[3]|c[4]|c[5]|c[6]|
|:---:|:---:|:---:|:---:|:---:|:---:|
|||||||||

1. From indexes, `i`, and `j` starting at 0, compare `a[i]` to `b[j]`.
2. While `a[i]` is smaller than `b[j]`, put into `c[k]`.
3. Once `a[i]` is larger than `b[j]`, iterate the array `b`, emulating step 2, until `b[j]` is smaller than `a[i]`
4. Stop when you reach the end of one array, copying the remaining array, if any, into the output array.  

### Divide and Conquer
We will split this into two parts, the Divide and the Conquer

#### Divide
Imagine a small, unsorted array.

|a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|a[6]|a[7]|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|23|1|4|16|8|5|3|2|

Divide is really easy. We will recursively break this down into arrays of size 1. We actually do not create new arrays, but segment one array by indices. For the example though, I will use discrete arrays.

|a[0]|a[1]|a[2]|a[3]||b[0]|b[1]|b[2]|b[3]|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|23|1|4|16||8|5|3|2|

|a[0]|a[1]||b[0]|b[1]||c[0]|c[1]||d[0]|d[1]|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|23|1||4|16||8|5||3|2|

|a[0]||b[0]||c[0]||d[0]||e[0]||f[0]||g[0]||h[0]|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|23||1||4||16||8||5||3||2|

#### Conquer
Now, using our example from the beginning, we can repeatedly call `merge` to merge the smaller arrays into bigger ones.

Starting with where we left off in the Divide.

|a[0]||b[0]||c[0]||d[0]||e[0]||f[0]||g[0]||h[0]|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|23||1||4||16||8||5||3||2|

|a[0]|a[1]||b[0]|b[1]||c[0]|c[1]||d[0]|d[1]|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|1|23||4|16||5|8||2|3|

|a[0]|a[1]||b[0]|b[1]||c[0]|c[1]||d[0]|d[1]|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|1|4|16|23||2|3|5|8||

|a[0]|a[1]|a[2]|a[3]|a[4]|a[5]|a[6]|a[7]|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|1|2|3|4|5|8|16|23|


## Time Complexity

1. Each call to merge is clearly O(n). We can tell this because we will iterate the length of 2 arrays, but when combine will be of `n` length, so if evenly split, each increment that occurs is `n/2`.

2. The dividing of arrays is the "tricky" part. Assuming we start with an array that is a size of the power of two, eg `2<superscript>3</superscript>` as in our example, we have `n = 2<superscript>k</superscript>`, and if k is the number we need reach our array size, we have levels of recursive calls.

Since we need to compare how many times merge is called, we can tell it will be called `k` times, this means `k = log<subscript>2</subscript> n`, and hence for each `k`, we have O(n), this means we have `n * k` steps, but since `k = log n`, we can replace `k` with `log n` and we have O(n log n).

While this holds for `2<superscript>n</superscript>` array lengths, we could extrapolate this out to arrays that do not follow the easy math. If we were to imagine an array of length 15, this means `n = 15`, and `k` would be 4. If we imagined a `k = 3`, we would only be able to "complete" 8 steps, but with it equal to 4, we can "complete" 16 steps.

This means that `k - 1 < n < k`, and hence, Merge Sort will always be O(n log n).

## Iterative Solution
Iterative Merge Sort is **not** easy, so we will favor the recursive solution instead.

## Recursive Solution
