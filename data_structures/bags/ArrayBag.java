package data_structures.bags;


public final class ArrayBag<T> implements BagInterface<T> {
  private final T[] _bag;
  private int _count;
  private static final int DEFAULT_CAPACITY = 25;



  public ArrayBag(){
    this(CAPACITY);
  }

  public ArrayBag(int capacity){
    @SuppressWarnings("unchecked");
    T[] bag = (T[])new Object[capacity];
    this._bag = bag;
    this _count = 0;
  }

  public int getCurrentSize(){
    return this _count;
  }

  public boolean isEmpty(){
    return  (_count!=0);

  }

  public boolean add(T item){
    if(this._isArrayFull()){
      return false;
    }
    this._bag[_count] = item;
    _count++;
    return true;
  }

  public T remove(){
    if(this.isEmpty()){
      return null;
    }
    T item = this._bag[_count];
    this._bag[_count] = null;
    _count--;
    return item;
  }

  public T remove(T item){
    int index = 0;

    if(this.isEmpty()){
      return null;
    }
    while (!item.equals(this._bag[index]) && index < _count)
    {
      index++;
    }

    // if the index reached count, that means we never found the item
    if (index == _count) {
      return null;
    }

    T item = this._bag[index];
    this._bag[index] = null;
    return item;
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
