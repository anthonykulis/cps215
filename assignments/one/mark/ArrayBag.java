/* CPS 215 : Assignment One
 * Mark Kiel
*/

package assignments.one;

public final class ArrayBag<T> implements BagInterface<T> {
    private T[] _bag;
    private int _count;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 1000;
    private int _capacity;

    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    /*
      Instructor notes:
      This should really be removed since we are auto-growing the array
      and the developer can not add more than one at a time.
    */
    public ArrayBag(int capacity) {
        this._buildInternalArray(capacity);
        this._capacity = capacity;
    }

    public int getCurrentSize() {
        return this._count;
    }

    public boolean isEmpty() {
        return  (_count == 0);
    }

    public boolean add(T item) {

      /*
        Instructor notes:
        If you set _allocateMoreSpace() return type to a boolean,
        you could have checked for MAX_CAPACITY there. This would have
        trimmed up your code made it more readable.

        Example:
        if(this._isArrayFull() && !this._allocateMoreSpace()){
          reutrn false;
        }

        There is nothing wrong with this though. The above is my opinion
        and doesn't qualify this for refactoring.
      */
        if( this._capacity == MAX_CAPACITY && this._isArrayFull() )
            return false;
        else if(this._isArrayFull()) {
            _allocateMoreSpace();
        }

        this._bag[_count] = item;
        _count++;
        return true;
    }

    public T remove() {
        if(this.isEmpty()) {
        return null;
        }

        T item = this._bag[_count - 1];
        this._bag[_count - 1] = null;
        this._count--;
        return item;
    }

    public T remove(T item) {
        int index = contains(item);
        if (index == -1) {
            return null;
        }

        item = this._bag[index];
        this._bag[index] = null;
        this._count--;

        /*
          Instructor notes:
          Perfect. Good job.
        */
        T[] mergedArray = (T[])new Object[this._capacity];
        System.arraycopy(this._bag, 0, mergedArray, 0, index);
        System.arraycopy(this._bag, index + 1, mergedArray, index, this._count - index);
        this._bag = mergedArray;

        return item;
    }

    public void clear() {
        this._buildInternalArray(this._capacity);
    }

    public int contains(T item) {
        int index = 0;

        if(this.isEmpty()) {
            return -1;
        }
        while ( (index < this._count) && !item.equals(this._bag[index]) ) {
            index++;
        }

        // if the index reached count, that means we never found the item
        if (index == this._count) {
            return -1;
        }
        return index;
    }

    public T[] toArray() {
        return this._bag.clone();
    }

    private boolean _isArrayFull() {
        return this._capacity == this._count;
    }

    private void _buildInternalArray(int capacity) {
       //@SuppressWarnings("unchecked")
        this._bag = (T[])new Object[capacity];
        this._count = 0;
    }

    /*
      Instructor notes:
      Besides my opinion on this methods return type, this couldn't be
      any better or well thought out.
    */
    private void _allocateMoreSpace() {
        // calculte the new capacity based on current capacity
        int newCapacity = (MAX_CAPACITY - getCurrentSize()) >= DEFAULT_CAPACITY ?
            (this._capacity + DEFAULT_CAPACITY) : MAX_CAPACITY;


        T[] newArray = (T[])new Object[newCapacity];
        System.arraycopy(this._bag, 0, newArray, 0, this.getCurrentSize());
        this._bag = newArray;
        this._capacity = newCapacity;
    }

    // Tests
    public static void main(String args[]) {
        ArrayBag<String> hugeCap = new ArrayBag<>(1000);
        Object[] hc = hugeCap.toArray();
        if(hc.length != 1000) {
          throw new RuntimeException("I made a bag with a capacity of 1000 and its " + hc.length);
        }

        ArrayBag<String> ab = new ArrayBag<>();
        if(ab.getCurrentSize() != 0) {
          throw new RuntimeException("Just initialized and it has size");
        }

        if(!ab.isEmpty()) {
          throw new RuntimeException("Just initialized and its not empty");
        }

        if(!ab.add(new String("yolo"))) {
          throw new RuntimeException("I tried to add to an empty bag and got rejected");
        }

        String yolo = ab.remove();
        if(yolo == null) {
          throw new RuntimeException("Got returned null when I expected yolo");
        }

        if(!yolo.equals("yolo")) {
          throw new RuntimeException("expected yolo got " + yolo);
        }

        ab.add(yolo);

        yolo = ab.remove(yolo);
        if(yolo == null) {
          throw new RuntimeException("Added yolo back and tried to find it. That didn't work");
        }

        if(!yolo.equals("yolo")) {
          throw new RuntimeException("That yolo i got... not yolo but " + yolo);
        }

        if(ab.contains(yolo) > -1) {
          throw new RuntimeException("Went to see if yolo still was there. It was. But I removed it");
        }

        ab.add(yolo);

        if(ab.contains(yolo) == -1) {
          throw new RuntimeException("I added yolo back, looked for it in contains, couldnt find it");
        }


        if(ab.contains(yolo) == -1) {
            throw new RuntimeException("I added yolo back, looked for it in contains, couldnt find it");
        }

        Object[] yolos = ab.toArray();
        if(yolos.length != 25) {
          throw new RuntimeException("I expected an array length of 25 and got " + yolos.length);
        }

        ab.clear();

        if(!ab.isEmpty()) {
          throw new RuntimeException("I just cleared but the array is not empty");
        }


        /*
          Instructor notes:

          I appreciate you tagging your tests. Makes it very easy to find. I
          also think you could have reduced this to 2 very easy tests.

          // for auto-grow and max capacity - im omitting infinte loop blocking found on my solution
          while(ab.add("dog") != null);
          if(ab.getCurrentSize() > 1000){
            throw new RuntimeException("...");
          }

          ab.clear();
          ab.add("dog");
          ab.add("cat");
          ab.remove("dog");
          Object[] arr = ab.toArray();
          if(arr[0] == null || !ab[0].equals("cat")){
            throw new RuntimeException("....");
          }

        */
        // -------------------- My tests --------------------------------------- //
        ArrayBag<Integer> newBag = new ArrayBag<Integer>();

        for (int x = 0; x < 25; x++) {
            newBag.add(x);
        }

        try {
            newBag.contains(99);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("An ArrayIndexOutOfBoundsException was caught");
        }

        int removalIndex = newBag.remove(15); // removalIndex = 15, should now contain the integer 16

        if (newBag.contains(16) != 15) {
            throw new RuntimeException("After removing the integer 15, the null still exists.");
        }

        newBag.add(25);
        newBag.add(26);

        Object[] out = newBag.toArray();
        if (out.length != 50) {
            throw new RuntimeException("The array capacity should have increased to 50, but it didn't");
        }

        for (int x = 27; x < 1100; x++) {
            newBag.add(x);
        }

        out = newBag.toArray();
        if (out.length != 1000) {
            throw new RuntimeException("The array capacity should be at maximum, but it isn't");
        }

        System.out.println("Woot. Completed Arraybag tests");

    }
}
