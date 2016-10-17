//package sorting;

public class ArraySorter {

   public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int n, int current){
      if(current >= n){return;}
      int smallest = findIndexOfSmallest(unsorted, unsorted[current], current, n - 1, current, current);
      swap(unsorted, current, smallest);
      selectionSort(unsorted, n, current+1);
   }

   public static <T extends Comparable<?super T>> void insertionSort(T[] unsorted, int n, int i, int found){
      if(i > n){return;}
      found = findIndexOfNextSmallerItem(unsorted, i, i);
      if(found > i){
         insertInOrder(unsorted,unsorted[found], found - 1);
      }

      insertionSort(unsorted, n, i+1, found);
   }

   /* refactor -
    you dont need half of what you passed.
    you also need better variable names so others can read it
    try to get to a signature of:
    findIndexOfSmallest(T[] a, int index, int position)
  */
   private static <T extends Comparable<? super T>> int findIndexOfSmallest(T[] a, T min, int start, int end, int minIndex, int i){
      if(i > end){return minIndex;}

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

   private static <T extends Comparable<? super T>> int findIndexOfNextSmallerItem(T[] a, int index, int smallest){
      if(index >= a.length){return -1;}

      if(a[smallest].compareTo(a[index]) > 0){return index;}

      return findIndexOfNextSmallerItem(a, ++index, smallest);
   }

   private static <T extends Comparable<? super T>> void insertInOrder(T[] a, T item, int s){
      if(s < 0){
         a[s+1] = item;
         return;
      }

      if(a[s].compareTo(item) < 0){
         a[s+1] = item;
         return;
      }

      a[s+1] = a[s];
      insertInOrder(a, item, --s);
   }

   // for testing reasons only
   public static String viewArray(Object[] a, String contents, int pos){
      if(pos == a.length){
         return contents; }
      contents += (contents.length() > 0 ? ", " : "") + a[pos++];
      return ArraySorter.viewArray(a, contents, pos);
   }

   //new integer counter
   public static int[] counterSort(int[] a){
      int biggestInt = ArraySorter.findBiggestInt(a);
      int[] counts = ArraySorter.countNumbers(a, biggestInt);
      int[] sorted = ArraySorter.reSortCount(a, counts);
      return sorted;
   }

   //step 1
   public static int findBiggestInt(int[] a){
      int max = a[0];
      for(int i = 0; i < a.length; i++){
         if(a[i] > max){
            max = a[i];
         }
      }
      return max;
   }

   //step 2-3
   public static int[] countNumbers(int[] unsorted, int biggestInt){
      int[] counts = new int[biggestInt + 1];
      for(int i = 0; i < unsorted.length; i++){
         counts[unsorted[i]]++;
      }
      return counts;
   }

   //step 4
   public static int[] reSortCount(int[] unsorted, int[] counts){
      int uC = 0;
      for(int i = 0; i < counts.length; i++){
         while(counts[i]!=0){
            unsorted[uC++] = i;
            counts[i]--;
         }
      }
      return unsorted;
   }


   public static void main(String[] args){
      String[] selection = {"b", "c", "a", "z", "e", "A"};
      System.out.println("Unsorted Array: " + ArraySorter.viewArray(selection, "", 0));
      ArraySorter.selectionSort(selection, selection.length, 0);
      System.out.println("Selection Sorted: " + ArraySorter.viewArray(selection, "", 0));

      String[] insertion = {"b", "c", "a", "z", "e", "A"};
      ArraySorter.insertionSort(insertion, insertion.length, 0, 0);
      System.out.println("Insertion Sorted: " + ArraySorter.viewArray(insertion, "", 0));

      int[] intArray = {5000,2,1};
      ArraySorter.counterSort(intArray);
      System.out.println(intArray[0]);
      System.out.println(intArray[1]);
      System.out.println(intArray[2]);
   }
}
