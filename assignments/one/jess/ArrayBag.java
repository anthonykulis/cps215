package assignments.one;


public final class ArrayBag<T> implements BagInterface<T> {
   private  T[] _bag;
   private int _count;
   private static final int DEFAULT_CAPACITY = 25;
   private static final int MAX_CAPACITY = 1000;
   private int _capacity;

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
      if(this._isArrayFull() && this._capacity <= MAX_CAPACITY){

        /*
          Instructor notes:
          Your return type of _allocateMoreMemory() is type T[].
          You dont catch and assign that. This breaks your code.
          For the 25pts you will need to assign that correctly.
        */
         this._allocateMoreMemory();


         /*
          Instructor notes:
          Dont leave commented logic in your code. If for some reason
          you have a tought process you want to put on hold, that is
          where git comes into play. Long story short, git will allow
          us to "branch" our ideas off the main source file. Once we complete
          that idea, we then "merge" our idea back into the main source. FYI,
          I will make you refactor these out on subsequent submissions (pet peive lol!!!!)

         */
         //return false;
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

      /*
        Instructor notes:
        Your copies look perfect but can be cleaned up.
        Your `this._count--;` should be brought up to before this
        copy. Then in your second arraycopy you could give it a length
        of this.getCurrentSize() - index and be perfect readable.
        This normally would qualify for a refactor.
      */
      this._bag = (T[]) new Object[_bag.length];
      System.arraycopy(_bag, 0, this._bag, 0, index);
      System.arraycopy(_bag, index+1, this._bag, index, _bag.length-index-1);

      this._count--;

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
    There is nothing wrong with this, but I would call for a refactor on
    this. The reason being is the method name starts with _allocate and not _get.
    Allocate hints that the developer using your library doesn't need to manage
    the memory. I would return a boolean. Then where you check to see if it full
    in your add method, I would move that into this method. That way if it is full,
    you return false.
   */
   private T[] _allocateMoreMemory(){
      this._bag = (T[]) new Object[_bag.length + DEFAULT_CAPACITY];
      System.arraycopy(_bag, 0, this._bag, 0, _bag.length);
      return this._bag;
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

      /*
        Instructor notes:
        The easier way to handle this...

          while(nb.add("a") != null);
          if(nb.getCurrentSize() > 1000){
            throw new RuntimeException("...")
          }

        That covers both the auto-grow and the 1000 limiter.

        You also need to test for the null removal on remove(T item). But since
        that code is correct, I would only call for a refactor on that. To test it,
        start with an empty bag. If you want a sanity check to go along with it (for you not me),

          nb.add("dog")
          nb.add("cat")
          nb.remove("dog")
          Object[] arr = nb.toArray()
          if(arr[0] == null || !arr[0].equals("cat")){
            throw new RuntimeException("...");
          }
      */
      ArrayBag<String> nb = new ArrayBag<>();

      nb.add("a");
      nb.add("b");
      nb.add("c");
      nb.add("d");
      nb.add("e");
      nb.add("f");
      nb.add("g");
      nb.add("h");
      nb.add("i");
      nb.add("j");
      nb.add("k");
      nb.add("l");
      nb.add("m");
      nb.add("n");
      nb.add("o");
      nb.add("p");
      nb.add("q");
      nb.add("r");
      nb.add("s");
      nb.add("t");
      nb.add("u");
      nb.add("v");
      nb.add("w");
      nb.add("x");
      nb.add("y");

      if(!nb.add(new String("z"))){
         throw new RuntimeException("I tried to add a 26th element but got rejected");
      }





      System.out.println("Woot. Completed Arraybag tests");

   }
}
