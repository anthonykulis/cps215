// Mark Kiel: Assignment 3

//package assignments.three;

public class ArraySorter {
  public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int n){
    selectionSort(unsorted, 0, n);
  }

  /* Good use of helper */
  private static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int position, int n){
    if(position == (n - 1)) return;  // array is sorted

    int smallest = findIndexOfSmallest(unsorted, position, n - 1);
    swap(unsorted, position, smallest);
    selectionSort(unsorted, position + 1, n);
  }

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

  private static <T extends Comparable<? super T>> int findIndexOfSmallest(T[] a, int start, int end){
    int minIndex = start;
    if(start == end) return minIndex;  // base case

    minIndex = findIndexOfSmallest(a, start + 1, end);
    if(a[start].compareTo(a[minIndex]) < 0) {
        minIndex = start;
    }
    return minIndex;
  }

  private static void swap(Object[] a, int i, int j){
    Object t = a[i];
    a[i] = a[j];
    a[j] = t;
  }


  private static <T extends Comparable<? super T>> int findIndexOfNextSmallerItem(T[] a, int start, T comparable){
      if(start == a.length) return -1;

      if(a[start].compareTo(comparable) < 0){
          return start;
      }
      return findIndexOfNextSmallerItem(a, start + 1, comparable);
  }

  private static <T extends Comparable<? super T>> void insertInOrder(T[] a, int s){
    T cached = a[s--];

    if(s < 0) return;
    if(a[s].compareTo(cached) < 0) return;
    a[s + 1] = a[s];
    a[s] = cached;
    insertInOrder(a, s);
  }

  // for testing reasons only
  public static String viewArray(Object[] a, String contents, int pos){
    if(pos == a.length){ return contents; }
    contents += (contents.length() > 0 ? ", " : "") + a[pos++];
    return ArraySorter.viewArray(a, contents, pos);
  }

  public static void main(String[] args){
    String[] selection = {"b", "c", "a", "z", "e", "A"};
    System.out.println("Unsorted Array: " + ArraySorter.viewArray(selection, "", 0));
    ArraySorter.selectionSort(selection, selection.length);
    System.out.println("Selection Sorted: " + ArraySorter.viewArray(selection, "", 0));

    String[] insertion = {"b", "c", "a", "z", "e", "A"};
    ArraySorter.insertionSort(insertion, insertion.length);
    System.out.println("Insertion Sorted: " + ArraySorter.viewArray(insertion, "", 0));

  }
}
