package assignments.one;

/*
  Instructor notes:
  Please keep the file/class names as I requested. I run automated tests (typically)
  and do not want to spend time hand testing yours because you wanted to change it.
  If you want to put your name on it, use comments in the header.

  Eg:

  Author: Wyatt
*/
public final class ArrayBag<T> implements BagInterface<T> {

   private static final int DEFAULT_CAPACITY = 25;
   private static final int MAX_CAPACITY = 1000;

   private  T[] _bag;

   private int _count;
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

   /*
    Instructor notes:

    I know we discussed the return type on _allocateMoreSpace() and you
    suggested we go with void. If you are going with void, and we do not
    have any way to allocate more space (eg your checks), you need to cover
    that. You ALWAYS return true and you will suffer an array out of bounds
    exception with this code.

    You need to handle that.

    A suggestion is to return a boolean from _allocateMoreSpace() so that
    if we are max capacity, _allocateMoreSpace() fails, and you can simply
    return false from that condition.
   */
   public boolean add(T item){
      if(this._isArrayFull() && this._capacity <= MAX_CAPACITY){
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

      /*
        Instructor Notes:
        Wyatt, this block is way off. Let me try to help you with this logic.

        First off, we are copying the left and right sides of an array who
        has a null value in it because we removed that item at the index.
        No where in this method do you actually remove that item. So you broke my
        original code.

        Second, when you are coping the left side of the original array into a
        new array, you need to copy those item. Imagine if we had an array with
        10 elements filled and we remove the 9th element. This means our left
        side is 8 elements long and our right side is 1 element long, yes?

        Using my example with your code, the left hand side copy takes all the elements from
        the original array starting at 0 all the way 1 and puts them in starting at
        9th cell.

        For the right hand side, you copy the 10th element from the original, put it
        in the new array starting at 1 more than our array can hold, and if that worked
        somehow, you would copy the length of the bag into it.

        This means that for our example, we would have something like
        [NULL][NULL][NULL][NULL][NULL][NULL][NULL][NULL][ITEM][NULL]...[NULL]

        When we need
        [ITEM][ITEM][ITEM][ITEM][ITEM][ITEM][ITEM][ITEM][ITEM][NULL]...[NULL]

        If you are finding this manipulation of arrays difficult, draw your logic out
        on paper the same way I did on the board.
      */
      T foundItem = this._bag[index];
      System.arraycopy(this._bag, 0, this._bag, index, this._bag.length - index);
      System.arraycopy(this._bag, index + 1, this._bag, this._bag.length, this._bag.length - index - 1 );

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
    This creates a new array in scope only. Once this method exits,
    we are still at the original size of 25.
   */
   private void _allocateMoreSpace() {

      T[] newArray = (T[]) new Object[_bag.length + DEFAULT_CAPACITY];
      System.arraycopy(this._bag, 0, newArray, 0, this._bag.length);


   }

  // Tests
   public static void main(String args[]){

     /*
      Instructor notes:
      I dont know why you tossed all my tests. Those tests guaranteed the previous
      code worked. So when you add to the class, you run those tests again. If they fail,
      your code is not correct. I brought this concept up in class. The idea is for
      scalabilty. As you add to the class, and if this code has already been released, we
      are promising to our customers that it works *this* way. When you change the tests,
      you change the promise, which could break our clients production applications,
      assuming we had any clients.

      Now adding to the tests is totally reasonable. Since you are adding two pieces of
      functionality (auto-grow w/ MAX_CAPACITY and removing null pointers in the array),
      you need to test for only those two.

      The simplest way to handle those tests:

      // with no infinite loop sanity check
      while(bag.add("dog"));
      if(bag.getCurrentSize() > 1000){
        throw new RuntimeException("...");
      }

      bag.clear();
      bag.add("dog");
      bad.add("cat");
      bag.remove("dog");
      Object[] array = bag.toArray();
      if(array[0] == null || !array[0].equals("cat")){
        throw new RuntimeException("...");
      }
     */

      ArrayBag<Object> bag1 = new ArrayBag<>(25);

      Object[] cloneBag = bag1.toArray();

      if(cloneBag.length != 25) {
         throw new RuntimeException("The array length should have been 20, not "
                  + cloneBag.length);
      }

      ArrayBag<Object> testBag2 = new ArrayBag<>();

      if (testBag2.getCurrentSize() != 0) {
         throw new RuntimeException("This bag should be size 0, nothing else");
      }
      else if (!testBag2.isEmpty())   {
         throw new RuntimeException("There's already something in this!");
      }

      Object newItem = "New coat";

      if(!testBag2.isEmpty())    {
         throw new RuntimeException("Um, what happened to the item? It was in here.");
      }

      if (!testBag2.add(newItem)){
         throw new RuntimeException("Just tried to add an empty bag, but it got rejected?");
      }

      newItem = testBag2.remove();

      if(newItem == null)   {
         throw new RuntimeException("I expected the new item, not null.");
      }

      if(!newItem.equals("New coat"))   {
         throw new RuntimeException("I expected a new coat, not " + newItem);
      }

      testBag2.add(newItem);

      newItem = testBag2.remove(newItem);
      if(newItem == null)  {
         throw new RuntimeException("I just added that new item. I went to find it, and it's gone.");
      }

      if (!newItem.equals("New coat")) {
         throw new RuntimeException("That item I got wasn't a new coat. It was: " + newItem);
      }

      if (testBag2.contains(newItem) > -1) {
         throw new RuntimeException("That item you had. Yeah, I removed it!");
      }

      if (testBag2.getCurrentSize() != 0)  {
         throw new RuntimeException("I know the item is not in the bag. So why is the size not 0?");
      }

      testBag2.add(newItem);

      if (testBag2.contains(newItem) == -1)    {
         throw new RuntimeException("I just added the item. And it's gone? The hell??");
      }

      Object[] cloneBag2 = testBag2.toArray();
      if(cloneBag2.length != 25)   {
         throw new RuntimeException("Shouldn't the length be 25 instead of " + cloneBag2.length);
      }

      testBag2.clear();

      if(!testBag2.isEmpty())  {
         throw new RuntimeException("I just cleared this. But it's not cleared. Ooooh, mystery.");
      }

      System.out.println("If you are reading this, it means that the test has been completed.");



   }
}
