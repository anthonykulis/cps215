package data_structures.bags;


public final class ArrayBag<T> implements BagInterface<T> {
  // the bag itself
  private T[] _bag;

  // the count of items in the bag
  private int _countOfItems;

  // the default capacity - for the overloaded constructor
  private static final int DEFAULT_CAPACITY = 25;

  private int _capacity;

  public ArrayBag(){
    this(DEFAULT_CAPACITY);
  }

  public ArrayBag(int capacity){
    this._buildInternalArray(capacity);
  }

  public int getCurrentSize(){
    return this._countOfItems;
  }

  public int isEmpty(){
    return this._countOfItems == 0;
  }

  public boolean add(T item){

    if(!this._canIAddMore()){
      return false;
    }

    this._bag[this._countOfItems] = item;
    this._countOfItems++;
    return true;
  }

  public T remove(){

    if(this.isEmpty()){
      return null;
    }

    T last = this._bag[this._countOfItems];
    this._bag[this._countOfItems] = null;
    this._countOfItems--;

    return last;
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
    this._buildInternalArray(this.capacity);
  }

  public int contains(T item){
    if(this.isEmpty()){
      return -1;
    }

    int index = 0;
    while (index < _count && !item.equals(this._bag[index])){
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
    return this._currentCapacity == this._count;
  }

  private void _buildInternalArray(int capacity){
    this._bag = (T[])new Object[capacity];
    this._count = 0;
    this._capacity = capacity;
  }


  // Tests
  public static void main(String args[]){

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

    if(ab.getCurrentSize() != 0){
      throw new RuntimeException("I removed yolo, got yolo back, know its not in the bag, got a size not 0 but " + ab.getCurrentSize());
    }

    // now add 3 different ones, remove the second, then hope we only have 2 in sequence after the remove
    String dog = "Dog";
    String cat = "Cat";
    ab.add(dog);
    ab.add(cat);
    ab.add(dog);
    ab.remove(cat);

    if(ab.getCurrentSize() != 2){
      throw new RuntimeException("I had three, removed the middle one, have length 3");
    }
    Object[] dogs = ab.toArray();

    if(!(dogs[0].equals("Dog") && dogs[1].equals("Dog"))){
      throw new RuntimeException("I removed the cat. We should have only dogs. We have something besides dogs");
    }


    if(ab.contains(yolo) > -1){
      throw new RuntimeException("Went to see if yolo still was there. It was. But I removed it");
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

  }
}
