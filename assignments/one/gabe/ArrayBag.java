package assignments.one;

public class ArrayBag<T> implements BagInterface<T> {
   private  T[] _bag;
   private int _count;
   private static final int DEFAULT_CAPACITY = 25;
   private int _capacity;
   private static final int _maxCapacity=1000;

   public ArrayBag(){
      this._buildInternalArray();
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
        Spend time putting spaces in your code. The point of
        writing code (besides the obvious) is to allow for others
        to come into your work later and modify or update it.
        You want to make it readable.
      */
      if(this._isArrayFull()&&!_allocateMoreSpace()){
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
      this._count--;

      T[] tempBag=(T[])new Object[this._capacity];
      System.arraycopy(this._bag, 0, tempBag, 0, this._capacity);
      System.arraycopy(this._bag, index+1, tempBag, index, this.getCurrentSize()-index);
      this._bag=tempBag;



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

   private void _buildInternalArray(){
      this._bag = (T[])new Object[DEFAULT_CAPACITY];
      this._count = 0;
   }

   /*
    Instructor notes:
    Again, spaces plese. Use them! Make your code
    easily readable for the next guy. The goal is
    to "wow" the next reader. In the long run it pays
    off because you will have a boss looking at it and
    she might say "hmm. Is Gabe lazy? Maybe I dont want to give
    him that raise, or at least not as much as I though". No point
    in risking that. Remember, this is published work, others
    will read it. Give them the best impression.
   */
   public boolean _allocateMoreSpace(){
      if(_capacity==_maxCapacity){
         return false;
      }
      else{
         T[] tempBag=(T[])new Object[_capacity+DEFAULT_CAPACITY];
         System.arraycopy(_bag, 0, tempBag, 0, _capacity);
         this._capacity+=DEFAULT_CAPACITY;
         this._bag=tempBag;
         return true;
      }
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

      ab.clear();

      if(!ab.isEmpty()){
         throw new RuntimeException("I just cleared but the array is not empty");
      }

      String test="test";
      int i;
      while(ab.add(test)&& i<ab._maxCapacity+5){
         i++;
      }

      yolos=ab.toArray();
      if(yolos.length>ab._maxCapacity){
         throw new RuntimeException("Array was filled more than max capacity");
      }

      if(ab.getCurrentSize()!=ab._maxCapacity){
         throw new RuntimeException("Array does not have max capacity");
      }

      ab.clear();
      String one="one";
      String two="two";
      String three="three";

      ab.add(one);
      ab.add(two);
      ab.add(three);
      ab.remove(three);

      if(ab.getCurrentSize()!=2){
         throw new RuntimeException("Removed one from three, should have 2");
      }



      System.out.println("Woot. Completed Arraybag tests");

   }
}
