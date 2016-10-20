# Radix Sort
Radix Sort leverages extra space for a fast sort dating back to 1887. It can sort equal length values at O(n) speeds. It can be perfomed in LSD or MSD styles. We will use LSD.
## Basics

Imagine an array of equal length values:


`{134, 001, 342, 544, 034}`

We need create 10 buckets of length 5

```
0: []
1: []
...
...
9: []
```

1. From the Least Significant Digit (LSD), sort into buckets

```
0: empty
1: [001]
2: [342]
3: empty
4: [134, 544, 034]
5 - 9: empty
```

2. Now replace them sorted

`{001, 342, 134, 544, 034}`

3. Moving one position left

```
0: [001]
1: empty
2: empty
3: [134, 034]
4: [342, 544]
5 - 9: empty
```

4. Now replace them again
`{001, 134, 034, 342, 544}`

5. Again, move one left of last position

```
0: [001, 034]
1: [134]
2: empty
3: [342]
4: [544]
5 - 9: empty
```

6. Finally, when done with Most Significant Digit, you will be sorted. So replace.

`{001, 034, 134, 342, 544}`

## Time Complexity

* We will traverse the list of values at least once for a length of `n`.
* If the length of the values is greater than 1, then we will traverse the list `m` times.
* This gives us `O(mn)`, but if `m << n`, we can see this as `O(n)`


## Iterative Solution
* This will be [assignment 4](/assignments/four/readme.md)

## Recursive Solution
* This will be the extra credit of the assignment.