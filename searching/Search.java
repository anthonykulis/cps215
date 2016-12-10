package searching;

public class Search {

  public static <T extends Comparable<? super T>> int linear(T[] a, T value){
    return linear(a, value, 0);
  }

  private static <T extends Comparable<? super T>> int linear(T[] a, T value, int pos){
    // ran out of room
    if(pos >= a.length) return -1;

    // found it
    if(a[pos].compareTo(value) == 0) return pos;

    // iterate
    return linear(a, value, pos + 1);
  }

  public static <T extends Comparable<? super T>> int binary(T[] a, T value){
    return binary(a, value, 0, a.length - 1);
  }

  private static <T extends Comparable<? super T>> int binary(T[] a, T value, int leftIndex, int rightIndex){

    // get mid
    int mid = (leftIndex + rightIndex) / 2;

    // compare to see if value is bigger or smaller than current
    int compared = value.compareTo(a[mid]);

    // found it
    if(compared == 0) return mid;

    // mid was last test (array bounds), cant find it
    if(mid == 0 || mid == a.length - 1) return -1;

    // value is still smaller
    if(compared < 0) return binary(a, value, leftIndex, mid);

    // value is still bigger
    return binary(a, value, mid + 1, rightIndex);
  }


  public static void main(String[] args){
    Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    System.out.println("linear find 8: " + Search.linear(nums, 8));
    System.out.println("binary find 8: " + Search.binary(nums, 8));
  }
}
