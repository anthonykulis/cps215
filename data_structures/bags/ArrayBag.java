package data_structures.bags;


public final class ArrayBag<T> implements BagInterface<T> {
  private final T[] _bag;
  private int _count;
  private static final int DEFAULT_CAPACITY = 25;
  private int _capacity;




  public ArrayBag(){
    this(CAPACITY);
  }

  public ArrayBag(int capacity){
    @SuppressWarnings("unchecked");
    T[] bag = (T[])new Object[capacity];
    this._bag = bag;
    this _count = 0;
    this._capacity = capacity;
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
    int index=contains(item);
    if (index==-1){
      return null;
    }
    T item = this._bag[index];
    this._bag[index] = null;
    return item;
  }

  public void clear(){
    this(this._capacity);
  }

  public int contains(T item){
    if(this.isEmpty()){
      return -1;
    }
    while (!item.equals(this._bag[index]) && index < _count)
    {
      index++;
    }

    // if the index reached count, that means we never found the item
    if (index == _count) {
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


  // Tests
  public static void main(String args[]){

  }
}
