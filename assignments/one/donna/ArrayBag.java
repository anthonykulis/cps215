/*
  Instructor notes:
  I will mark each section that needs work in a similar fashion
  to this.

  For this section, you will need to import the Node class
*/

package data_structures.bags;

public final class ArrayBag<T> implements BagInterface<T>{
   // the bag itself
   private T[] _bag;

   // the count of items in the bag
   private int _countOfItems;

   // the default capacity - for the overloaded constructor
   private static final int DEFAULT_CAPACITY = 25;

   /*
   Instructor Notes:
   There is a member variable you will need to
   decide if the bag is at its max capacity and
   we can no longer automatically grow its size
   */

   private int _capacity;

   public ArrayBag(){
      this(DEFAULT_CAPACITY);
   }

   /*
   Instructor Notes:
   Since we are autogrowing, we will not need this.
   */
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

      /*
      Instructor notes:
      There is no method _canAddMore(). Don't call it
      unless it exists. If you need to fill out the logic
      earlier, at least define the "prototype" of it such
      that the code will compile. Since you are expecting
      a boolean, you can simply return true until youre ready
      for the logic to be implmented.

      Also, I gave a method named _allocateMoreSpace(). Compare
      the two names. _canAddMore() suggests you are checking to
      see if the bag can grow further. That may be the case,
      but you technically do not need a method to check that,
      and since this stopping of autogrowing is a cavaet I added
      for the fun of it, there really is no need to have a method
      to encapsulate that logic. You can simply check your current
      capacity against the maximum.

      Finally, if we have not reached our maximum capacity, the bag
      is currently full, and we are able to allocate more space,
      then we could continue. This means you know what your check should
      be. My suggestion is to move the maximum capacity check into the
      allocateMoreSpace() method and return a boolean on if the allocation
      worked. Hope that hint helps.
      */
      if(!this._canAddMore()){
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

      /*
        Instructor notes:
        Before you ever write a method that is non-business logic,
        check the Java API. Current version is 8.
      */
      arrayCopy(this._bag, this._bag[index]);

      return foundItem;
   }

   /*
   Instructor notes:
   Superfluous. Throw this out.
   */
   public T[] arrayCopy(T[] _bag, int index){
      T[] oldArray = T[] _bag;
      T[] tempArray;
      //copy left side
      for(int count = 0;count < index; count++){
         tempArray[count] = oldArray[count];
      }
      //copy right side
      for(int count2 = index; count2<oldArray.length;count2++){
         tempArray[count2] = oldArray[count2];
      }
      return tempArray;
   }


   public void clear(){
      this._buildInternalArray(this.capacity);
   }

   public int contains(T item){
      if(this.isEmpty()){
         return -1;
      }

      int index = 0;
      while(index < _count && !item.equals(this._bag[index])){
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

   /*
   Instructor notes:
   Be sure to write the tests. You will have to check that the bag
   grows automatically until the maximum capacity is reached, and you
   will have to test to make sure when you remove an element from the bag
   by name, that array cell is not null.
   */

   //Tests
   public static void main(String[] args){

      ArrayBag<String> ab = new ArrayBag<>();
      if(ab.getCurrentSize() != 0){
         throw new RuntimeException("Just initialized and it has size.");
      }

      if(!ab.isEmpty()){
         throw new RuntimeException("Just initialized and its not empty.");
      }

      String yolo = ab.remove();
      if(yolo == null){
         throw new RuntimeException("Got returned null when I expected yolo.");
      }

      if(!yolo.equals("yolo")){
         throw new RuntimeException("Expected yolo got " + yolo);
      }

      ab.add(yolo);

      yolo = ab.remove(yolo);
      if(yolo == null){
         throw new RuntimeException("Added yolo back and tried to find it. That didn't work.");
      }

      if(!yolo.equals("yolo")){
         throw new RuntimeException("That yolo I got ... not yolo but " + yolo);
      }

      if(ab.getCurrentSize() != 0){
         throw new RuntimeException("I removed yolo, got yolo back, know it's not in the bag, got a size not 0 but " + ab.getCurrentSize());
      }

      // now add 3 different ones, remove the second, then hope we only have 2 in sequence after the remove
      String dog = "Dog";
      String cat = "Cat";
      ab.add(dog);
      ab.add(cat);
      ab.add(dog);
      ab.remove(cat);

      if(ab.getCurrentSize() != 2){
         throw new RuntimeException("I had three, removed the middle one, have length 3.");
      }
      Object[] dogs = ab.toArray();

      if(!(dogs[0].equals("Dog") && dogs[1].equals("Dog"))){
         throw new RuntimeException("I removed the cat. We should have only dogs. We have something besides dogs.");
      }

      if(ab.contains(yolo) > -1){
         throw new RuntimeException("Went to see if yolo still was there. It was. But I removed it.");
      }

      ab.add(yolo);

      if(ab.contains(yolo) == -1){
         throw new RuntimeException("I added yolo back, looked for it in contains, couldn't find it.");
      }

      Object[] yolos = ab.toArray();
      if(yolos.length != 25){
         throw new RuntimeException("I expected an array length of 25 and got " + yolos.length);
      }

      ab.clear();

      if(!ab.isEmpty()){
         throw new RuntimeException("I just cleared but the array is not empty.");
      }

      System.out.println("Woot. Completed Arraybag tests");
   }
}
