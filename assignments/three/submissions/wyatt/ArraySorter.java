//package sorting;

//Author: Wyatt Melton


public class ArraySorter {

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

   /*
      refactor
      supeflous params in signature
      use signature selectionSort(T[] unsorted, int n)
   */
   public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int length, int index){
      if(index < length)   {
         int smallest = findIndexOfSmallest(unsorted, unsorted[index], index, length - 1, index, index);
         swap(unsorted, index, smallest);
         selectionSort(unsorted, length, index + 1);
      }
   }

   public static <T extends Comparable<?super T>> void insertionSort(T[] unsorted, int n){
    if(n == unsorted.length) return;
    int found = -1;
    found = findIndexOfNextSmallerItem(unsorted, unsorted[n], n + 1);
    if(found > n) insertInOrder(unsorted, unsorted[found], found - 1);
    insertionSort(unsorted, n+1);
  }

  /* refactor -
   you dont need half of what you passed.
   you also need better variable names so others can read it
   try to get to a signature of:
   findIndexOfSmallest(T[] a, int index, int position)
 */
   private static <T extends Comparable<? super T>> int findIndexOfSmallest(T[] a, T min, int start, int end, int minIndex, int i){
      if(i > end){
         return minIndex;}

      if(a[i].compareTo(min) < 0){
         min = a[i];
         minIndex = i;
      }
      return findIndexOfSmallest(a, min, start, end, minIndex, i+1);
   }
   private static void swap(Object[] a, int i, int j){
      Object t = a[i];
      a[i] = a[j];
      a[j] = t;
   }

   private static <T extends Comparable<? super T>> int findIndexOfNextSmallerItem(T[] a, T compare, int position){
    if(position == a.length) return -1;
    if(a[position].compareTo(compare) < 0) return position;
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
      ArraySorter.selectionSort(selection, selection.length, 0);
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
   }
}
