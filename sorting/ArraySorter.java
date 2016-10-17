package sorting;

public class ArraySorter {

  public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted){
    int n = unsorted.length;
    for(int current = 0; current < n; current++){
      int smallest = findIndexOfSmallest(unsorted, current, n - 1);
      swap(unsorted, current, smallest);
    }
  }

  public static <T extends Comparable<? super T>> void insertionSort(T[] unsorted){
    int n = unsorted.length;
    int found = -1;
    for(int i = 0; i < n; i++){
      found = findIndexOfNextSmallerItem(unsorted, i);
      if(found > i){
        insertInOrder(unsorted, found);
      }
    }
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

  private static <T extends Comparable<? super T>> int findIndexOfNextSmallerItem(T[] a, int start){
    T comparable = a[start++];
    while(start < a.length && a[start].compareTo(comparable) > 0) start++;
    return start != a.length ? start : -1;
  }

  private static <T extends Comparable<? super T>> void insertInOrder(T[] a, int s){
    T cached = a[s--];
    while(s > -1 && a[s].compareTo(cached) > 0) a[s + 1] = a[s--];
    a[++s] = cached;
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
    ArraySorter.selectionSort(selection);
    System.out.println("Selection Sorted: " + ArraySorter.viewArray(selection, "", 0));

    String[] insertion = {"b", "c", "a", "z", "e", "A"};
    ArraySorter.insertionSort(insertion);
    System.out.println("Insertion Sorted: " + ArraySorter.viewArray(insertion, "", 0));

  }
}
