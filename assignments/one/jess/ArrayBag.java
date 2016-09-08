package assigments.one;


public final class ArrayBag<T> implements BagInterface<T> {
   private  T[] _bag;
   private int _count;
   private static final int DEFAULT_CAPACITY = 25;
   private static final int MAX_CAPACITY = 1000;
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
      if(this._isArrayFull() && this._getMoreMemory()){
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

      T[] _temp = (T[])new Object[this._capacity];
      System.arraycopy(_bag, 0, _temp, 0, index);
      System.arraycopy(_bag, index+1, _temp, index, _bag.length-index-1);

      this._count--;
      this._bag = _temp;

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
    Instructors notes:
    You return true when you cannot "getMoreMemory" and
    return false when you can. This doesn't make sense. If
    there was refactoring available, you'd have to switch this out.
   */
   private boolean _getMoreMemory(){
      if(this._capacity == MAX_CAPACITY){
         return true;
      }

      T[] _temp = (T[])new Object[this._capacity + DEFAULT_CAPACITY];
      System.arraycopy(this._bag, 0, _temp, 0, this._bag.length);
      this._capacity += DEFAULT_CAPACITY;
      this._bag = _temp;

      return false;
   }

   // Tests
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

      int i = 0;
    while(ab.add(yolo) && i < ab.MAX_CAPACITY + 5){ i++; }
    yolos = ab.toArray();
    if(yolos.length > ab.MAX_CAPACITY){
      throw new RuntimeException("I was able to add " + yolos.length + " yolos when max is " + ab.MAX_CAPACITY);
    }

    // I should have 1000 of them
    if(ab.getCurrentSize() != ab.MAX_CAPACITY){
      throw new RuntimeException("I put max cap in, got not max cap. I got "+ ab.getCurrentSize());
    }



      System.out.println("Woot. Completed Arraybag tests");

   }
}
