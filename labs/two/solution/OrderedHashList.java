/* Packaged 1pt */
package labs.two.solution;

/*
  Import only these required classes 4pts
*/
import data_structures.hashmap.KeyMap;
import data_structures.lists.LList;
import data_structures.lists.ListIterator;
import sorting.ArraySorter;

/*
  Proper class name 1pt
  Proper upper bounds 2pt
  Proper lower bounds 2pt
  Open V 1pt
*/
public class OrderedHashList<K extends Comparable<? super K>, V> {

  /* 
    constant size 1pt
    private keymap 1pt
    array of LinkedList for collisions 1pt
  */
  private final int SEARCH_SPACE_SIZE = 2048;
  private final KeyMap<K> keys = new KeyMap<>();
  private final LList<CollisionItem>[] values = new LList[SEARCH_SPACE_SIZE];

  /*
    proper signature 1pt
    handles null with exception 1pt
    throws null pointer exception 1pt
    convert the key to hashcode 1pt
    constructs new linked list 1pt
    adds collision item to linked list 1pt
    adds key to key store 1pt
  */
  public void add(K key, V value) throws NullPointerException {
    if(key == null) throw new NullPointerException();
    int hash = this.toHashCode(key);
    if(values[hash] == null) values[hash] = new LList<CollisionItem>();
    this.values[hash].add(new CollisionItem(key, value));
    this.keys.add(key);
  }

  /*
    proper signature 1pt
    remove key from key store 1pt
    returns null on empty list 1pt
    returns front of list on size one 1pt
    gets iterator to find collision 1pt
    checks collision item for key with compareTo 1pt
    removes it from the collision list 1pt
    returns value 1pt
  */
  public V remove(K key){
    CollisionItem ci;
    this.keys.remove(key);
    LList collisions = values[this.toHashCode(key)];
    if(collisions.size() == 0) return null;
    if(collisions.size() == 1){
      ci = (CollisionItem) collisions.remove();
      return (V)ci.getValue();
    }

    ListIterator it = collisions.iterator();
    while(it.hasNext()){
      ci = (CollisionItem)it.next();
      K collisionKey = (K)ci.getKey();
      if(collisionKey.compareTo(key) == 0){
        collisions.remove(it.index());
        return (V)ci.getValue();
      }
    }
    return null;
  }

  /*
  proper signature 1pt
  gets hash code 1pt
  gets collision list at hashcode index 1pt
  gets iterator 1pt
  compareTo key verse collision key 1pt
  returns value on collision hit 1pt
  */
  public V get(K key){
    CollisionItem ci;
    LList collisions = values[this.toHashCode(key)];
    if(collisions.size() == 0) return null;

    ListIterator it = collisions.iterator();
    while(it.hasNext()){
      ci = (CollisionItem)it.next();
      K collisionKey = (K)ci.getKey();
      if(collisionKey.compareTo(key) == 0){
        return (V)ci.getValue();
      }
    }
    return null;
  }

  /*
  proper signature 1pt
  gets keys by calling toArray 1pt
  doing quick sort or mergeSort 1pt
  returning sorted keys 1pt
  */
  public Object[] keys(){
    K[] keys = this.keys.toArray();
    ArraySorter.mergeSort(keys);
    return (Object[])keys;
  }

  /*
  proper signature 1pt
  returns anonymous or private class 1pt
  inner class has index tracking variable 1pt
  inner class has keys trackign variable 1pt
  */
  public OrderedHashListIterator<K,V> iterator(){
    return new OrderedHashListIterator<K,V>(){

      private int _index = 0;
      private K[] _keys = (K[])keys();

      /*
      proper signature 1pt
      returns index 1pt
      */
      public int index(){
        return this._index;
      }

      /*
      proper signature 1pt
      returns proper value 1pt
      */
      public boolean hasNext(){
        return this._index < _keys.length;
      }

      /*
      proper signature 1pt
      handles out of bounds 1pt
      increments index properly 1pt
      returns key at index 1pt
      */
      public K next() throws IndexOutOfBoundsException {
        if(!this.hasNext()) throw new IndexOutOfBoundsException();
        _index++;
        return (K)get(_keys[_index-1]);
      }

    };
  }

  /*
  proper signature 1pt
  returns hashcode modulated by size 1pt
  */
  private int toHashCode(K key){ return key.hashCode() % SEARCH_SPACE_SIZE; }

  /*
  acceptable to use an extended node class.
  same principles apply

  immutable 1pt
  has private key 1pt
  has private value 1pt
  has constructor taking proper arguments 1pt
  constructor throws exception on null 1pt
  constructor sets key 1pt
  constructor sets value 1pt
  protected name accessor 1pt
  protected value accessor 1pt

  */
  private class CollisionItem<K,V>{

    private K key;
    private V value;

    CollisionItem(K key, V value) throws NullPointerException {
      if(key == null) throw new NullPointerException();
      this.key = key;
      this.value = value;
    }

    K getKey(){ return (K)this.key; }
    V getValue(){ return (V)this.value; }

  }

  public static void main(String[] args){
    OrderedHashList<String, String> ohl = new OrderedHashList<>();

    ohl.add("dog", new String("Dog"));
    ohl.add("cat", new String("Cat"));
    Object[] keys = ohl.keys();
    for(Object k : keys){
      System.out.println(k);
    }
    System.out.println(ohl.get("cat"));
    System.out.println(ohl.remove("cat"));
    keys = ohl.keys();
    for(Object k : keys){
      System.out.println(k);
    }
    ohl.add("cat", "Cat");
    OrderedHashListIterator it = ohl.iterator();
    while(it.hasNext()){
      System.out.println(it.next());
    }
  }
}
