public class Find {
  public static <T extends Comparable<T>> T arrayMinimum(T[] array){
    T min = array[0];
    for(T item : array){
      if(item.compareTo(min) < 0) min = item;
    }
    return min;
  }

  public static void main(String[] args){
    String[] things = {"banana", "cat", "horse", "aardark", "zebra"};

    System.out.println(Find.arrayMinimum(things));
  }
}
