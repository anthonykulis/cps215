/*
  Wyatt, note I commented out all your other code
  becauuse it caused compile time errors. You can
  uncomment if you wish.
*/

//package sorting;

import data_structures.queues.NodeQueue;
import java.util.*;

//Author: Wyatt Melton


public class ArraySorter {

   private static final int MIN_SIZE = 10;
   private static final int RADIX = 10;

   private static void clear(String[] a) {
      for (int i = 0; i < a.length; i++) {
         a[i] = "";
      }
   }

   /*

    Radix sort sorts values int values of type string by their digit position
    "123" gets sorted as "3" then "2" then "1"
    This means you will need to

    Loop across of each value in array.
      1) Convert the current digit index of the string to type int
      2) have a bucket that respents that value
      3) Place the full int as a string "123" into the 3 bucket
      4) Re-arrange the array according to the buckets order

    Hints:

    Rearrange the array according to the input of your buckets. First in First out.
    You will have multiple buckets. You will have an array of buckets.

    Arrays are ordered. Hence we know the order to re-arrange.
    For each bucket repsented by a cell of an array, the buckets need to
    be FIFO.

   */
   public static void radixSort(String[] unsorted) {

      // dont need
      String[] unsortedArray = unsorted;

      // you need buckets for every possible int in range of base 10 numbers
      String[] bucket = new String[RADIX

      int unsortedLength = unsortedArray.length;
      int bucketLength = bucket.length;

      // there is no size limitations, so you cannot
      // restrict it like this. You can however, pass
      // a string length to expect, but you will have to catch
      // a string not equal to the prescribed length
      int maxLength = 2;

      NodeQueue<String> nq = new NodeQueue<>();

      while(maxLength >= 0) {

         clear(bucket);

         for(int i = 0; i < unsortedLength; i++) {
            nq.push(unsortedArray[i]);

            /*
              This is your offending line of code in runtime.
              You are making the assumption peak() returns something.
              But at the end of the day, you will not be concating strings,
              so fixing it is moot.
            */
            bucket[Character.getNumericValue(nq.peek().charAt(maxLength))] += nq.shift();
         }

         /*
          I get where you're going, but read the algoritm. You will not
          do this, like this, here.
         */
         for(int i = 0; i < bucketLength; i++) {
            if(!bucket[i].equals("")) {
               nq.push(bucket[i]);
            }
         }

         /*
         You're kind of correct, you need to reorder the
         orignal array with the partially sorted values to re-run.
         You do not need, nor are allowed, a helper array to
         store your values in. In class, we discussed you need, and
         are only allowed, one array of a data structure type NodeQueue.
         */
         for(int i = 0; i < unsortedLength; i++) {
            unsortedArray[i] = nq.shift();
         }
         maxLength--;
      }
   }


   /*
    Im not investigating this, but I think
    this only pads 0 to 3 right?

    What if my longest String in the array was
    "100000000000" and my shortest was "2"?
    This helper is a pre-processor that should
    not be used in the actual sorting. It is
    optional for a developer to use.
   */
   public static String padLeft(String s) {
      return String.format("%1$-3s", s);
   }

/*
   public static <T extends Comparable<? super T>> void quickSort(T[] unsorted) {
      int first = 0, last = unsorted.length - 1;

      if(lessThanMinSize(first, last)) {
         insertionSort(unsorted, first);
      }
      else{
         int pivot = naiveRandomStrategy(first, last);
         partition(unsorted, pivot, first, last);
      }

   }

   private static <T extends Comparable<? super T>> int partition(T[] unsorted, int pivot, int front, int back) {
      //Swap last position with pivot
      swap(unsorted, pivot, back);

      int fromLeft = front;
      int fromRight = back;

      while(fromLeft < fromRight) {

         while(unsorted[fromLeft].compareTo(unsorted[fromRight]) < 0) {
            fromLeft++;
         }
         while(unsorted[fromLeft].compareTo(unsorted[fromRight]) > 0) {
            fromRight--;
         }
         swap(unsorted, fromLeft, fromRight);
      }
      swap(unsorted, fromLeft, pivot);

      return pivot;

   }

   private static boolean lessThanMinSize(int first, int last) {
      return (last - first + 1 < MIN_SIZE);
   }

   // provides a random index within the bounds, exclusively
   private static int naiveRandomStrategy(int from, int to){
      if(to - from < 2){
         return from; }
      return (int)(Math.random() * (to - from - 1)) + from + 1;
   }

   public static int getNaiveRandomStrategy(int from, int to){
      return naiveRandomStrategy(from, to);
   }

   //Counting Sort
   public static void countingSort(int[] unsorted) {
      int largestValue = findLargestValue(unsorted);
      int[] countArray = createAndPopulate(unsorted, largestValue);
      sortArray(unsorted, countArray);
   }

   private static int findLargestValue(int[] unsorted) {
      int largestValue = unsorted[0];
      for (int i = 0; i < unsorted.length; i++) {
         if (unsorted[i] > largestValue) {
            largestValue = unsorted[i];
         }
      }
      return largestValue;
   }

   private static int[] createAndPopulate(int[] unsorted, int n) {
      int[] countArray = new int[n + 1];

      for(int i = 0; i < unsorted.length; i++) {
         countArray[unsorted[i]] += 1;
      }
      return countArray;
   }

   private static void sortArray(int[] unsorted, int[] countArray) {
      int unsortedPointer = 0;
      int countArrayPointer = 0;
      for(int i = 0; i < countArray.length; i++) {
         while(countArray[i] > 0) {
            unsorted[unsortedPointer] = i;
            unsortedPointer++;
            countArray[i]--;
         }
      }
   }

   //Merge
   public static <T extends Comparable<? super T>> void mergeSort(T[] unsorted) {
      int length = unsorted.length;

      @SuppressWarnings("unchecked")
         T[] consumableArray = (T[]) new Comparable[length];
      mergeSort(unsorted, consumableArray, 0, unsorted.length - 1);
   }

   private static <T extends Comparable<? super T>> void merge(T[] a, T[] c, int start, int mid, int end) {

      System.arraycopy(a, start, c, start, end - start + 1);

      int leftStart = start;
      int rightStart = mid + 1;
      int i = start;

      while(leftStart <= mid && rightStart <= end){
         if(c[leftStart].compareTo(c[rightStart]) <= 0){
            a[i++] = c[leftStart++];
         }
         else {
            a[i++] = c[rightStart++];
         }
      }

      while(leftStart <= mid){
         a[i++] = c[leftStart++];
      }

   }

   public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int n){
      if(n == unsorted.length){
         return;
      }
      int smallest = findIndexOfSmallest(unsorted, n, n + 1);
      swap(unsorted, smallest, n);
      selectionSort(unsorted, n + 1);

   }

   public static <T extends Comparable<?super T>> void insertionSort(T[] unsorted, int n){
      if(n == unsorted.length)
         return;
      int found = -1;
      found = findIndexOfNextSmallerItem(unsorted, unsorted[n], n + 1);
      if(found > n) insertInOrder(unsorted, unsorted[found], found - 1);
      insertionSort(unsorted, n+1);
   }

   private static <T extends Comparable<? super T>> int findIndexOfSmallest(T[] a, int n, int position){
      if(position == a.length){
         return n;
      }
      if(a[position].compareTo(a[n]) < 0){
         n = position;
      }
      return findIndexOfSmallest(a, n, position + 1);
   }

   private static void swap(Object[] a, int i, int j){
      Object t = a[i];
      a[i] = a[j];
      a[j] = t;
   }

   private static <T extends Comparable<? super T>> int findIndexOfNextSmallerItem(T[] a, T compare, int position){
      if(position == a.length)
         return -1;
      if(a[position].compareTo(compare) < 0)
         return position;
      return findIndexOfNextSmallerItem(a, compare, position + 1);
   }

   private static <T extends Comparable<? super T>> void insertInOrder(T[] a, T item, int s){
      if(s < 0){
         a[s + 1] = item;
         return;
      }
      if(a[s].compareTo(item) < 0){
         a[s + 1] = item;
         return;
      }
      a[s+1] = a[s];
      insertInOrder(a, item, s - 1);
   }

*/
   // for testing reasons only
   public static String viewArray(Object[] a, String contents, int pos){
      if(pos == a.length){
         return contents; }
      contents += (contents.length() > 0 ? ", " : "") + a[pos++];
      return ArraySorter.viewArray(a, contents, pos);
   }

   public static void main(String[] args){
/*
      String[] selection = {"b", "c", "a", "z", "e", "A"};
      System.out.println("Unsorted Array: " + ArraySorter.viewArray(selection, "", 0));
      ArraySorter.selectionSort(selection, selection.length);
      System.out.println("Selection Sorted: " + ArraySorter.viewArray(selection, "", 0));

      String[] insertion = {"b", "c", "a", "z", "e", "A"};
      ArraySorter.insertionSort(insertion, 0);
      System.out.println("Insertion Sorted: " + ArraySorter.viewArray(insertion, "", 0));

      int[] counting = {1, 3, 5, 2, 2, 4, 3};
      String unsortedString = "";
      for(int i = 0; i < counting.length; i++) {
         unsortedString += (counting[i] + " ");
      }
      System.out.println("Unsorted number array: " + unsortedString);

      ArraySorter.countingSort(counting);

      String sortedString = "";
      for (int i = 0; i < counting.length; i++) {
         sortedString += (counting[i] + " ");
      }
      System.out.println("Sorted numbers: " + sortedString);

      String[] merging = {"z", "B", "q", "a", "a", "X", "k", "i", "e", "V", "v"};
      ArraySorter.mergeSort(merging);
      System.out.println("Merge Sorted: " + ArraySorter.viewArray(merging, "", 0));
*/
      String[] radix = {"0100000", "514", "223", "105", "618", "007", "212"};
      System.out.println("Unsorted array: " + ArraySorter.viewArray(radix, "", 0));
      ArraySorter.radixSort(radix);
      System.out.println("Radix-sorted: " + ArraySorter.viewArray(radix, "", 0));



   }
}
