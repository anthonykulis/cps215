package sorting;

public class ArraySorter {

  public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int n){
    for(int current = 0; current < n; current++){
      int smallest = findIndexOfSmallest(unsorted, current, n - 1);
      swap(unsorted, current, smallest);
    }
  }

  public static <T extends Comparable<? super T>> void insertionSort(T[] unsorted, int n){
    int found = -1;
    for(int i = 0; i < n; i++){
      found = findIndexOfNextSmallerItem(unsorted, i);
      if(found > i){
        insertInOrder(unsorted, found);
      }
    }
  }

  public static <T extends Comparable<? super T>> void mergeSort(T[] unsorted, int n){

    // jic they dont want it all sorted
    T[] cacheCopy = unsorted;

    // be graceful for this sandbox
    if(n != unsorted.length && n < unsorted.length){
      T[] t = (T[])new Comparable[n];
      System.arraycopy(unsorted, 0, t, 0, n);
      unsorted = t;
    } else {
      n = unsorted.length;
    }

    // call a helper with the new array to return
    T[] consumable = (T[])new Comparable[n];
    mergeSort(unsorted, consumable, 0, n - 1);

    for(int i = 0; i < n; i++){
      cacheCopy[i] = unsorted[i];
    }

  }

  private static <T extends Comparable<? super T>> void mergeSort(T[] unsorted, T[] consumable, int first, int last){

    if(first < last){

      int mid = first + (last-first)/2;

      mergeSort(unsorted, consumable, first, mid );

      mergeSort(unsorted, consumable, mid + 1, last);

      merge(unsorted, consumable, first, mid, last);

    }
  }

  private static <T extends Comparable<? super T>> void merge(T[] unsorted, T[] consumable, int first, int middle, int last){

    System.arraycopy(unsorted, first, consumable, first, last - first + 1);

    int leftFront = first;
    int rightFront = middle + 1;
    int i = first;

    while(leftFront <= middle && rightFront <= last){
      if(consumable[leftFront].compareTo(consumable[rightFront]) <= 0){
        unsorted[i++] = consumable[leftFront++];
      } else {
        unsorted[i++] = consumable[rightFront++];
      }
    }

    while(leftFront <= middle){
      unsorted[i++] = consumable[leftFront++];
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
    ArraySorter.selectionSort(selection, selection.length);
    System.out.println("Selection Sorted: " + ArraySorter.viewArray(selection, "", 0));

    String[] insertion = {"b", "c", "a", "z", "e", "A"};
    ArraySorter.insertionSort(insertion, insertion.length);
    System.out.println("Insertion Sorted: " + ArraySorter.viewArray(insertion, "", 0));

    String[] merge = {"b", "c", "a", "z", "e", "A"};
    ArraySorter.mergeSort(merge, 7);
    System.out.println("Merge Sorted: " + ArraySorter.viewArray(merge, "", 0));
  }
}
