package sorting;

public class ArraySorterRecursive {

  public static <T extends Comparable<? super T>> void selectionSort(T[] unsorted, int n){
    if(n == unsorted.length){ return; }
    int indexOfSmallest = findIndexOfSmallest(unsorted, n, n+1);
    swap(unsorted, indexOfSmallest, n);
    selectionSort(unsorted, n+1);
  }

  public static <T extends Comparable<?super T>> void insertionSort(T[] unsorted, int n){
    if(n == unsorted.length) return;
    int found = -1;
    found = findIndexOfNextSmallerItem(unsorted, unsorted[n], n + 1);
    if(found > n) insertInOrder(unsorted, unsorted[found], found - 1);
    insertionSort(unsorted, n+1);
  }

  private static <T extends Comparable<? super T>> int findIndexOfSmallest(T[] a, int index, int position){
     if(position == a.length){
        return index;
     }
     if(a[position].compareTo(a[index]) < 0){
        index = position;
     }
     return findIndexOfSmallest(a, index, position+1);
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
    if(pos == a.length){ return contents; }
    contents += (contents.length() > 0 ? ", " : "") + a[pos++];
    return ArraySorterRecursive.viewArray(a, contents, pos);
  }

  public static void main(String[] args){
    String[] selection = {"b", "c", "a", "z", "e", "A"};
    System.out.println("Unsorted Array: " + ArraySorterRecursive.viewArray(selection, "", 0));
    ArraySorterRecursive.selectionSort(selection, selection.length);
    System.out.println("Selection Sorted: " + ArraySorterRecursive.viewArray(selection, "", 0));

    String[] insertion = {"b", "c", "a", "z", "e", "A"};
    ArraySorterRecursive.insertionSort(insertion, 0);
    System.out.println("Insertion Sorted: " + ArraySorterRecursive.viewArray(insertion, "", 0));
  }
}
