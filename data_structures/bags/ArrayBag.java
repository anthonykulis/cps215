package data_structures.bags;


public final class ArrayBag<T> implements BagInterface<T> {
  private  T[] _bag;
  private int _count;
  private static final int DEFAULT_CAPACITY = 25;
  private int _capacity;

  public ArrayBag(){
    this(DEFAULT_CAPACITY);
  }

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
    if(this._isArrayFull()){
      return false;
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

  // Tests
  public static void main(String args[]){


  }
}
