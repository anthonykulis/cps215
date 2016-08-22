package data_structures.bags;


public final class ArrayBag<T> implements BagInterface<T> {
  private final T[] _bag;
  private int _entryCount;
  private static final int DEFAULT_CAPACITY = 25;


  private static final int MAX_CAPACITY = 1000;


  public ArrayBag(){
    this(CAPACITY);
  }

  public ArrayBag(int capacity){
    @SuppressWarnings("unchecked");
    T[] bag = (T[])new Object[capacity];
    this._bag = bag;
    this._entryCount = 0;
  }

  public int getCurrentSize(){

  }

  public boolean isEmpty(){


  }

  public boolean add(T item){

  }

  public T remove(T item){

  }

  public void clear(){

  }

  public boolean contains(T item){

  }

  public T[] toArray(){

  }

  private boolean _isArrayFull(){

  }


  // Tests
  public static void main(String args[]){
    
  }
}
