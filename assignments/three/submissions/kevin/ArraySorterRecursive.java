//package sorting;

public class ArraySorterRecursive {

  /*
  Refactor
  Superflous if/else
  Superflous params in signature
  Use signature: selectionSort(T[] unsorted, int n)
  */
   public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int current, int max){
      if(current != max){
         int smallest = findIndexOfSmallest(unsorted, current, max - 1, current);
         swap(unsorted, current, smallest);
         selectionSort(unsorted, current + 1, max);
      }
      else{
         return;
      }
   }

   /*
    refactor
    lose expensive creating min variable.
    superfluous if/else if each statment is returning
    use signature of findIndexOfSmallest(T[] array, int index, int position)
    */
   private static <T extends Comparable<? super T>> int findIndexOfSmallest(T[] array, int start, int end, int minIndex){
      T min = array[minIndex];
      if(start > end){
         return minIndex;
      }
      else if(min.compareTo(array[start]) > 0){
         return findIndexOfSmallest(array, start + 1, end, start);
      }
      else{
         return findIndexOfSmallest(array, start + 1 , end, minIndex);
      }
   }

   private static void swap(Object[] a, int i, int j){
      Object t = a[i];
      a[i] = a[j];
      a[j] = t;
   }



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



   public static void countingSort(int[] unsorted){
      countSort(unsorted, populate(getLargest(unsorted), unsorted));
   }

   private static int getLargest(int[] array){
      int largest = 0;
      for(int i = 0; i < array.length; i++){
         if(largest < array[i]){
            largest = array[i];
         }
      }
      return largest;
   }

   private static int[] populate(int size, int[] array){
      int[] counter = new int[size + 1];
      for(int i = 0; i < array.length; i++){
         counter[array[i]]++;
      }
      return counter;
   }

   private static int[] countSort(int[] unsorted, int[] counter){
      int unsortedPos = 0;
      int counterPos;
      int count;

      for(counterPos = 0; counterPos < counter.length; counterPos++){
         count = counter[counterPos];
         while(count > 0){
            unsorted[unsortedPos] = counterPos;
            unsortedPos++;
            count--;
         }
      }
      return unsorted;
   }



   // for testing reasons only
   public static String viewArray(Object[] a, String contents, int pos){
      if(pos == a.length){
         return contents; }
      contents += (contents.length() > 0 ? ", " : "") + a[pos++];
      return ArraySorterRecursive.viewArray(a, contents, pos);
   }

   public static void main(String[] args){
      String[] selection = {"b", "c", "a", "z", "e", "A"};
      System.out.println("Unsorted Array: " + ArraySorterRecursive.viewArray(selection, "", 0));
      ArraySorterRecursive.selectionSort(selection, 0, selection.length);
      System.out.println("Selection Sorted: " + ArraySorterRecursive.viewArray(selection, "", 0));

      String[] insertion = {"b", "c", "a", "z", "e", "A"};
      ArraySorterRecursive.insertionSort(insertion, 0);
      System.out.println("Insertion Sorted: " + ArraySorterRecursive.viewArray(insertion, "", 0));
   }
}
