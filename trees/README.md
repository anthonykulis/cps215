# Trees

## Overview
Previously our ADTs were linear, or one dimensional. Trees provide a 2 dimensional abstraction, allowing for a more highly refined data structure.

Trees are typically implemented with nodes, but arrays can be used.

## Tree Terminology
Borrowed from [wiki](https://en.wikipedia.org/wiki/Tree_(data_structure))

### Root
The top node in a tree. Similar in concept to the front of a queue or the top of a stack.

### Child
A node directly connected to another node when moving away from the Root.

### Parent
The converse notion of a child.

### Siblings
A group of nodes with the same parent.

### Descendant
A node reachable by repeated proceeding from parent to child.

### Ancestor
A node reachable by repeated proceeding from child to parent.

### Leaf
A node with no children.

### Degree
The number of sub trees of a node.

### Edge
The connection between one node and another.

### Path
A sequence of nodes and edges connecting a node with a descendant.

### Level
The level of a node is defined by 1 + (the number of connections between the node and the root).

### Height of node
The height of a node is the number of edges on the longest path between that node and a leaf.

### Height of tree
The height of a tree is the height of its root node.

### Depth
The depth of a node is the number of edges from the tree's root node to the node.

## [Tree Traversal](https://en.wikipedia.org/wiki/Tree_(data_structure)#Traversal_methods)
A walk in which each parent node is traversed before its children is called a `pre-order walk`; a walk in which the children are traversed before their respective parents are traversed is called a `post-order walk`; a walk in which a node's left subtree, then the node itself, and finally its right subtree are traversed is called an `in-order traversal`. A `level-order` walk effectively performs a `breadth-first search` over the entirety of a tree; nodes are traversed level by level, where the root node is visited first, followed by its direct child nodes and their siblings, followed by its grandchild nodes and their siblings, etc., until all nodes in the tree have been traversed.

### Breadth-First Search
As noted, a breadth-first search can be thought of if we were to take the tree and collapse it "in order". In order for this implies that the root of the tree would be cell 0 in an array, the roots immediate children are cells 1 through m, the grandchildren of all siblings are cells m+1 to n, etc.

Without any other logic, to complete a breadth-first search would require at worst O(n) to complete.

### Depth-First Search
A Depth-First search requires starting at the root, digging to the first child (first sibling), digging to the first grandchild, until you reach a leaf. Once a leaf is reached, you return to the immediate parent and then dig down into the next sibling child. Once you have exhausted the lowest level parent, you then proceed onto that parents sibling, exhausting that path. You repeat this concept until you have completely traversed the tree.

Again, without any other logic, to complete a Depth-First search would require at worst O(n) to complete.


## Tree Implementations
There are many, many trees to choose from. In this class we will cover only the `binary tree` and `b-tree`. From these two trees, you should be able to read about other trees and make proper decisions on which tree is best suited for your task.

### Binary Tree
A binary tree may, or may not, be balanced. It is comprised of a root node where each subsequent added nodes are determined to go the right or left depending on it value compared to the root. For values less than the root, the node goes on the left. For values greater than or equal to the root, the values go on the right.

When a Binary Tree has more than one node, the process will repeat itself until you find a node that has no child.

The Binary Tree can only have two children, and is not required to be sorted.

Because of it's simple deterministic structure, to find a node requires at most the height of the tree in operations, but because it is not balanced, the worst possible case is a sorted tree whose root is either the smallest or the largest value and each subsequent node inserted is ordered logically smaller or larger, respectively, hence it is O(n) to search.

#### Example Insert

```java
void insert(Node node, Node data){
  if(node == null) node = data;
  else {
    if(node.value < data.value){
      insert(node.leftChild, data);
    } else {
      insert(node.rightChild, data);
    }
  }
}
```

#### Example Find
Just a simple example to see if a node is in a tree, of course, you may want a different implementation.

```java
boolean isInTree(Node node, Node data){
  if(node == null) return false;
  if(node.value == data.value) return true;
  if(node.value < data.value) return isInTree(node.leftChild, data);
  return isInTree(node.rightChild, data);
}

```

### B-Tree
The reason to introduce a B-Tree is because at its core form, this tree is very commonly used in databases. Understand the concept will increase your full-stack engineering understanding.

A b-tree is a balanced, ordered tree. Being that it is balanced, it allows for fast search times. Similar to concept is a binary search such that you can divide the process in half recursively, since we are guaranteed that each half will be of similar height, we can obtain O(log n) search times.

Another strength is that in a b-tree has a fast insert and delete time as well, O(log n) to be exact. How we insert and delete is beyond the scope of this class, but knowing this will help us decide what tree ADT to choose.

#### B-Tree Indexing

As a side note to the B-Tree, we shall cover indexing. A database is literally just a file on a computer. If the database has 1TB of data, bringing that all into memory would be futile. Because of so, if we were required to bring a table or set of tables into memory every time a database query occurred, our slowest operation would be the HDD seek time. To speed things up, indexing occurs such that a minimal amount of information is brought into memory that allows us to search the B-Tree without the need for the data, and then when we locate the record we want, we know exactly where to search. This means if you are building a database in the future, and know you will need to search a given column in a table frequently, it is wise to index that column ahead of time.

## Other Trees
If you are interested in researching other types of trees on your own, please visit this [wiki](https://en.wikipedia.org/wiki/List_of_data_structures)
