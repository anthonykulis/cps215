public class Example {
  public <T> void displayArray(T[] someArray){
    for(T item : someArray){
      System.out.print(item + " ");
    }
    System.out.println();
  }

  public static void main(String[] args){
    Example e = new Example();

    String[] things = {"apple", "banana", "carrot", "dandelion"};
    e.displayArray(things);

    Character[] letters = {'a', 'b', 'c', 'd'};
    e.displayArray(letters);
  }
}
