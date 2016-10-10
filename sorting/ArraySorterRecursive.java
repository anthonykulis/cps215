package sorting;

public class ArraySorterRecursive {

  public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int n){
    for(int current = 0; current < n; current++){
      int smallest = findIndexOfSmallest(unsorted, current, n - 1);
      swap(unsorted, current, smallest);
    }
  }

  public static <T extends Comparable<?super T>> void insertionSort(T[] unsorted, int n){
    if(n == unsorted.length) return;
    int found = -1;
    found = findIndexOfNextSmallerItem(unsorted, unsorted[n], n + 1);
    if(found > n) insertInOrder(unsorted, unsorted[found], found - 1);
    insertionSort(unsorted, n+1);
  }

  private static <T extends Comparable<? super T>> int findIndexOfSmallest(T[] a, int start, int end){
    T min = a[start];
    int minIndex = start;
    for(int i = start + 1; i <= end; i++){
      if(a[i].compareTo(min) < 0){
        min = a[i];
        minIndex = i;
      }
    }
    return minIndex;
  }

  private static void swap(Object[] a, int i, int j){
    Object t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  private static <T extends Comparable<? super T>> int findIndexOfNextSmallerItem(T[] a, T compare, int position){
    if(position == a.length) return -1;
    if(a[position].compareTo(compare) < 0) return position;
    return findIndexOfNextSmallerItem(a, compare, position++);
  }

  private static <T extends Comparable<? super T>> void insertInOrder(T[] a, T item, int s){
    if(a[s].compareTo(item) < 0){
      a[s] = item;
      return;
    }
    insertInOrder(a, item, s--);
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