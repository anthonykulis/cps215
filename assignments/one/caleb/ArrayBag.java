public final class ArrayBag<T> implements BagInterface<T> {
   private  T[] _bag;
   private int _count;
   private static final int DEFAULT_CAPACITY = 25;
   private int _capacity;
   private static final int MAX_CAPACITY = 1000;

   public ArrayBag(){
      this._buildInternalArray(this._capacity);
   }

   public int getCurrentSize(){
      return this._count;
   }

   public boolean isEmpty(){
      return this._count == 0;
   }

   public boolean add(T item){

     /*
      Instructors notes:
      Ok, you check the same thing twice (isArrayFull). You can
      mix that down using boolean algebra or refactoring to a better
      paradigm. While fine for now, I would have you refactor this
      on a normal submission.
     */
      if(this._capacity == MAX_CAPACITY && this._isArrayFull()){
         return false;
      }
      else if(this._isArrayFull()){
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
      System.arraycopy(_bag, 0, removeNull, 0, index);
      System.arraycopy(_bag, index + 1, removeNull, index, this.getCurrentSize() - index);
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

   private void _allocateMoreSpace(){
      T[] bigBag = (T[])new Object[_capacity + DEFAULT_CAPACITY];
      System.arraycopy(_bag, 0, bigBag, 0, _bag.length);
      this._bag = bigBag;
      this._capacity = _bag.length;

   }


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

      ArrayBag<String> arrayBag = new ArrayBag<>();
      while(arrayBag.add("BOB"));
      if (arrayBag.getCurrentSize() > 1000){
         throw new RuntimeException("bag shouldn't exceed 1000 but it did.");}

      arrayBag.clear();
      arrayBag.add("bob");
      arrayBag.add("tim");
      arrayBag.remove("bob");


      Object[] arr = arrayBag.toArray();
      if(arr[0] == null || !arr[0].equals("tim")){
              throw new RuntimeException("...");
            }


      }
   }
