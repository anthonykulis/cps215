package assignments.one;

public final class ArrayBag<T> implements BagInterface<T> {
  private  T[] _bag;
  private int _count;
  private static final int DEFAULT_CAPACITY = 25;
  private int _capacity;
  private static final int MAX_CAPACITY = 1000;

  public ArrayBag(){
    this(DEFAULT_CAPACITY);
  }

  /*
   Instructor notes:
   You do not need this. We are auto-growing the array and
   hence encapsulating this logic. Also, if the user sends 1001,
   we break our rules. For next time, this could qualify as a refactor bonus.
  */
  public ArrayBag(int capacity){
    // @SuppressWarnings("unchecked");
    this._buildInternalArray(capacity);
    this._capacity = capacity;
  }

  public int getCurrentSize(){
    return this._count;
  }

  public boolean isEmpty(){
    return this._count == 0;
  }

  public boolean add(T item){

    /*
      Instructor notes:

      You are exiting the application upon reaching the max capacity.
      What if a developer wanted to max it out? eg:

      while(myBag.add("a"));

      Now you just ended the program on them. Refactor and ALWAYS use the
      return type you promised.
    */
    if(_bag.length == MAX_CAPACITY){
      System.out.println("Bag reached max capacity of 1000.");
      System.exit(0);
    }
    if(this._isArrayFull()){
      this._allocateMoreSpace();
    }

    this._bag[this._count] = item;

    this._count++;

    return true;
  }

  public T remove(){
    if(this.isEmpty()){
      return null;
    }

    T item = this._bag[this._count - 1];
    this._bag[_count] = null;
    this._count--;

    return item;
  }

  public T remove(T item){
    int index = contains(item);
    if(index == -1){
      return null;
    }

    T foundItem = this._bag[index];
    this._bag[index] = null;
    this._count--;

    T[] removeNull = (T[]) new Object[_bag.length];

    /*
      Instructor notes:
      This looks good.
    */
    System.arraycopy(_bag, 0, removeNull, 0, index);

    /*
      Instructor notes:
      The above copies the cells from 0 to index. This copies the
      cells from index to something more than we need. You literally just
      copied the bag from one array to another as it is.

      Also, if the cell removed was the last one in the array, and that
      array is 1000 long, you just did 1000 operations when you needed 0.

      You will need to rework the indexing.
    */
    System.arraycopy(_bag, index, removeNull, index, _bag.length);
    this._bag = removeNull;

    return foundItem;
  }

  public void clear(){
    this._buildInternalArray(this._capacity);
  }

  public int contains(T item){
    if(this.isEmpty()){
      return -1;
    }

    int index = 0;
    while (!item.equals(this._bag[index]) && index < _count){
      index++;
    }

    // if the index reached count, that means we never found the item
    if(index == _count){
      return -1;
    }
    return index;
  }

  public T[] toArray(){
    return this._bag.clone();
  }

  private boolean _isArrayFull(){
    return this._capacity == this._count;
  }

  private void _buildInternalArray(int capacity){
    this._bag = (T[])new Object[capacity];
    this._count = 0;
  }

  /*
    Instructor notes:
    This looks good.
  */
  private void _allocateMoreSpace(){
      T[] bigBag = (T[])new Object[_capacity + DEFAULT_CAPACITY];
      System.arraycopy(_bag, 0, bigBag, 0, _bag.length);
      this._bag = bigBag;
      this._capacity = _bag.length;

  }


public static void main(String args[]){

    ArrayBag<String> hugeCap = new ArrayBag<>(1000);
    Object[] hc = hugeCap.toArray();
    if(hc.length != 1000){
      throw new RuntimeException("I made a bag with a capacity of 1000 and its " + hc.length);
    }

    ArrayBag<String> ab = new ArrayBag<>();
    if(ab.getCurrentSize() != 0){
      throw new RuntimeException("Just initialized and it has size");
    }

    if(!ab.isEmpty()){
      throw new RuntimeException("Just initialized and its not empty");
    }

    if(!ab.add(new String("yolo"))){
      throw new RuntimeException("I tried to add to an empty bag and got rejected");
    }

    String yolo = ab.remove();
    if(yolo == null){
      throw new RuntimeException("Got returned null when I expected yolo");
    }

    if(!yolo.equals("yolo")){
      throw new RuntimeException("expected yolo got " + yolo);
    }

    ab.add(yolo);

    yolo = ab.remove(yolo);
    if(yolo == null){
      throw new RuntimeException("Added yolo back and tried to find it. That didn't work");
    }

    if(!yolo.equals("yolo")){
      throw new RuntimeException("That yolo i got... not yolo but " + yolo);
    }

    if(ab.contains(yolo) > -1){
      throw new RuntimeException("Went to see if yolo still was there. It was. But I removed it");
    }

    if(ab.getCurrentSize() != 0){
      throw new RuntimeException("I removed yolo, got yolo back, know its not in the bag, got a size not 0");
    }

    ab.add(yolo);

    if(ab.contains(yolo) == -1){
      throw new RuntimeException("I added yolo back, looked for it in contains, couldnt find it");
    }

    Object[] yolos = ab.toArray();
    if(yolos.length != 25){
      throw new RuntimeException("I expected an array length of 25 and got " + yolos.length);
    }

    ab.clear();

    if(!ab.isEmpty()){
      throw new RuntimeException("I just cleared but the array is not empty");
    }

    System.out.println("Woot. Completed Arraybag tests");

    /*
      Instructor notes on testing
      You added two pieces of fucntionality.

      1) Dynamically grow the array to 1000
        - if you are returning the proper type, which you have to, you can
        while loop and add until it stops you. Then check to see if you have MAX_CAPACITY.
        That proves add and the limiter both work.

        The easier way to handle this...

          while(nb.add("a") != null);
          if(nb.getCurrentSize() > 1000){
            throw new RuntimeException("...")
          }

        That covers both the auto-grow and the 1000 limiter.

      2) Remove null cells upon remove.
        - easist way to handle this is from an empty array.
        - add("myString") 2x or something similar.
        - we remove the first instance of "myString" on remove.
        - do toArray() and you should have only one item in cell 0.
          - if you want a sanity check, add("dog"), add("cat"), remove dog, you
            should only have cat.

            nb.add("dog")
            nb.add("cat")
            nb.remove("dog")
            Object[] arr = nb.toArray()
            if(arr[0] == null || !arr[0].equals("cat")){
              throw new RuntimeException("...");
            }
            
      You need to test both of those
    */

   ArrayBag<String> tBag = new ArrayBag<>();
   for (int i = 0; i<1200; i++){
      tBag.add("BOB");
   }




  }
}
