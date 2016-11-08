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

public class KeyMap<K extends Comparable<? super K>> {
  private final int SIZE = 4096;
  private ArrayList<K> store = new ArrayList<>(SIZE);

  public void add(K key){
    int keyHash = key.hashCode();

    /*
    Since we cannot set an index whose
    predecesors are not initialized, lazily
    initialize our array list to null as we need it
    */
    this.store.ensureCapacity(keyHash + 1);
    while(this.store.size() <= keyHash) this.store.add(null);
    this.store.set(keyHash, key);
  }

  /*
   NOTE: DOES NOT CONISDER KEYS OUT OF RANGE
  */
  public K get(K key){
    return (K)this.store.get(key.hashCode());
  }

  /*
   NOTE: DOES NOT CONISDER KEYS OUT OF RANGE
  */
  public K remove(K key){
    return (K)this.store.remove(key.hashCode());
  }

  @SuppressWarnings("unchecked")
  public K[] toArray(){
    Set<K> v = new HashSet<K>(store);
    v.remove(null);
    Object[] a = v.toArray();
    return Arrays.asList(a).toArray((K[])new Comparable[a.length]);
  }

}
