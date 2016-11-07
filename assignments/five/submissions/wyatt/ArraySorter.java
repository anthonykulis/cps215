package assignments.five.submissions.wyatt;

import data_structures.queues.NodeQueue;
import java.util.*;

//Author: Wyatt Melton


public class ArraySorter {

   private static final int MIN_SIZE = 10;
   private static final int RADIX = 10;


   //Radix Sort
   public static void radixSort(String[] unsorted) {

      int maxValue = unsorted[0].length() - 1;
      int index = 0;
      int bPosition = 0;
      @SuppressWarnings("unchecked")
      NodeQueue<String>[] buckets = new NodeQueue[RADIX];

      for(index = 0; index < RADIX; index++) {
         buckets[index] = new NodeQueue();
      }

      while(maxValue >= 0) {
         for(index = 0; index < unsorted.length; index++) {
            bPosition = Character.getNumericValue(unsorted[index].charAt(maxValue));
            buckets[bPosition].push(unsorted[index]);
         }

         bPosition = 0;

         for(index = 0; index < unsorted.length; index++) {
            while(bPosition < RADIX - 1 && buckets[bPosition].isEmpty()) bPosition++;
            unsorted[index] = buckets[bPosition].shift();
         }
         maxValue--;
      }
   }

   public static <T extends Comparable<? super T>> String[] padStringLeft(String[] unsorted) {
      int maxLength = 0, index = 0;
      for (index = 0; index < unsorted.length; index++) {
         if(unsorted[index].length() > maxLength) maxLength = unsorted[index].length() - 1;
      }
     for(index = 0; index < unsorted.length; index++) {
         while(unsorted[index].length() <= maxLength) {
            unsorted[index] = "0" + unsorted[index];
         }
      }

      return unsorted;
   }

   //Quick Sort
   public static <T extends Comparable<? super T>> void quickSort(T[] unsorted) {
      quickSort(unsorted, 0, unsorted.length - 1);
   }

   private static <T extends Comparable<? super T>> void quickSort(T[] unsorted, int first, int last) {

      if(lessThanMinSize(first, last)) {
         insertionSort(unsorted, first);
      }
      else  {
         int pivot = partition(unsorted, first, last);
         quickSort(unsorted, first, pivot - 1);
         quickSort(unsorted, pivot + 1, last);
      }
   }


   private static <T extends Comparable<? super T>> int partition(T[] unsorted, int front, int back) {
      int pivot = grabMidValue(unsorted);

      swap(unsorted, pivot, back);

      while(front > back) {

         while(unsorted[front].compareTo(unsorted[pivot]) <= 0) {
            front++;
         }
         while(unsorted[back].compareTo(unsorted[pivot]) >= 0) {
            back--;
         }
         if(front < back) swap(unsorted, front, back);
      }
      swap(unsorted, front, pivot);

      return pivot;
   }

   private static boolean lessThanMinSize(int first, int last) {
      return (last - first + 1 < MIN_SIZE);
   }

   private static <T extends Comparable<? super T>> int grabMidValue(T[] a) {
      int pivot =  a.length/2;

      if(pivot%2 == 0)
         return pivot - 1;

      else
         return pivot;
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

   //Merge Sort
   public static <T extends Comparable<? super T>> void mergeSort(T[] unsorted) {
      int length = unsorted.length;

      @SuppressWarnings("unchecked")
         T[] consumableArray = (T[]) new Comparable[length];
      mergeSort(unsorted, consumableArray, 0, unsorted.length - 1);
   }

   private static <T extends Comparable<? super T>> void mergeSort(T[] unsorted, T[] consumable, int start, int end) {

      if(end == start) {
         return; }

      int mid = ((start + end) / 2);

      mergeSort(unsorted, consumable, start, mid);
      mergeSort(unsorted, consumable, mid + 1, end);
      merge(unsorted, consumable, start, mid, end);
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

   //Selection Sort
   public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int n){
      if(n == unsorted.length){
         return;
      }
      int smallest = findIndexOfSmallest(unsorted, n, n + 1);
      swap(unsorted, smallest, n);
      selectionSort(unsorted, n + 1);

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



   //Insertion Sort
   public static <T extends Comparable<?super T>> void insertionSort(T[] unsorted, int n){
      if(n == unsorted.length)
         return;
      int found = -1;
      found = findIndexOfNextSmallerItem(unsorted, unsorted[n], n + 1);
      if(found > n) insertInOrder(unsorted, unsorted[found], found - 1);
      insertionSort(unsorted, n+1);
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

   //Used in multiple sorting types
   private static void swap(Object[] a, int i, int j){
      Object t = a[i];
      a[i] = a[j];
      a[j] = t;
   }


   // for testing reasons only
   public static String viewArray(Object[] a, String contents, int pos){
      if(pos == a.length){
         return contents; }
      contents += (contents.length() > 0 ? ", " : "") + a[pos++];
      return ArraySorter.viewArray(a, contents, pos);
   }

   public static void main(String[] args){

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

      String[] radix = {"10", "19401", "2020", "1", "15", "55559"};
      System.out.println("Unsorted array: " + ArraySorter.viewArray(radix, "", 0));

      radix = padStringLeft(radix);

      ArraySorter.radixSort(radix);
      System.out.println("Radix-sorted: " + ArraySorter.viewArray(radix, "", 0));


      Integer[] quick = {0, 55, 21, 4, 7, 36, 9, 15, 33, 54, 70, 13, 12, 40};
      String unsortedQuick = "";
      for(int i = 0; i < quick.length; i++) {
         unsortedQuick += (quick[i] + " ");
      }
      System.out.println("Unsorted array: " + unsortedQuick);

      ArraySorter.quickSort(quick);

      String sortedQuick = "";
      for(int i = 0; i < quick.length; i++) {
         sortedQuick += (quick[i] + " ");
      }
      System.out.println("Sorted Quick: " + sortedQuick);


   }
}
