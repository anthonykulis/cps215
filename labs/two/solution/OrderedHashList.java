package labs.two.solution;

import data_structures.hashmap.KeyMap;
import data_structures.lists.LList;
import data_structures.lists.ListIterator;
import sorting.ArraySorter;


public class OrderedHashList<K extends Comparable<? super K>, V> {

  private final int SEARCH_SPACE_SIZE = 2048;
  private final KeyMap<K,K> keys = new KeyMap<>();
  private final LList<CollisionItem>[] values = new LList[SEARCH_SPACE_SIZE];

  public void add(K key, V value) throws NullPointerException {
    if(key == null) throw new NullPointerException();
    int hash = this.toHashCode(key);
    if(values[hash] == null) values[hash] = new LList<CollisionItem>();
    this.values[hash].add(new CollisionItem(key, value));
    this.keys.add(key, key);
  }

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

  public Object[] keys(){
    K[] keys = this.keys.toArray();
    ArraySorter.mergeSort(keys);
    return (Object[])keys;
  }

  public OrderedHashListIterator<K,V> iterator(){
    return new OrderedHashListIterator<K,V>(){

      private int _index = 0;
      private K[] _keys = (K[])keys();

      public int index(){
        return this._index;
      }

      public boolean hasNext(){
        return this._index < _keys.length;
      }

      public K next() throws IndexOutOfBoundsException {
        if(!this.hasNext()) throw new IndexOutOfBoundsException();
        _index++;
        return (K)get(_keys[_index-1]);
      }

    };
  }

  private int toHashCode(K key){ return key.hashCode() % SEARCH_SPACE_SIZE; }

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
