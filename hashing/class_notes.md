# Hashing
Hashing is a technique to compress one namespace `{a, b, c, ...}` into a search space `{1,2,3,...}`


## Name Space
Let us define what a name space is. In computing, a `name space` is a way to organize a set of symbols. For our discussion, a namespace will be a set of data. This could be every word in the dictionary, any phrase of words possible, or a set of instantiated objects. It just needs to be a set of similar items of any size.

## Search Space
Because this is a data structures class, we are attempting to do one of three things:

1. organize data
2. sort data.
3. find data.

Our search space will be part of finding data.

So far in your academics when it comes to putting data into an array you have learned it one way that is a variation of:

```java
String[] a = new String[SIZE];
for(int i = 0; i < a.length; i++){
  a[i] = "Some string value";
}
```

I ask a simple question: Why do we have to put it into the array in order of `i`? This means when we want to retrieve this data, we need to use `i` to get it back. This is useless in cases where we know we will put the data much less often than we will access it. To access data, as is, we will need `O(n)`.

What if we could access the data in `O(1)`? How can we do this? Well, this is where the `search space` comes in. Imagine:

  Name space: {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}
  Search space: {0, 1, 2, 3, 4, 5, 6, 7}

In the simplest sense, we could just put every item into an array ordered:

  [a, b, c, d, e, f, g, h]

And then simply realize we need to access each item in the name space by a simple method that if we ask for `val(a)` we get back a 0. Now we could simply access our data with `someArray[val(a)]` and so on.

The result of `val(a)` to `val(h)` should always give us a value of 0 to 7. This is our search space. We can find our name space item with the value of the search space.

With this logic we could simply create a method that subtracts the characters unicode value from an `a` values unicode value and get the index:

```java
int val(char letter){
  return int(letter - 'a');
}
```

Now whenever we wanted to store any lower case letter, we would need an array of size 26 and simply do something like:

```java
a[val('g')] = 'g';
```

And then when we needed to recover it:

```java
letter = a[val('g')];
```

### Search Space - more complex

Ok, that character examples seems pretty obvious, right? But let's mix it up a little. Imagine:

  Name space: {"horse", "cat", "dog"}

Since our name space is a set of words, they have no real order. Sure we can do alphabetical, but how do we know what *index* a word is out of every possible word? Words change all the time. We would have to constantly maintain a look up table. That seems overwhelming.

So, our example using characters needs a redux.

## Computing Hashes
By now you have hopefully realized that a hash is simply a way to convert some value into a number that can be indexed in an array. The question that should remain is how?

Well, lets look at how Java does strings:

  u<sub>0</sub>g<sup>n-1</sup> + u<sub>1</sub>g<sup>n-2</sup> + ... + u<sub>n</sub>

Looks intimidating? Well, in code it is simply:

```java
String n = "anthony";
int hash = n.charAt(0)*Math.pow(2, 31) + n.charAt(1)*Math.pow(2,30) + ... n.charAt(n.length - 1);
```

### Simplified String hashing

```java
// Its java folks!
int hash = "anthony".hashCode();
```

### Primitive hashing

### 32 bit primitives

Do we need a hash code for a 32 bit value? An `int` is 32 bit. Do we modify it for accessing an array? No. So no 32 bit primitive needs to be hashed. Just converted. This means our original hash method for converting a `char` to an `int` is useless.

### 64bit primitives

An `int` is 32 bits. A `long` is 64 bit. How do we represent a 64 bit number as a 32 bit number? Well, we have to *compress* it.

If we look at the binary representation (hence the 64 bit), we can break it into two values. The most significant 32 bits and the least significant 32bits. To do so, we use the `right shift` operator. The right shift will add `n` number of 0s to the left side of the number (in binary), pushing the most significant values to the *right* while dropping the least significant bits in the process, eg right shifting `11110101` to the right 4 places gives us `00001111`.

  int msb = (int)(someLongValue >> 32);

We can always force a `long` to an `int` with casting

  int lsb = (int)someLongValue;

Now all we have to do is *merge* them. This merge is a compression. We are taking a 64 bit number and compressing it into a 32bit number that represents the bigger, hence we are taking the *name space* value and creating a *search space* value. To do so, simply `xor` the two.

  int key = msb ^ lsb;

Now we have a key to store our `long` value.

  a[key] = someLongValue;

#### More compression

If we were to implement our hash for 64 bit primitives as is, what problem do we face when storing? Hopefully you realized that we need a **very** big array. Hopefully you also realized that every `msb ^ lsb` represents 2 `long` values, but more on that in a bit. What we are mostly concerned with is reducing the size of the array to store the longs.

This is a pretty easy concept. If I want to limit any `int` to say size 10, we would simply use the modulus operator. We do the same here.

  int key = (msb ^ lsb) % array.length;

### Hashing objects

Hashing objects is beyond the scope of this class. Fortunately for us, `hashCode` exists on all objects. For *all* classes used in our sandbox coding, we will simply get the hash code by that method.

## Collisions

Hopefully you have noticed that in our `long` value example, we reduced a name space with 2<sup>64</sup> possibilities into a search space of `array.length`. If our array length was 10, that means we made 1.8446744e+19 numbers fit into 10 spaces. We obviously will have collisions. This means we need to do one of three things.

1. Use another location in the table (array)
2. Change the structure of the table.
3. Make the search space larger

Yet realize, there is such a thing as a `perfect hash` such that no collision will ever occur. We see these claims of perfection in security, but for the purpose of data structures, we can live with collisions. Our goal is to reduce the search from `O(n)` to `O(1)`. This means our hash computation has to be constant as well otherwise we would have to consider it in the efficiency as well. Keeping our hash function simple achieves our goal but will come with collisions.

### Using another location
When trying to find another place in the array we can use a technique called `probing`. While interesting, it is *well* beyond what I want you to learn in this class.

### Changing the structure of the table
We will cover how to handle this with mapping.

### Make the search space larger
This makes sense! But how far do we want to increase the search space? At some point we have to accept collisions.


### Chaining

So we have covered
