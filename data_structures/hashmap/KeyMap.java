package data_structures.hashmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/*
  A very simple hashmap to
  hash a key to a value

  Meant to help understand the
  lab.

  Use for storing keys for later.
*/

public class KeyMap<K ,V extends Comparable<? super V>> {
  private final int SIZE = 4096;
  private ArrayList<V> store = new ArrayList<>(SIZE);

  public void add(K key, V value){
    int keyHash = key.hashCode();

    /*
    Since we cannot set an index whose
    predecesors are not initialized, lazily
    initialize our array list to null as we need it
    */
    this.store.ensureCapacity(keyHash + 1);
    while(this.store.size() <= keyHash) this.store.add(null);
    this.store.set(keyHash, value);
  }

  /*
   NOTE: DOES NOT CONISDER KEYS OUT OF RANGE
  */
  public V get(K key){
    return (V)this.store.get(key.hashCode());
  }

  /*
   NOTE: DOES NOT CONISDER KEYS OUT OF RANGE
  */
  public V remove(K key){
    return (V)this.store.remove(key.hashCode());
  }

  @SuppressWarnings("unchecked")
  public V[] toArray(){
    Set<V> v = new HashSet<V>(store);
    v.remove(null);
    Object[] a = v.toArray();
    return Arrays.asList(a).toArray((V[])new Comparable[a.length]);
  }

}
