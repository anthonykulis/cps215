// Mark Kiel: Assignment 4

package assignments.five;

import java.util.*;
import data_structures.queues.*;

public class ArraySorter {

  private static final int MIN_SIZE = 10;

  public static <T extends Comparable<? super T>> void quickSort(T[] unsorted){
      quickSort(unsorted, 0, unsorted.length - 1);
  }

  private static <T extends Comparable<? super T>> void quickSort(T[] unsorted, int first, int last){
    if(last - first + 1 < MIN_SIZE){
        insertionSort(unsorted, unsorted.length - 1);
    }
    else if(first < last){
        int pivotIndex = partition(unsorted, first, last);
        quickSort(unsorted, first, pivotIndex - 1);
        quickSort(unsorted, pivotIndex + 1, last);
    }
  }

  private static <T extends Comparable<? super T>> int partition(T[] unsorted, int first, int last){
      int pivotIndex = getRandomIndex(first, last);
      T pivot = unsorted[pivotIndex];
      swap(unsorted, pivotIndex, last);

      int leftIndex = first, rightIndex = (last - 1);

      while(leftIndex < rightIndex){
          while((unsorted[leftIndex].compareTo(pivot) <= 0) && leftIndex < last) leftIndex++;
          while((unsorted[rightIndex].compareTo(pivot) >= 0) && rightIndex > first) rightIndex--;
          if(leftIndex < rightIndex) swap(unsorted, leftIndex, rightIndex);
      }

      swap(unsorted, last, leftIndex);
      return leftIndex;
  }

  private static int getRandomIndex(int from, int to){
      int range = ((to - 1) - (from + 1)) + 1;
      return (int)(Math.random() * range + (from + 1));
  }

// ---------------------------------------------------------------------------------------------------------- //

  public static void radixSort(String[] unsorted){
    NodeQueue<String>[] buckets;
    int digit = getLengthOfLongest(unsorted) - 1;

    while(digit >= 0){
        buckets = FillBuckets(unsorted, digit);
        EmptyBuckets(unsorted, buckets);
        digit--;
    }
  }

  private static NodeQueue<String>[] FillBuckets(String[] unsorted, int digit){
      int index = 0, bucket = 0;
      NodeQueue<String>[] queues = new NodeQueue[10];
      for(int i = 0; i < 10; i++) queues[i] = new NodeQueue();

      while(index < unsorted.length){
            String strBucket = String.valueOf(unsorted[index].charAt(digit));
            bucket = Integer.parseInt(strBucket);
            queues[bucket].push(unsorted[index]);
            index++;
      }
      return queues;
  }

  private static void EmptyBuckets(String[] unsorted, NodeQueue<String>[] queues){
      int index = 0, bucket = 0;

      for(index = 0; index < unsorted.length; index++){
          while(bucket < 9 && queues[bucket].isEmpty()) bucket++;
          unsorted[index] = queues[bucket].shift();
      }
  }

  private static int getLengthOfLongest(String[] strArray){
      int max = 0;
      for(String s : strArray)
          if(s.length() > max) max = s.length();
      return max;
  }

  public static void addLeadingZeros(String[] strArray){
      int longest = getLengthOfLongest(strArray);

      for(int index = 0; index < strArray.length; index++){
          int difference = longest - strArray[index].length();
          if(difference > 0){
              for(int i = 0; i < difference; i++){
                  strArray[index] = "0" + strArray[index];
              }
          }
      }
  }

  private static void removeLeadingZeros(String[] strArray){
    int length = strArray[0].length();
    int position = 0;

    for(int index = 0; index < strArray.length; index++){
        while(strArray[index].charAt(position) == '0' && (position < length - 1)) position++;
        strArray[index] = strArray[index].substring(position);
        position = 0;
    }
  }
// -------------------------------------------------------------------------------------------------------- //

  public static <T extends Comparable<? super T>> void countingSort(T[] unsorted){
    int[] counts = createCountArray(unsorted);
    insertFromCountArray(unsorted, counts);
  }

  private static <T extends Comparable<? super T>> int[] createCountArray(T[] unsorted){
    Integer size = 0;
    int[] countArray = {};

    if(unsorted instanceof Integer[]){
        size = (Integer)findMaxValue(unsorted, unsorted.length);

        countArray = new int[size + 1];

        for(int i = 0; i < unsorted.length; i++){
             countArray[(Integer)unsorted[i]]++;
        }
    }

    if(unsorted instanceof Character[]){
        Character max = (Character)findMaxValue(unsorted, unsorted.length);
        size = (int)max;
        countArray = new int[size + 1];

        for(int i = 0; i < unsorted.length; i++){
            countArray[(Character)unsorted[i]]++;
        }
    }
    return countArray;
  }

  private static <T extends Comparable<? super T>> void insertFromCountArray(T[] unsorted, int[] countArray){
    int index = 0;

    for(int i = 0; i < countArray.length; i++){
        while(countArray[i] != 0){
            T element;
            if(unsorted instanceof Integer[]) element = (T)new Integer(i);
            else if(unsorted instanceof Character[]) element = (T)new Character((char)i);
            else { return; }

            unsorted[index] = element;
            index++;
            countArray[i]--;
        }
    }
  }

  private static <T extends Comparable<? super T>> T findMaxValue(T[] unsorted, int n){
    T max;

    if(n == 0) return unsorted[0];
    max = findMaxValue(unsorted, --n);
    if(max.compareTo(unsorted[n]) < 0) max = unsorted[n];

    return max;
  }

  private static <T extends Comparable<? super T>> T findMinValue(T[] unsorted, int n){
    T min;

    if(n == 0) return unsorted[0];
    min = findMinValue(unsorted, --n);
    if(min.compareTo(unsorted[n]) > 0) min = unsorted[n];
    return min;
  }

// ------------------------------------------------------------------------------------------------------ //

  public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int n){
    selectionSort(unsorted, 0, n);
  }

  private static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int position, int n){
    if(position == (n - 1)) return;  // array is sorted

    int smallest = findIndexOfSmallest(unsorted, position, n - 1);
    swap(unsorted, position, smallest);
    selectionSort(unsorted, position + 1, n);
  }

  private static <T extends Comparable<? super T>> int findIndexOfSmallest(T[] a, int start, int end){
    int minIndex = start;
    if(start == end) return minIndex;  // base case

    minIndex = findIndexOfSmallest(a, start + 1, end);
    if(a[start].compareTo(a[minIndex]) < 0) {
        minIndex = start;
    }
    return minIndex;
  }

// ---------------------------------------------------------------------------------------------------------- //

  public static <T extends Comparable<? super T>> void insertionSort(T[] unsorted, int n){
    insertionSort(unsorted, 0, n);
  }

  private static <T extends Comparable<?super T>> void insertionSort(T[] unsorted, int position, int n){
    if(position == n) return;

    T comparable = unsorted[position];
    int index = findIndexOfNextSmallerItem(unsorted, position, comparable);
    if(index > 0) insertInOrder(unsorted, index);
    insertionSort(unsorted, position + 1, n);
  }

  private static <T extends Comparable<? super T>> int findIndexOfNextSmallerItem(T[] a, int start, T comparable){
    if(start == a.length) return -1;
    //return a[start].compareTo(comparable) < 0 ? start : findIndexOfNextSmallerItem(a, start + 1, comparable);
    if(a[start].compareTo(comparable) < 0){
        return start;
    }
    return findIndexOfNextSmallerItem(a, start + 1, comparable);
  }

  private static <T extends Comparable<? super T>> void insertInOrder(T[] a, int s){
    T cached = a[s--];

    if(s < 0) return; // prevent out of bounds on array
    if(a[s].compareTo(cached) < 0) return; // array is sorted up to a[s+1], we're done
    a[s + 1] = a[s];
    a[s] = cached;
    insertInOrder(a, s);
  }

// --------------------------------------------------------------------------------------------------------- //

  private static void swap(Object[] a, int i, int j){
    Object t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  // for testing reasons only
  public static String viewArray(Object[] a, String contents, int pos){
    if(pos == a.length){ return contents; }
    contents += (contents.length() > 0 ? ", " : "") + a[pos++];
    return ArraySorter.viewArray(a, contents, pos);
  }

    public static <T extends Comparable<? super T>> void mergeSort(T[] unsorted){
        // create temporary array half size of the unsorted array
        T[] consumable = (T[])new Comparable[unsorted.length];

        mergeSort(unsorted, consumable, 0, unsorted.length - 1);
    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] unsorted, T[] consumable, int start, int end){

        if(start != end){
            int middle = (start + end) / 2;


            mergeSort(unsorted, consumable, start, middle);
            mergeSort(unsorted, consumable, middle + 1, end);

            merge(unsorted, consumable, start, middle, end);
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] a, T[] b, int start, int mid, int end){
        int index = 0;
        int leftPosition = start, rightPosition = (mid + 1);

        while(leftPosition <= mid && rightPosition <= end){

            if(a[leftPosition].compareTo(a[rightPosition]) < 0){
                b[index] = a[leftPosition];
                leftPosition++;
            }
            else{
                b[index] = a[rightPosition];
                rightPosition++;
            }
            index++;
         }

         // If left side of subarray has been completely iterated, merge right side in
         if(leftPosition > mid){
             for(int i = rightPosition; i <= end; i++){
                b[index] = a[i];
                index++;
             }
         }

         // If right side of subarray has been completely iterated, merge left side in

         if(rightPosition > end){
             for(int i = leftPosition; i <= mid; i++){
                b[index] = a[i];
                index++;
             }
         }

         index = 0; // reset temp array index and append it to the main array
         for(int i = start; i <= end; i++){
             a[i] = b[index];
             index++;
         }
    }

  public static void main(String[] args){
    Integer[] numbers;
    Random generator = new Random();

    String[] selection = {"b", "c", "a", "z", "e", "A"};
    System.out.println("Unsorted Array: " + ArraySorter.viewArray(selection, "", 0));
    ArraySorter.selectionSort(selection, selection.length);
    System.out.println("Selection Sorted: " + ArraySorter.viewArray(selection, "", 0));

    String[] insertion = {"b", "c", "a", "z", "e", "A"};
    ArraySorter.insertionSort(insertion, insertion.length);
    System.out.println("Insertion Sorted: " + ArraySorter.viewArray(insertion, "", 0));

    Integer[] counting = {66, 6, 11, 1, 5, 0, 15, 58};
    ArraySorter.countingSort(counting);
    System.out.println("Counting Sorted: " + ArraySorter.viewArray(counting, "", 0));

    Character[] characters = {'h', 'T', 'n', '*', 'B', '%', '2'};
    ArraySorter.countingSort(characters);
    System.out.println("Counting Sorted: " + ArraySorter.viewArray(characters, "", 0));

    numbers = new Integer[10];
    for(int i = 0; i < 10; i++){
        numbers[i] = generator.nextInt(10);
    }
    ArraySorter.mergeSort(numbers);
    System.out.println("Merge Sorted: " + ArraySorter.viewArray(numbers, "", 0));


    String[] string_numbers = new String[10];
    for(int i = 0; i < 10; i++){
        string_numbers[i] = Integer.toString(generator.nextInt(100));
    }

    addLeadingZeros(string_numbers);
    radixSort(string_numbers);
    removeLeadingZeros(string_numbers);
    System.out.println("Radix Sorted: " + ArraySorter.viewArray(string_numbers, "", 0));

    numbers = new Integer[10];
    for(int i = 0; i < 10; i++){
        numbers[i] = generator.nextInt(10);
    }
    ArraySorter.quickSort(numbers);
    System.out.println("QuickSort Sorted: " + ArraySorter.viewArray(numbers, "", 0));
  }
}
